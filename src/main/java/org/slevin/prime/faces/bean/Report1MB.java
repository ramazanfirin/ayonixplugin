package org.slevin.prime.faces.bean;


import java.io.Serializable;
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
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }



	public void refreshList() throws Exception {
		alarmHistoryList = alarmHistoryDao.findAlarmHistory();
		System.out.println("alarm size="+alarmHistoryList.size());
		
	}



	public List<AlarmHistoryDto> getAlarmHistoryList() {
		return alarmHistoryList;
	}



	public void setAlarmHistoryList(List<AlarmHistoryDto> alarmHistoryList) {
		this.alarmHistoryList = alarmHistoryList;
	}


	
	
	
	
}
