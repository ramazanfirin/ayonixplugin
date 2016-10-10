package org.slevin.threads.capture;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.slevin.common.v2.IpCamera;
import org.springframework.web.context.WebApplicationContext;

import ayonix.FaceID;

import com.github.sarxos.webcam.Webcam;

public class WebcamCapture extends BaseCapture{

	final static Logger logger = Logger.getLogger(WebcamCapture.class);
	Webcam webcam ;
	
	
	public WebcamCapture(IpCamera item, WebApplicationContext context,FaceID sdk) {
		super(item, context, sdk);
		
	}

	@Override
	public void intitialize() {
		webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(320, 240));
		webcam.open();
		logger.info("webcam opened");
	}

	@Override
	public byte[] screenshot() throws Exception {
//		java.nio.ByteBuffer buffer = webcam.getImageBytes();
//		return buffer.array();
		BufferedImage image = webcam.getImage();
//		ImageIO.write(image, "PNG", new File("C:\\smartSchool\\temp\\"+(new Date()).getTime()+".png"));
		
		
		byte[] imageInByte=null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( image, "jpg", baos );
		baos.flush();
		imageInByte = baos.toByteArray();
		baos.close();
		
		return imageInByte;
		
		
	}

	@Override
	public void stopCamera() {
		webcam.close();
		
	}

	
}
