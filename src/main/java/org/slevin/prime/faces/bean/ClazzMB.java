package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.Clazz;
import org.slevin.dao.AlarmHistoryDao;
import org.slevin.dao.ClazzDao;
import org.slevin.dto.AlarmHistoryDto;
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
	

	private List<Clazz> itemList;
	private Clazz item = new Clazz();
	
	private List<AlarmHistoryDto> alarmHistoryList;
	@Autowired
	private AlarmHistoryDao alarmHistoryDao;
	
	public List<AlarmHistoryDto> getAlarmHistoryList() {
		return alarmHistoryList;
	}

	public void setAlarmHistoryList(List<AlarmHistoryDto> alarmHistoryList) {
		this.alarmHistoryList = alarmHistoryList;
	}

	@PostConstruct
    public void init() throws Exception {
		refreshList();
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
	
		alarmHistoryList = alarmHistoryDao.findAlarmHistory(startDate,endDate,clazz.getName());
		
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
