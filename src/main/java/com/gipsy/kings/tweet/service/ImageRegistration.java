package com.gipsy.kings.tweet.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.ParseException;
import java.time.Instant;
import java.util.Set;

import javax.imageio.ImageIO;

public class ImageRegistration {
	
	
	public final String PATH = "/opt/jboss/images";
	
	
	public long register(long senderID,byte[] data) throws IOException, ParseException {
		
		Image nv ;
		
		long timestmp = Instant.now().toEpochMilli();
		System.out.println("In register function");
		// convert byte array back to BufferedImage
		InputStream in = new ByteArrayInputStream(data);
		BufferedImage nvImage = ImageIO.read(in);
		if (nvImage == null)
			throw new ParseException(" Error reading image", 0);
		System.out.println("Image Height : "+nvImage.getHeight());
		if ((nvImage.getHeight() > 500) || (nvImage.getWidth() > 500 )) {
			nv = nvImage.getScaledInstance(500, 500, java.awt.Image.SCALE_DEFAULT);
		} else {
			nv = nvImage.getScaledInstance(-1, -1, java.awt.Image.SCALE_DEFAULT);
		}
		
		// construct the buffered image
		BufferedImage bImage = new BufferedImage(nv.getWidth(null), nv.getHeight(null), BufferedImage.TYPE_INT_RGB);

		//obtain it's graphics
		Graphics2D bImageGraphics = bImage.createGraphics();

		//draw the Image (image) into the BufferedImage (bImage)
		bImageGraphics.drawImage(nv, null, null);

		// cast it to rendered image
		RenderedImage rImage = (RenderedImage)bImage;
		
		
		File f = new File(PATH + "/" + senderID + "/" + timestmp + ".jpg");
		try {
			f.getParentFile().mkdirs();
			f.createNewFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileOutputStream fo = new FileOutputStream(f);
		
		ImageIO.write(rImage,"JPG" ,fo);
		
		return timestmp;
	}

}
