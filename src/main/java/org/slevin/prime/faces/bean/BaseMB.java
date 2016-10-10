package org.slevin.prime.faces.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slevin.common.v2.Clazz;
import org.slevin.dao.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;

import ayonix.FaceID;

public class BaseMB{

	String imageLocation="http://localhost:8080/smartSchoolImages";
	
	String imagePath="c:/smartSchool";
	
	FaceID sdk = new FaceID("C:\\Program Files (x86)\\Ayonix\\FaceID\\data\\engine");
	
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
