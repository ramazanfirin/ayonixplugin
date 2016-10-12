package org.slevin.threads.capture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.slevin.common.v2.IpCamera;
import org.slevin.util.SmartSchoolUtil;
import org.springframework.web.context.WebApplicationContext;

import ayonix.FaceID;

import com.github.sarxos.webcam.Webcam;

public class MJPEGCapture extends BaseCapture{

	final static Logger logger = Logger.getLogger(MJPEGCapture.class);
	Webcam webcam;
	
	
	public MJPEGCapture(IpCamera item, WebApplicationContext context, FaceID sdk) {
		super(item, context, sdk);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void intitialize() {
		List<Webcam> list = Webcam.getWebcams();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Webcam webcamtemp = (Webcam) iterator.next();
			if(webcamtemp.getName().equals(item.getName())){
				this.webcam = webcamtemp;
				webcamtemp.open();
				return;
			}	
		}
		
		
	}

	@Override
	public byte[] screenshot() {
		long d= System.currentTimeMillis();
		
		
		byte[] imageInByte=null;
		try {
			BufferedImage image = webcam.getImage();
			//ImageIO.write(image, "PNG", new File("C:\\smartSchool\\temp\\"+(new Date()).getTime()+".png"));
			imageInByte = SmartSchoolUtil.convertToByteArray(image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		long s= System.currentTimeMillis();
		logger.info("Capture complated. camea: "+webcam.getName()+" duration:"+(s-d));
		return imageInByte;
	}

	@Override
	public void stopCamera() {
		// TODO Auto-generated method stub
		
	}

}
