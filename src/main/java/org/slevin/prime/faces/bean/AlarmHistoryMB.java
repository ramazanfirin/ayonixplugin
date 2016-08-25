package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.AlarmHistory;
import org.slevin.dao.AlarmHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="alarmHistoryMB")
@ViewScoped
public class AlarmHistoryMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private AlarmHistoryDao alarmHistoryDao;
	

	private List<AlarmHistory> alarmHistoryList;
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }



	public void refreshList() throws Exception {
		alarmHistoryList = alarmHistoryDao.findAll();
		System.out.println("alarm size="+alarmHistoryList.size());
		
	}



	public List<AlarmHistory> getAlarmHistoryList() {
		return alarmHistoryList;
	}



	public void setAlarmHistoryList(List<AlarmHistory> alarmHistoryList) {
		this.alarmHistoryList = alarmHistoryList;
	}

	
	
	
	
}
