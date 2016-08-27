package org.slevin.prime.faces.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseMB{

	public void addMessage(String s){
		FacesContext fctx = FacesContext.getCurrentInstance();
        FacesMessage message =new FacesMessage(s);
        fctx.addMessage(null, message);
	}
	
}
