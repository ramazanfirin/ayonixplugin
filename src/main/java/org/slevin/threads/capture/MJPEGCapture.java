package org.slevin.threads.capture;

import org.slevin.common.v2.IpCamera;
import org.springframework.web.context.WebApplicationContext;

import ayonix.FaceID;

public class MJPEGCapture extends BaseCapture{

	
	
	
	
	public MJPEGCapture(IpCamera item, WebApplicationContext context, FaceID sdk) {
		super(item, context, sdk);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void intitialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] screenshot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stopCamera() {
		// TODO Auto-generated method stub
		
	}

}
