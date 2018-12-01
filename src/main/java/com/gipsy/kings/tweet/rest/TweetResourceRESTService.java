package com.gipsy.kings.tweet.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gipsy.kings.tweet.data.TweetRepository;
import com.gipsy.kings.tweet.model.Tweet;
import com.gipsy.kings.tweet.service.TweetRegistration;

@Path("/tweet")
@RequestScoped
public class TweetResourceRESTService {
	 @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private TweetRepository repository;

    @Inject
    TweetRegistration registration;
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> listAllMembers() {
        return repository.findAllOrderedByName();
    }*/

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tweet lookupTweetById(@PathParam("id") long id) {
    	Tweet tweet = repository.findById(id);
        if (tweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return tweet;
    }

    /**
     * Creates a new tweet from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTweet(Tweet tweet) {

        Response.ResponseBuilder builder = null;

        try {
            // Validates tweet using bean validation
            validateTweet(tweet);

            registration.register(tweet);

            // Create an "ok" response
            builder = Response.ok();
        } catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (ValidationException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("email", "Email taken");
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }

    /**
     * <p>
     * Validates the given tweet variable and throws validation exceptions based on the type of error. If the error is standard
     * bean validation errors then it will throw a ConstraintValidationException with the set of the constraints violated.
     * </p>
     * <p>
     * If the error is caused because an existing tweet with the same email is registered it throws a regular validation
     * exception so that it can be interpreted separately.
     * </p>
     * 
     * @param tweet tweet to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException If tweet with the same email already exists
     */
    private void validateTweet(Tweet tweet) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Tweet>> violations = validator.validate(tweet);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

        // Check the uniqueness of the email address
        if (tweetlAlreadyExists(tweet.getTweetId())) {
            throw new ValidationException("Unique Email Violation");
        }
    }

    /**
     * Creates a JAX-RS "Bad Request" response including a map of all violation fields, and their message. This can then be used
     * by clients to show violations.
     * 
     * @param violations A set of violations that needs to be reported
     * @return JAX-RS response containing all violations
     */
    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        log.fine("Validation completed. violations found: " + violations.size());

        Map<String, String> responseObj = new HashMap<String, String>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

    /**
     * Checks if a tweet with the same email address is already registered. This is the only way to easily capture the
     * "@UniqueConstraint(columnNames = "email")" constraint from the tweet class.
     * 
     * @param email The email to check
     * @return True if the email already exists, and false otherwise
     */
    public boolean tweetlAlreadyExists(String tweetId) {
        Tweet tweet = null;
        try {
        	tweet = repository.findById(tweetId);
        } catch (NoResultException e) {
            // ignore
        }
        return tweet != null;
    }

}
