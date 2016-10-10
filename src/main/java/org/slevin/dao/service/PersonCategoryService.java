package org.slevin.dao.service;


import org.slevin.common.v2.PersonCategory;
import org.slevin.dao.PersonCategoryDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class PersonCategoryService extends EntityService<PersonCategory> implements PersonCategoryDao {

	
}

