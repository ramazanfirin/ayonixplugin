package org.slevin.dao.service;


import org.slevin.common.v2.Clazz;
import org.slevin.dao.ClazzDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class ClazzService extends EntityService<Clazz> implements ClazzDao {

	
}

