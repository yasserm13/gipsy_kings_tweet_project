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
package com.gipsy.kings.tweet.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "tweetId"))
public class Tweet implements Serializable {

    @Id
    @SequenceGenerator(name = "TWEET_ID_GENERATOR", sequenceName = "TWEET_SEQ", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWEET_ID_GENERATOR")
    @Column(name = "tweetId")
    // le tweet id n'est dispo qu'appres l'enregistrement en base
    private long tweetId;

    @NotNull
    @Column(name = "senderId")
    private long senderId;
    
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "text")
    private String text;
    
    @Column(name = "date")
    private long date;

    @Column(name = "urlMedia")
    private String urlMedia;
   

    // la date n'est dispo qu'a la sauvegarde en base !
    @PrePersist
    protected void onDate() {
    	date = Instant.now().toEpochMilli();
    }
    
	public String getUrlMedia() {
		return urlMedia;
	}

	public void setUrlMedia(String urlMedia) {
		this.urlMedia = urlMedia;
	}

	public long getTweetId() {
        return tweetId;
    }
    
    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }
    
    public long getSenderId() {
        return senderId;
    }
    
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }
    
    public void setDate(long date) {
        this.date = date;
    }
}
