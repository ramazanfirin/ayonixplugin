package org.slevin.tests;

import java.io.File;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slevin.common.v2.Afid;
import org.slevin.common.v2.ClazzLectureRelation;
import org.slevin.common.v2.Person;
import org.slevin.dao.AfidDao;
import org.slevin.dao.ClazzLectureRelationDao;
import org.slevin.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class persistenceTests {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Autowired
    PersonDao personDao;
	
	@Autowired
    AfidDao afidDao;
	
	
	@Autowired
	ClazzLectureRelationDao clazzLectureRelationDao;
	
	
//	@Test
//	@Transactional
//	@Rollback(false)
	public void testDaoImpl() throws Exception {
		Person person = new Person();
		person.setName("ramazan");
		
		
		Afid afid = new Afid();
		afid.setAfid("merbaba".getBytes());
		person.getAfidList().add(afid);
		
		personDao.persist(person);
		//assertEquals(personDao.findAll().size(), 1);
	}
	

//	@Test
//	@Transactional
	//@Rollback(false)
	public void afidTest() throws Exception{
		Afid afid = afidDao.findById(new Long(1));
		System.out.println("name is="+afid.getPerson().getName());
	}
	
	//@Test
	public void directoryTest(){
		File file  = new File("C://smartSchool//13");
		System.out.println(file.mkdir());
	}
	
	@Test
	@Rollback(false)
	public void deleteAfid() throws Exception{
		ClazzLectureRelation clazzLectureRelation = clazzLectureRelationDao.findNearest(new Date(), new Long(1));
		System.out.println(clazzLectureRelation);
	}
}
