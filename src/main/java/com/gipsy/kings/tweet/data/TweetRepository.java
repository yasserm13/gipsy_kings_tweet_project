/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gipsy.kings.tweet.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.gipsy.kings.tweet.model.Tweet;

@ApplicationScoped
public class TweetRepository {

    @Inject
    @PersistenceContext(name="mysql")
    private EntityManager em;

    public Tweet findById(Long tweetId) {
    	System.out.println("recherche tweet : "+tweetId);
        return em.find(Tweet.class, tweetId);
    }
    
    public List<Tweet> findAllTweetOrderedByTweetId() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tweet> criteria = cb.createQuery(Tweet.class);
        Root<Tweet> tweet = criteria.from(Tweet.class);
        criteria.select(tweet).orderBy(cb.asc(tweet.get("tweetId")));
        return em.createQuery(criteria).getResultList();
    }

}