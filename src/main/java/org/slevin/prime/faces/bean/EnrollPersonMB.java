package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.v2.AlarmHistory;
import org.slevin.common.v2.EnrollPerson;
import org.slevin.dao.EnrollPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="enrollPersonMB")
@ViewScoped
public class EnrollPersonMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private EnrollPersonDao enrollPersonDao;
	

	private List<EnrollPerson> enrollPersonList;
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }



	public void refreshList() throws Exception {
		enrollPersonList = enrollPersonDao.findAll();
		System.out.println("alarm size="+enrollPersonList.size());
		
	}



	public List<EnrollPerson> getEnrollPersonList() {
		return enrollPersonList;
	}



	public void setEnrollPersonList(List<EnrollPerson> enrollPersonList) {
		this.enrollPersonList = enrollPersonList;
	}



	
	
	
	
}
