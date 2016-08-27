package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.Clazz;
import org.slevin.common.Teacher;
import org.slevin.dao.ClazzDao;
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
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
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
