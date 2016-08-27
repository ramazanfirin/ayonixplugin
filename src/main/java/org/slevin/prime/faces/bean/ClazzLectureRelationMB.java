package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.Clazz;
import org.slevin.common.ClazzLectureRelation;
import org.slevin.common.Teacher;
import org.slevin.dao.ClazzDao;
import org.slevin.dao.ClazzLectureRelationDao;
import org.slevin.dao.TeacherDao;
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
	
	
	private List<ClazzLectureRelation> itemList;
	private ClazzLectureRelation item = new ClazzLectureRelation();
	
	private List<Clazz> clazzList;
	private List<Teacher> teacherList;
	
	@PostConstruct
    public void init() throws Exception {
		clazzList = clazzDao.findAll();
		teacherList = teacherDao.findAll();
		refreshList();
    }

	public void create() throws Exception{
		item = new ClazzLectureRelation();
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





	


	
	
	
	
}
