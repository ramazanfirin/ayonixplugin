package org.slevin.dao.service;


import org.slevin.common.Teacher;
import org.slevin.dao.TeacherDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class TeacherService extends EntityService<Teacher> implements TeacherDao {

	
}

