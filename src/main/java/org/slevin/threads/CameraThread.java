package org.slevin.threads;

import javax.annotation.PostConstruct;

import org.slevin.common.v2.IpCamera;
import org.slevin.dao.IpCameraDao;
import org.slevin.prime.faces.bean.IpCameraMB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import ayonix.FaceID;

@Component
@Scope("prototype")
public class CameraThread extends Thread {
	   private volatile boolean stop = false;
	  
	   @Autowired
		private IpCameraDao itemDao;
	   
	   FaceID sdk ;
	   
	   WebApplicationContext context;
	   
	   @PostConstruct
	   public void init(){
		   System.out.println("itemdao control:"+itemDao);
	   }
	   
	   
	   
	   public CameraThread() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CameraThread(IpCamera item,WebApplicationContext context,FaceID sdk) {
		super(item.getName());
		this.context=context;
		this.sdk=sdk;
		
	}
	public void run() {
		IpCameraDao  service =(IpCameraDao)context.getBean("ipCameraService");
		IpCameraMB  cameraMB =(IpCameraMB)context.getBean("ipCameraMB");
		 
	      while (!stop) {
	    	  System.out.println("thread runnig");
	    	  try {
	    		   // thread to sleep for 1000 milliseconds
	    		  System.out.println("itemdao control:"+service.count());
	    		  System.out.println("cameraMB control:"+cameraMB.getItem().getName());
	    		   Thread.sleep(5000);
	    		   } catch (Exception e) {
	    		   System.out.println(e);
	    		   }
	      }
	      if (stop)
	      	System.out.println("Detected stop"); 
	   }
	   public void requestStop() {
	      stop = true;
	   }
}	   
