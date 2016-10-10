package org.slevin.dao.service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slevin.common.v2.AlarmHistory;
import org.slevin.common.v2.FaceAlarmParameters;
import org.slevin.common.v2.Notification;
import org.slevin.dao.NotificationDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class NotificationService extends EntityService<Notification> implements NotificationDao {

	@Override
	public Notification findNotification(Long alarmId, String purpose) {
		Query queryObject =getEntityManager().createQuery("select t from Notification t where t.alarmId=:alarmId and t.purpose=:purpose");
		queryObject.setParameter("alarmId", alarmId);
		queryObject.setParameter("purpose", purpose);
		List<Notification> list = (List<Notification>)queryObject.getResultList();
	
		if(list==null || list.size()==0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public List<Notification> findNotification(Date date, Date endDate) {
		String query =  "Select t from Notification t where 1=1  ";
		if(date!=null){
			query = query+" and t.date>= :date1 AND t.date< :date2";
		
		
		}
		query=query+" order by t.date";
		Query queryObject =getEntityManager().createQuery(query);
		
		if(date!=null){
			queryObject.setParameter("date1",date);
			queryObject.setParameter("date2",endDate);
		}
			
		
		List<Notification> list=  queryObject.getResultList();
	
		return list;
	}
	

	
}

