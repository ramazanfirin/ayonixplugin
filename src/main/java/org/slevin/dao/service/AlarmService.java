package org.slevin.dao.service;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.slevin.common.v2.Afid;
import org.slevin.common.v2.Alarm;
import org.slevin.common.v2.ClazzLectureRelation;
import org.slevin.common.v2.IpCamera;
import org.slevin.common.v2.Person;
import org.slevin.dao.AfidDao;
import org.slevin.dao.AlarmDao;
import org.slevin.dao.ClazzLectureRelationDao;
import org.slevin.dto.AlarmDto;
import org.slevin.util.Constants;
import org.slevin.util.SmartSchoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class AlarmService extends EntityService<Alarm> implements AlarmDao {

	final static Logger logger = Logger.getLogger(AlarmService.class);
	
	@Autowired
	private AfidDao afidDao;
	
	@Autowired
	private ClazzLectureRelationDao clazzLectureRelationDao;;
	
	Map<String,List<Person>> tracking = new HashMap<String,List<Person>>();
	
	@Override
	public void insertAlarm(byte[] afidData,byte[] imageInByte, float score,IpCamera item) throws Exception {
		Date starDate= new Date();
		List<Afid> afidList = afidDao.findByProperty("afid", afidData);
		if(afidList.size()==0){
			logger.error("no afid list found");
			return;
		}
		Afid afid = afidList.get(0);
		Date date = new Date();
		if(searchAlarm(item.getName(), afid.getPerson().getId(), SmartSchoolUtil.enrollDate(date, Calendar.SECOND, -3), date)){
			logger.info("kisi onceki frame icinde var");
			return;
		}
		
		
		String path1 = "//alarmImages//"+System.currentTimeMillis();
		
		File file3 = new File(Constants.imagePath+"//"+afid.getPerson().getId()+path1+".jpg");
		InputStream in = new ByteArrayInputStream(imageInByte);
		BufferedImage bImageFromConvert = ImageIO.read(in);
		ImageIO.write(bImageFromConvert, "JPG", file3);
		
		Alarm alarm = new Alarm();
		alarm.setAlarmImage("//"+afid.getPerson().getId()+path1+".jpg");
		alarm.setInsertDate(new Date());
		alarm.setPerson(afid.getPerson());
		alarm.setScore(score);
		alarm.setIpCamera(item);
		
		
		ClazzLectureRelation  clazzLectureRelation = clazzLectureRelationDao.findNearest(new Date(),item.getId());
		alarm.setClazzLectureRelation(clazzLectureRelation);
		if(clazzLectureRelation!=null){
			alarm.setLecture(clazzLectureRelation.getLecture().getName());
			alarm.setTeacher(clazzLectureRelation.getTeacher().getName());
		}
		
		persist(alarm);
		
		Date endDate= new Date();
		long s = endDate.getTime()-starDate.getTime();
		logger.error("alarm inserted:"+alarm.getId()+" duration:"+s);
	}

	@Override
	public void insertAlarmList(List<AlarmDto> alarms) throws Exception {
		
		List<Person> personListInFrame = new ArrayList<Person>();
		String cameraName=null;
		
		for (Iterator iterator = alarms.iterator(); iterator.hasNext();) {
			AlarmDto alarmDto = (AlarmDto) iterator.next();
			List<Afid> afidList = afidDao.findByProperty("afid", alarmDto.getAfid());
			Afid afid = afidList.get(0);
			if(checkTracking(alarmDto.getItem().getName(),afid.getPerson())){
				logger.info("kisi onceki frame icinde var");
				return;
			}
			personListInFrame.add(afid.getPerson());
			cameraName = alarmDto.getItem().getName();
			
			String path1 = "//alarmImages//"+System.currentTimeMillis();
			
			File file3 = new File(Constants.imagePath+"//"+afid.getPerson().getId()+path1+".jpg");
			InputStream in = new ByteArrayInputStream(alarmDto.getImage());
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "JPG", file3);
			
			Alarm alarm = new Alarm();
			alarm.setAlarmImage("//"+afid.getPerson().getId()+path1);
			alarm.setInsertDate(new Date());
			alarm.setPerson(afid.getPerson());
			alarm.setScore(alarmDto.getScore());
			alarm.setIpCamera(alarmDto.getItem());
			persist(alarm);
			logger.error("alarm inserted:"+alarm.getId());
			
		}
		
		tracking.put(cameraName, personListInFrame);
		
		
	}
	
	public Boolean checkTracking(String cameraName,Person person){
		List<Person> personList = tracking.get(cameraName);
		if(personList== null || personList.size()==0)
			return false;
		
		for (Iterator iterator = personList.iterator(); iterator.hasNext();) {
			Person personTemp = (Person) iterator.next();
			if(personTemp.getId()==person.getId())
				return true;
		}
		
		return false;
	}

	@Override
	public Boolean searchAlarm(String cameraName, Long personId,
			Date startDate, Date endDate) {
		Query query = getEntityManager().createQuery("from Alarm a where a.ipCamera.name=:name and a.person.id=:id and a.insertDate>=:startDate and a.insertDate<=:endDate");
		query.setParameter("name", cameraName);
		query.setParameter("id", personId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		
		List<Alarm> list = query.getResultList();
		if(list.size()>0)
			return true;
		else
			return false;
		
		
	}

	@Override
	public List<Alarm> findAlarmHistory(Date startDate, Date endDate, Long classId)
			throws Exception {
		
		Query query = getEntityManager().createQuery("from Alarm a where a.ipCamera.clazz.id=:id and  a.insertDate>=:startDate and a.insertDate<=:endDate");
		query.setParameter("id", classId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		
		List<Alarm> list = query.getResultList();
		return list;
	}
	
	

	
}

