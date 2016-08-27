package org.slevin.prime.faces.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slevin.common.Clazz;
import org.slevin.dao.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseMB{

	
	
	public void addMessage(String s){
		FacesContext fctx = FacesContext.getCurrentInstance();
        FacesMessage message =new FacesMessage(s);
        fctx.addMessage(null, message);
	}
	
}
