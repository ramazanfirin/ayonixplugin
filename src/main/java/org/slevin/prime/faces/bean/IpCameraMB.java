package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.AlarmHistory;
import org.slevin.common.EnrollPerson;
import org.slevin.common.IpCamera;
import org.slevin.dao.EnrollPersonDao;
import org.slevin.dao.IpCameraDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="ipCameraMB")
@ViewScoped
public class IpCameraMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private IpCameraDao ipCameraDao;
	

	private List<IpCamera> ipCameraList;
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }



	public void refreshList() throws Exception {
		ipCameraList = ipCameraDao.findAll();
		System.out.println("alarm size="+ipCameraList.size());
		
	}



	public List<IpCamera> getIpCameraList() {
		return ipCameraList;
	}



	public void setIpCameraList(List<IpCamera> ipCameraList) {
		this.ipCameraList = ipCameraList;
	}






	
	
	
	
}
