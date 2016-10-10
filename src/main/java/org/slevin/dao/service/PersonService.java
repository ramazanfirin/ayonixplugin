package org.slevin.dao.service;


import org.slevin.common.v2.Person;
import org.slevin.dao.PersonDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class PersonService extends EntityService<Person> implements PersonDao {

	
}

