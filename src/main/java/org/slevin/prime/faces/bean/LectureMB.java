package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.v2.Lecture;
import org.slevin.common.v2.Teacher;
import org.slevin.dao.LectureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="lectureMB")
@ViewScoped
public class LectureMB extends BaseMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private LectureDao itemDao;
	

	private List<Lecture> itemList;
	private String name;
	private Lecture item = new Lecture();
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }

	public void create() throws Exception{
		item = new Lecture();
		item.setName("");
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

	public List<Lecture> getItemList() {
		return itemList;
	}

	public void setItemList(List<Lecture> itemList) {
		this.itemList = itemList;
	}

	public Lecture getItem() {
		return item;
	}

	public void setItem(Lecture item) {
		this.item = item;
	}

	
	
	
}
