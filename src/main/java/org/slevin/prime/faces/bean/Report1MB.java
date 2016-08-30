package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.AlarmHistory;
import org.slevin.dao.AlarmHistoryDao;
import org.slevin.dto.AlarmHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="report1MB")
@ViewScoped
public class Report1MB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private AlarmHistoryDao alarmHistoryDao;
	

	private List<AlarmHistoryDto> alarmHistoryList;
	
private Date searchDate;
	
	private String searchClass;
	
	@PostConstruct
    public void init() throws Exception {
		//refreshList();
    }

	public void search() throws Exception{
		refreshList();
	}


	public void refreshList() throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(searchDate);
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date endDate = c.getTime();
		alarmHistoryList = alarmHistoryDao.findAlarmHistory(searchDate,endDate,searchClass);
		System.out.println("alarm size="+alarmHistoryList.size());
		
	}



	public List<AlarmHistoryDto> getAlarmHistoryList() {
		return alarmHistoryList;
	}



	public void setAlarmHistoryList(List<AlarmHistoryDto> alarmHistoryList) {
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
