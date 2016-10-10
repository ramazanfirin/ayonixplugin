package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.v2.PersonCategory;
import org.slevin.common.v2.Teacher;
import org.slevin.dao.PersonCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="personCategoryMB")
@ViewScoped
public class PersonCategoryMB extends BaseMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private PersonCategoryDao itemDao;
	

	private List<PersonCategory> itemList;
	private String name;
	private PersonCategory item = new PersonCategory();
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }

	public void create() throws Exception{
		item = new PersonCategory();
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

	public PersonCategoryDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(PersonCategoryDao itemDao) {
		this.itemDao = itemDao;
	}

	public List<PersonCategory> getItemList() {
		return itemList;
	}

	public void setItemList(List<PersonCategory> itemList) {
		this.itemList = itemList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonCategory getItem() {
		return item;
	}

	public void setItem(PersonCategory item) {
		this.item = item;
	}

	

	


	
	
	
	
}
