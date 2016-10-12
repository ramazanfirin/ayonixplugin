package org.slevin.threads.capture;

import org.apache.log4j.Logger;
import org.slevin.common.v2.IpCamera;
import org.slevin.dao.AlarmDao;
import org.slevin.dao.IpCameraDao;
import org.springframework.web.context.WebApplicationContext;

import ayonix.AynxFace;
import ayonix.AynxImage;
import ayonix.FaceID;

public abstract class BaseCapture extends Thread implements Capture{

	final static Logger logger = Logger.getLogger(BaseCapture.class);
	 private volatile boolean stop = false;
	
	 FaceID sdk ;
     WebApplicationContext context;
     IpCamera item;
   
	
	public BaseCapture(IpCamera item,WebApplicationContext context,FaceID sdk) {
		super(item.getName());
		this.sdk=sdk;
		this.item=item;
		this.sdk=sdk;
		this.context=context;
	}

	public void requestStop(){
		stop = true;
	}
	
	public void updateStatus(String status){
		IpCameraDao ipCameraDao =(IpCameraDao)context.getBean("ipCameraService");
		ipCameraDao.updateStatus(item.getId(), status);
	}	
	

	public void run() {
		logger.info("thread started");
		intitialize();
		updateStatus("RUNNING");
		while (!stop) {
			try {
				faceIdentify();
	    		//Thread.sleep(500);
	    	} catch (Exception e) {
	    	   e.printStackTrace();
	    	}
	      }
	      if (stop){
	      	stopCamera();
	      	updateStatus("STOPPED");
	    	  System.out.println("thread stopped"); 
	
	      }}
	
	
	
	public void faceIdentify() throws Exception{
		byte[] imageInByte= screenshot();
		IdentifyThread t = new IdentifyThread(item, context, sdk, imageInByte);
		t.start();
	}
	

}
