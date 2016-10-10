package org.slevin.dao.service;


import org.slevin.common.v2.Category;
import org.slevin.dao.CategoryDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class CategoryService extends EntityService<Category> implements CategoryDao {

	
}

