package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.Category;
import org.slevin.common.Clazz;
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
	private ClazzDao clazzDao;
	

	private List<Clazz> clazzList;
	private String name;
	private Clazz clazz = new Clazz();
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }

	public void create() throws Exception{
		clazz = new Clazz();
		System.out.println("create");
	}
	
	public void merge() throws Exception{
		//clazz.setName(name);
		clazzDao.merge(clazz);
		refreshList();
		addMessage("Islem Tamamlandi");
	}
	
	public void delete() throws Exception{
		clazzDao.remove(clazz.getId());
		refreshList();
		addMessage("Islem Tamamlandi");
	}

	public void refreshList() throws Exception {
		clazzList = clazzDao.findAll();
		System.out.println("class size="+clazzList.size());
		
	}



	public List<Clazz> getClazzList() {
		return clazzList;
	}



	public void setClazzList(List<Clazz> clazzList) {
		this.clazzList = clazzList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}




	
	
	
	
}
