package org.slevin.dao.service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slevin.common.v2.AlarmHistory;
import org.slevin.common.v2.FaceAlarmParameters;
import org.slevin.dao.FaceAlarmParametersDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class FaceAlarmParametersService extends EntityService<FaceAlarmParameters> implements FaceAlarmParametersDao {

	public FaceAlarmParameters findByAlarmId(Long alarmId) throws Exception {
		List<FaceAlarmParameters> list=findByProperty("alarmId", alarmId);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public List<FaceAlarmParameters> find(Date date, String clazzName) {
		Calendar c = Calendar.getInstance();
		
		String query =  "Select t from FaceAlarmParameters t where 1=1  ";
		if(clazzName!=null && !clazzName.equals(""))
			query = query+" and t.alarmCamera=:clazzName";
		if(date!=null){
			
			c.setTime(date);
			c.set(Calendar.HOUR, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			query = query+" and t.alarmDate>= :date1 AND t.alarmDate< :date2";
		
		
		}
		Query queryObject =getEntityManager().createQuery(query);
		if(clazzName!=null && !clazzName.equals("")){
			queryObject.setParameter("clazzName",clazzName);
		}if(date!=null){
			queryObject.setParameter("date1",date);
			queryObject.setParameter("date2",c.getTime());
		}
			
		
		List<FaceAlarmParameters> list=  queryObject.getResultList();
	
		return list;
	}

	
}

