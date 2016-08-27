package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.Clazz;
import org.slevin.common.Teacher;
import org.slevin.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="teacherMB")
@ViewScoped
public class TeacherMB extends BaseMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private TeacherDao itemDao;
	

	private List<Teacher> itemList;
	private String name;
	private Teacher item = new Teacher();
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }

	public void create() throws Exception{
		item = new Teacher();
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

	public List<Teacher> getItemList() {
		return itemList;
	}

	public void setItemList(List<Teacher> itemList) {
		this.itemList = itemList;
	}

	public Teacher getItem() {
		return item;
	}

	public void setItem(Teacher item) {
		this.item = item;
	}



	


	
	
	
	
}
