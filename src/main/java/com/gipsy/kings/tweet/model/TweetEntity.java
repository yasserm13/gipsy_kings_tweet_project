package com.gipsy.kings.tweet.model;

import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
 
// POJO class to store the image
public class TweetEntity {
 
    public TweetEntity() {
    	
    }
 
    private byte[] data;
    private long senderId;
 
    public byte[] getData() {
        return data;
    }
 
    @FormParam("uploadedFile")
    @PartType("application/octet-stream")
    public void setData(byte[] data) {
        this.data = data;
    }
    
    public long getSenderId() {
        return senderId;
    }
 
    @FormParam("senderId")
    @PartType("application/octet-stream")
    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }
}