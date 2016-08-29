package org.slevin.dao.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
	
	public List<AlarmHistoryDto> findAlarmHistory() throws Exception {
		// TODO Auto-generated method stub
		List<AlarmHistoryDto> returnList= new ArrayList<AlarmHistoryDto>();
		
		List<AlarmHistory> list= findAllOrderByDate();
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
			Date date = sdf.parse(dateInString);
			//date = alarmHistory.getDate();
			
			
			ClazzLectureRelation clazzLectureRelation = clazzLectureRelationDao.findNearest(date,ipCamera.getName());
			dto.setClazzLectureRelation(clazzLectureRelation);
			
			returnList.add(dto);
		}
		
		return returnList;
	}

	public List<AlarmHistory> findAllOrderByDate() throws Exception {
		return getEntityManager().createQuery("Select t from " + getEntityClass().getSimpleName() + " t order by date").getResultList();
	}

	
}

