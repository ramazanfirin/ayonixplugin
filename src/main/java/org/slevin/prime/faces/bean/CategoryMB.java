package org.slevin.prime.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.slevin.common.v2.Category;
import org.slevin.common.v2.IpCamera;
import org.slevin.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="categoryMB")
@ViewScoped
public class CategoryMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private CategoryDao categoryDao;
	
	
	private List<Category> categoryList;
	
	@PostConstruct
    public void init() throws Exception {
		refreshList();
    }



	public void refreshList() throws Exception {
		categoryList = categoryDao.findAll();
		System.out.println("alarm size="+categoryList.size());
		
	}



	public List<Category> getCategoryList() {
		return categoryList;
	}



	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}



	
	
	
	
}
