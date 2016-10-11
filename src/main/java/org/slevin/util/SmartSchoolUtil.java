package org.slevin.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

public class SmartSchoolUtil {

	public static Date enrollDate(Date date,int field,int value){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, value);
		return c.getTime();
	}
	
	public static byte[] convertToByteArray(BufferedImage image) throws Exception{
		
		byte[] imageInByte=null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( image, "jpg", baos );
		baos.flush();
		imageInByte = baos.toByteArray();
		baos.close();
		
		return imageInByte;
	}
}
