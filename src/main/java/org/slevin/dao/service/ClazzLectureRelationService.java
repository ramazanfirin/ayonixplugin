package org.slevin.dao.service;


import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.TemporalType;

import org.slevin.common.ClazzLectureRelation;
import org.slevin.dao.ClazzLectureRelationDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class ClazzLectureRelationService extends EntityService<ClazzLectureRelation> implements ClazzLectureRelationDao {

	@Override
	public ClazzLectureRelation findNearest(Date date,String clazzName) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		
		Date startDate = c.getTime();
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date endDate = c.getTime();
		
		
		List<ClazzLectureRelation> list=  getEntityManager().createQuery("Select t from ClazzLectureRelation t where t.startDate>= :date1 AND t.startDate< :date2")
		.setParameter("date1",startDate)
		.setParameter("date2",endDate)
		//.setParameter(3,clazzName)
		.getResultList();
		
		ClazzLectureRelation temp = null;
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			ClazzLectureRelation clazzLectureRelation = (ClazzLectureRelation) iterator.next();
			if(clazzLectureRelation.getStartDate().getTime() < date.getTime()){
				if(temp==null)
					temp = clazzLectureRelation;
				else{
					if(temp.getStartDate().getTime()<clazzLectureRelation.getStartDate().getTime()){
						temp = clazzLectureRelation;
					}
				}
			}
		}
		
		
		  return temp;
	}

	
	public List<ClazzLectureRelation> find(Date date, String clazzName) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		
		
		List<ClazzLectureRelation> list=  getEntityManager().createQuery("Select t from ClazzLectureRelation t where t.clazz.name=:clazzName and t.startDate>= :date1 AND t.startDate< :date2")
				.setParameter("date1",date)
				.setParameter("date2",c.getTime())
				.setParameter("clazzName",clazzName)
				.getResultList();
	
		return list;
	}

	
}

