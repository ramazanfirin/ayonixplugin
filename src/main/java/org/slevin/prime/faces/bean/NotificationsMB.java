package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.AlarmHistory;
import org.slevin.common.Notification;
import org.slevin.dao.NotificationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="notificationsMB")
@ViewScoped
public class NotificationsMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private NotificationDao notificationDao;
	

	private List<Notification> notificationList;
	private Date searchDate;
	
	@PostConstruct
    public void init() throws Exception {
		
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
		
		notificationList = notificationDao.findNotification(searchDate, endDate);
		System.out.println("alarm size="+notificationList.size());
		
	}

	public NotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}



	
	
	
}
