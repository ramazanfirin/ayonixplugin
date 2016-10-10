package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.v2.Alarm;
import org.slevin.common.v2.Clazz;
import org.slevin.common.v2.ClazzLectureRelation;
import org.slevin.dao.AlarmDao;
import org.slevin.dao.ClazzDao;
import org.slevin.dao.ClazzLectureRelationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="clazzMB")
@ViewScoped
public class ClazzMB extends BaseMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private ClazzDao itemDao;
	
	@Autowired
	private ClazzLectureRelationDao clazzLectureRelationDao;
	

	private List<Clazz> itemList;
	private Clazz item = new Clazz();
	
	private List<Alarm> alarmList;
	@Autowired
	private AlarmDao alarmDao;
	
	public List<Alarm> getAlarmList() {
		return alarmList;
	}

	public void setAlarmHList(List<Alarm> alarmList) {
		this.alarmList = alarmList;
	}

	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }
	
	public void currentSnapshot() throws Exception{
		ClazzLectureRelation clazzLectureRelation = clazzLectureRelationDao.findNearest(new Date(),item.getId());
		if(clazzLectureRelation==null)
			alarmList.clear();
		else
			alarmList = alarmDao.findAlarmHistory(clazzLectureRelation.getStartDate(),clazzLectureRelation.getEndDate(),item.getId());
	}

	
	public void attandenceList() throws Exception{
		Clazz clazz = itemDao.findById(item.getId());
	
	Calendar c= Calendar.getInstance();
	c.set(Calendar.HOUR, 0);
	c.set(Calendar.MINUTE, 0);
	c.set(Calendar.SECOND, 0);
	Date startDate = c.getTime();
	
	c.set(Calendar.HOUR, 23);
	c.set(Calendar.MINUTE, 59);
	c.set(Calendar.SECOND, 59);
	
	Date endDate = c.getTime();
	
		alarmList = alarmDao.findAlarmHistory(startDate,endDate,clazz.getId());
		
	}

	public void create() throws Exception{
		item = new Clazz();
		System.out.println("create");
	}
	
	public void merge() throws Exception{
		//clazz.setName(name);
		itemDao.merge(item);
		refreshList();
		addMessage("Islem Tamamlandi");
	}
	
	public void delete() throws Exception{
		itemDao.remove(item.getId());
		refreshList();
		addMessage("Islem Tamamlandi");
	}

	public void refreshList() throws Exception {
		itemList = itemDao.findAll();
		System.out.println("class size="+itemList.size());
		
	}

	public List<Clazz> getItemList() {
		return itemList;
	}

	public void setItemList(List<Clazz> itemList) {
		this.itemList = itemList;
	}

	public Clazz getItem() {
		return item;
	}

	public void setItem(Clazz item) {
		this.item = item;
	}

	



	


	
	
	
	
}
