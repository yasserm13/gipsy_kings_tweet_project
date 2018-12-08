package com.gipsy.kings.tweet.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import javax.imageio.ImageIO;

public class ImageRegistration {
	
	
	public final String PATH = "/opt/jboss/images";
	
	
	public long register(long senderID,byte[] data) throws IOException {
		
		long timestmp = Instant.now().toEpochMilli();
		System.out.println("In register function");
		// convert byte array back to BufferedImage
		InputStream in = new ByteArrayInputStream(data);
		BufferedImage nvImage = ImageIO.read(in);
		System.out.println("Image Height : "+nvImage.getHeight());
		if ((nvImage.getHeight() > 500) || (nvImage.getWidth() > 500 ))
			nvImage = (BufferedImage) nvImage.getScaledInstance(500, 500, java.awt.Image.SCALE_DEFAULT);
		
		ImageIO.write(nvImage,"JPG" ,new File(PATH + "/" + senderID + "/" + timestmp + ".jpg"));
		
		return timestmp;
	}

}
