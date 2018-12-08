package com.gipsy.kings.tweet.model;

import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
 
// POJO class to store the image
public class TweetEntity {
 
    public TweetEntity() {
    }
 
    private byte[] data;
 
    public byte[] getData() {
        return data;
    }
 
    @FormParam("uploadedFile")
    @PartType("application/octet-stream")
    public void setData(byte[] data) {
        this.data = data;
    }
}