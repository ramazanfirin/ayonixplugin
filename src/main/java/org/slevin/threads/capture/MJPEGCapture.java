package org.slevin.threads.capture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.slevin.common.v2.IpCamera;
import org.slevin.util.SmartSchoolUtil;
import org.springframework.web.context.WebApplicationContext;

import ayonix.FaceID;

import com.github.sarxos.webcam.Webcam;

public class MJPEGCapture extends BaseCapture{

	
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
		BufferedImage image = webcam.getImage();
		
		byte[] imageInByte=null;
		try {
			//ImageIO.write(image, "PNG", new File("C:\\smartSchool\\temp\\"+(new Date()).getTime()+".png"));
			imageInByte = SmartSchoolUtil.convertToByteArray(image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("image error");
		}
	
		return imageInByte;
	}

	@Override
	public void stopCamera() {
		// TODO Auto-generated method stub
		
	}

}
