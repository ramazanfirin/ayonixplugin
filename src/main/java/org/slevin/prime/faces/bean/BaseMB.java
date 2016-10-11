package org.slevin.prime.faces.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slevin.common.v2.Clazz;
import org.slevin.dao.EntityDao;
import org.slevin.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import ayonix.AynxFace;
import ayonix.AynxImage;
import ayonix.FaceID;

public class BaseMB{

	String imageLocation=Constants.imageLocation;
	
	String imagePath="c:/smartSchool";
	
	FaceID sdk = new FaceID("C:\\Program Files (x86)\\Ayonix\\FaceID\\data\\engine");
	
	public byte[] createAfid(byte[] image) throws Exception{
		AynxImage img = sdk.LoadImage(image);
		AynxFace[] faces = sdk.DetectFaces(img);
		if(faces.length>1)
			throw new Exception("more then 1 face");
		AynxFace face = faces[0];
		sdk.PreprocessFace(face);
		byte[] query = sdk.CreateAfid(face);
		return query;
	}
	
	public void addMessage(String s){
		FacesContext fctx = FacesContext.getCurrentInstance();
        FacesMessage message =new FacesMessage(s);
        fctx.addMessage(null, message);
	}

	public void addMessage(FacesMessage.Severity severity,String message){
		FacesMessage msg = null;  
		msg = new FacesMessage(severity, message, message);  
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public FaceID getSdk() {
		return sdk;
	}

	public void setSdk(FaceID sdk) {
		this.sdk = sdk;
	}
	
}
