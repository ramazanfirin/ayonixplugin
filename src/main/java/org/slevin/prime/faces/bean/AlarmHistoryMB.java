package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.Date;
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
	private Date searchDate;
	
	private String searchClass;
	
	@PostConstruct
    public void init() throws Exception {
		
    }

	public void search() throws Exception{
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



	public Date getSearchDate() {
		return searchDate;
	}



	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}



	public String getSearchClass() {
		return searchClass;
	}



	public void setSearchClass(String searchClass) {
		this.searchClass = searchClass;
	}

	
	
	
	
}
