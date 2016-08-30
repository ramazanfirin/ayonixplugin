package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.Clazz;
import org.slevin.common.ClazzLectureRelation;
import org.slevin.common.Lecture;
import org.slevin.common.Teacher;
import org.slevin.dao.AlarmHistoryDao;
import org.slevin.dao.ClazzDao;
import org.slevin.dao.ClazzLectureRelationDao;
import org.slevin.dao.LectureDao;
import org.slevin.dao.TeacherDao;
import org.slevin.dto.AlarmHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="clazzLectureRelationMB")
@ViewScoped
public class ClazzLectureRelationMB extends BaseMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private ClazzLectureRelationDao itemDao;
	
	@Autowired
	private ClazzDao clazzDao;
	
	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired
	private AlarmHistoryDao alarmHistoryDao;

	
	@Autowired
	private LectureDao lectureDao;
	
	private List<ClazzLectureRelation> itemList;
	private ClazzLectureRelation item = new ClazzLectureRelation();
	
	private List<Clazz> clazzList;
	private List<Teacher> teacherList;
	
	private List<Lecture> lectureList;
	
	

	private List<AlarmHistoryDto> alarmHistoryList;
	
	
	private Date startDate;
	private Date endDate;
	
	private Date searchtDate;
	
	private String searchClass;
	
	private Boolean update=false;
	@PostConstruct
    public void init() throws Exception {
		clazzList = clazzDao.findAll();
		teacherList = teacherDao.findAll();
		lectureList = lectureDao.findAll();
		//refreshList();
    }
	
	public void search() throws Exception{
		refreshList();
	}
	
	public List<AlarmHistoryDto> getAlarmHistoryList() {
		return alarmHistoryList;
	}

	public void setAlarmHistoryList(List<AlarmHistoryDto> alarmHistoryList) {
		this.alarmHistoryList = alarmHistoryList;
	}

	public void attandenceList() throws Exception{
		ClazzLectureRelation clazzLectureRelation = itemDao.findById(item.getId());
		System.out.println("startDate="+item.getStartDate()+",endDate="+item.getEndDate());
		alarmHistoryList = alarmHistoryDao.findAlarmHistory(clazzLectureRelation.getStartDate(),clazzLectureRelation.getEndDate(),clazzLectureRelation.getClazz().getName());
		System.out.println("alarm list"+alarmHistoryList.size());
		System.out.println("startDate2="+item.getStartDate()+",endDate2="+item.getEndDate());
	}

	public void create() throws Exception{
		item = new ClazzLectureRelation();
		System.out.println("create");
		update=false;
	}
	
	public void update() throws Exception{
		
		itemDao.merge(item);
		System.out.println("dikkat");
	}
	
	public void changeToUpdate(){
		update=true;
	}
	
	public void cancel(){
		update=false;
	}
	
	public Date prepareDate(Date date,int hour,int minute){
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.setTime(date);
		calendarTemp.set(Calendar.HOUR, hour);
		calendarTemp.set(Calendar.MINUTE, minute);
		return calendarTemp.getTime();
	}
	
	public void merge() throws Exception{
		//clazz.setName(name);
		if(update){
			System.out.println("startdate:"+item.getStartDate());
			ClazzLectureRelation aaa = itemDao.findById(item.getId());
			item.setStartDate(prepareDate(aaa.getStartDate(), item.getStartDate().getHours(), item.getStartDate().getMinutes()));
			item.setEndDate(prepareDate(aaa.getEndDate(), item.getEndDate().getHours(), item.getEndDate().getMinutes()));
			itemDao.merge(item);
			update=false;
			return;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		while(calendar.getTime().getTime() < endDate.getTime()){
			item.setStartDate(prepareDate(calendar.getTime(), item.getStartDate().getHours(), item.getStartDate().getMinutes()));
			item.setEndDate(prepareDate(calendar.getTime(), item.getEndDate().getHours(), item.getEndDate().getMinutes()));
			itemDao.merge(item);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			
			item.setId(null);
		}
		

		refreshList();
		addMessage("Islem Tamamlandi");
	}
	
	public void delete() throws Exception{
		itemDao.remove(item.getId());
		refreshList();
		addMessage("Islem Tamamlandi");
	}

	public void refreshList() throws Exception {
		itemList = itemDao.find(searchtDate, searchClass);
		System.out.println("class size="+itemList.size());
		
	}

	public List<ClazzLectureRelation> getItemList() {
		return itemList;
	}

	public void setItemList(List<ClazzLectureRelation> itemList) {
		this.itemList = itemList;
	}

	public ClazzLectureRelation getItem() {
		return item;
	}

	public void setItem(ClazzLectureRelation item) {
		this.item = item;
	}

	public List<Clazz> getClazzList() {
		return clazzList;
	}

	public void setClazzList(List<Clazz> clazzList) {
		this.clazzList = clazzList;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Lecture> getLectureList() {
		return lectureList;
	}

	public void setLectureList(List<Lecture> lectureList) {
		this.lectureList = lectureList;
	}

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public Date getSearchtDate() {
		return searchtDate;
	}

	public void setSearchtDate(Date searchtDate) {
		this.searchtDate = searchtDate;
	}

	public String getSearchClass() {
		return searchClass;
	}

	public void setSearchClass(String searchClass) {
		this.searchClass = searchClass;
	}





	


	
	
	
	
}
