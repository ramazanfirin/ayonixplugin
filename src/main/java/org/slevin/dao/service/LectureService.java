package org.slevin.dao.service;


import org.slevin.common.Lecture;
import org.slevin.dao.LectureDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class LectureService extends EntityService<Lecture> implements LectureDao {

	
}

