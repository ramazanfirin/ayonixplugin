package org.slevin.dao.service;


import org.slevin.common.v2.Teacher;
import org.slevin.dao.TeacherDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class TeacherService extends EntityService<Teacher> implements TeacherDao {

	
}

