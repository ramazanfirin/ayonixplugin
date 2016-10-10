package org.slevin.threads.capture;

import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.slevin.common.v2.IpCamera;
import org.slevin.dao.AlarmDao;
import org.slevin.prime.faces.bean.IpCameraMB;
import org.springframework.web.context.WebApplicationContext;

import ayonix.AynxFace;
import ayonix.AynxImage;
import ayonix.FaceID;

public class IdentifyThread extends Thread{
	
	final static Logger logger = Logger.getLogger(IdentifyThread.class);
	
	 FaceID sdk ;
     WebApplicationContext context;
     IpCamera item;
	 byte[] imageInByte;
	 Vector<byte[]> afidDatabase; 
	 
	 AlarmDao  alarmDao;
     
	public IdentifyThread(IpCamera item,WebApplicationContext context,FaceID sdk,byte[] imageInByte) {
		super();
		this.sdk=sdk;
		this.item=item;
		this.sdk=sdk;
		this.imageInByte = imageInByte;
		IpCameraMB  cameraMB =(IpCameraMB)context.getBean("ipCameraMB");
		this.afidDatabase = cameraMB.getAfidDatabase();
		alarmDao =(AlarmDao)context.getBean("alarmService");
	}



	public void run() {
		Date date = new Date();
		AynxImage img = sdk.LoadImage(imageInByte);
		AynxFace[] faces = sdk.DetectFaces(img);
		if(faces.length==0){
			logger.info("no face detected");
		}for (int i = 0; i < faces.length; i++) {
			AynxFace face = faces[i];
			sdk.PreprocessFace(face);
			byte[] query = sdk.CreateAfid(face);
			float[] scores = new float[afidDatabase.size()];
            int[] indexes = new int[afidDatabase.size()];
            sdk.MatchAfids(query, afidDatabase, scores, indexes);
            int index = sort(scores);
	        float score  = scores[index];
	        byte[] a = afidDatabase.get(index);
	        if(score>0.7)
				try {
					createAlarm(a,imageInByte,score,item);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		Date endDate = new Date();
		logger.info(endDate.getTime()-date.getTime()+" duration");
		
		
		
	}
	
	public void createAlarm(byte[] afid,byte[] imageInByte, float score,IpCamera item) throws Exception{
		alarmDao.insertAlarm(afid, imageInByte, score,item);
		//logger.info("alarm created score:"+score);
	}

public static int sort(float[] scores){
		
		float value = Float.MIN_VALUE;
		int index = Integer.MIN_VALUE;
		
		for(int i =0;i<scores.length;i++) {
		
		            if(scores[i] > value) {
		            	value = scores[i];

		            	index = i;
	
		            }
		
		        }

		return index;
	}
}
