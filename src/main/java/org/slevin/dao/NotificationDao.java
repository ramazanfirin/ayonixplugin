package org.slevin.dao;

import java.util.Date;
import java.util.List;

import org.slevin.common.v2.Notification;


public interface NotificationDao extends EntityDao<Notification> {
	public Notification findNotification(Long alarmId,String purpose);
	public List<Notification> findNotification(Date startDate,Date endDate);
	
}
