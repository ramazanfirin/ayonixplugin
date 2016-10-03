package org.slevin.dao.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.slevin.common.AlarmHistory;
import org.slevin.common.Category;
import org.slevin.common.ClazzLectureRelation;
import org.slevin.common.EnrollPerson;
import org.slevin.common.FaceAlarmParameters;
import org.slevin.common.IpCamera;
import org.slevin.dao.AlarmHistoryDao;
import org.slevin.dao.CategoryDao;
import org.slevin.dao.ClazzLectureRelationDao;
import org.slevin.dao.EnrollPersonDao;
import org.slevin.dao.FaceAlarmParametersDao;
import org.slevin.dao.IpCameraDao;
import org.slevin.dto.AlarmHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class AlarmHistoryService extends EntityService<AlarmHistory> implements AlarmHistoryDao {

	@Autowired
	private FaceAlarmParametersDao faceAlarmParametersDao;
	
	@Autowired
	private EnrollPersonDao enrollPersonDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private IpCameraDao ipCameraDao;
	
	@Autowired
	private ClazzLectureRelationDao clazzLectureRelationDao;
	
	public List<AlarmHistoryDto> findAlarmHistory(Date date,Date endDate, String clazzName) throws Exception {
		// TODO Auto-generated method stub
		List<AlarmHistoryDto> returnList= new ArrayList<AlarmHistoryDto>();
		
		List<AlarmHistory> list= find(date,endDate, clazzName);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			AlarmHistory alarmHistory = (AlarmHistory) iterator.next();
			
			AlarmHistoryDto dto =new AlarmHistoryDto();
			dto.setAlarmHistory(alarmHistory);
			
			FaceAlarmParameters faceAlarmParameters  = faceAlarmParametersDao.findByAlarmId(alarmHistory.getId());
			dto.setFaceAlarmParameters(faceAlarmParameters);
		
			EnrollPerson enrollPerson = enrollPersonDao.findById(new Long(alarmHistory.getUserId()));
			dto.setEnrollPerson(enrollPerson);
			
			Category category = categoryDao.findById(enrollPerson.getCategory());
			dto.setCategory(category);
			
			IpCamera ipCamera = ipCameraDao.findById(alarmHistory.getCameraId());
			dto.setIpCamera(ipCamera);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			String dateInString = "02-08-2016 16:00:00";
			Date date2 = sdf.parse(dateInString);
			//date = alarmHistory.getDate();
			
			
			ClazzLectureRelation clazzLectureRelation = clazzLectureRelationDao.findNearest(date2,ipCamera.getName());
			dto.setClazzLectureRelation(clazzLectureRelation);
			
			returnList.add(dto);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		return returnList;
	}

	public List<AlarmHistory> findAllOrderByDate() throws Exception {
		return getEntityManager().createQuery("Select t from " + getEntityClass().getSimpleName() + " t order by date").getResultList();
	}

	@Override
	public List<AlarmHistory> find(Date date, Date endDate,String clazzName) throws Exception {

		Long cameraId=findCameraId(clazzName);

		String query =  "Select t from AlarmHistory t where 1=1  ";
		if(clazzName!=null && !clazzName.equals(""))
			query = query+" and t.cameraId=:clazzName";
		if(date!=null){
			query = query+" and t.date>= :date1 AND t.date< :date2";
		
		
		}
		query=query+" order by t.date";
		Query queryObject =getEntityManager().createQuery(query);
		if(clazzName!=null && !clazzName.equals("")){
			queryObject.setParameter("clazzName",cameraId);
		}if(date!=null){
			queryObject.setParameter("date1",date);
			queryObject.setParameter("date2",endDate);
		}
			
		
		List<AlarmHistory> list=  queryObject.getResultList();
	
		return list;
	}

	public Long findCameraId(String cameraName) throws Exception{
		List<IpCamera> list= ipCameraDao.findByProperty("name",cameraName);
		if(list.size()==0)
			return new Long(0);
		else
			return list.get(0).getId();
	}

	
}

