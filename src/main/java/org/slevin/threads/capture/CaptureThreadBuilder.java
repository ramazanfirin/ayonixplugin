package org.slevin.threads.capture;

import org.slevin.common.v2.IpCamera;
import org.springframework.web.context.WebApplicationContext;

import ayonix.FaceID;

public class CaptureThreadBuilder {
    public  static BaseCapture getThread(IpCamera item,WebApplicationContext context,FaceID sdk){
    	if(item.getType().equals("USB")){
    		return new WebcamCapture(item, context, sdk);
    	}else if(item.getType().equals("MJPEG")){
    		return new MJPEGCapture(item, context, sdk);
    	}else if(item.getType().equals("RTSP")){
    		return new OnvifCapture(item, context, sdk);	
    	}else
    		return null;
    }
}
