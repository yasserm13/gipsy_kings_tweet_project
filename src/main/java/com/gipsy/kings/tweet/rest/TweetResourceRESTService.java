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

@Path("/")
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

    @GET
    @Path("/tweet/{tweetId:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    //pour essai
    //curl -i    --request GET    http://localhost:8080/gipsy-kings-tweet/tweet/1
    public Tweet lookupTweetById(@PathParam("tweetId") long tweetId) {
    	Tweet tweet = repository.findById(tweetId);
        if (tweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return tweet;
    }

    /**
     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Path("/tweet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //pour essai
    // curl -i  --header "Content-Type: application/json"   --request POST   --data '{"senderId":"1234","text":"test text","urlMedia":"test urlMedia"}' http://localhost:8080/gipsy-kings-tweet/tweet
    public Response createTweet(Tweet tweet) {
    	System.out.println("tweet post");
        Response.ResponseBuilder builder = null;

        try {
            // Validates member using bean validation
            validateMember(tweet);

            registration.register(tweet);

            // Create an "ok" response
            builder = Response.ok();
        } catch (ConstraintViolationException ce) {
            // Handle bean validation issues typiquement tweet trop long ou un type qui va pas un non null ?
        	// il s'agit d'une erreur 406 je l'ai modififie dans createViolationResponse
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (ValidationException e) {
            // Handle the unique constrain violation tweetid existe deja => ne devrait jamais arrivé
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("tweetid", "tweetid existe déjà");
            builder = Response.status(Response.Status.NOT_ACCEPTABLE).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions = 
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("erreur", e.getMessage());
            builder = Response.status(Response.Status.NOT_ACCEPTABLE).entity(responseObj);
        }

        return builder.build();
    }

    /**
     * <p>
     * Validates the given Member variable and throws validation exceptions based on the type of error. If the error is standard
     * bean validation errors then it will throw a ConstraintValidationException with the set of the constraints violated.
     * </p>
     * <p>
     * If the error is caused because an existing member with the same tweetid is registered it throws a regular validation
     * exception so that it can be interpreted separately.
     * </p>
     * 
     * @param member Member to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException If member with the same tweetid already exists
     */
    private void validateMember(Tweet tweet) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Tweet>> violations = validator.validate(tweet);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

    }

    /**
     * Creates a JAX-RS "Bad Request" response including a map of all violation fields, and their message. This can then be used
     * by clients to show violations.
     * Renvoie une erreur 406 avec les violations
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

        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(responseObj);
    }


}
