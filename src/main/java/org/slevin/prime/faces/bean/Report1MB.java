package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.v2.Alarm;
import org.slevin.dao.AlarmDao;
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
	private AlarmDao alarmDao;
	

	private List<Alarm> alarmList;
	
private Date searchDate;
	
	private Long searchClassId;
	
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
		alarmList = alarmDao.findAlarmHistory(searchDate,endDate,searchClassId);
		System.out.println("alarm size="+alarmList.size());
		
	}




	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	public List<Alarm> getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(List<Alarm> alarmList) {
		this.alarmList = alarmList;
	}

	public Long getSearchClassId() {
		return searchClassId;
	}

	public void setSearchClassId(Long searchClassId) {
		this.searchClassId = searchClassId;
	}

	
	
	
	
	
}
