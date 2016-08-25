package org.slevin.dao.service;


import org.slevin.common.AlarmHistory;
import org.slevin.common.EnrollPerson;
import org.slevin.dao.AlarmHistoryDao;
import org.slevin.dao.EnrollPersonDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class EnrollPersonService extends EntityService<EnrollPerson> implements EnrollPersonDao {

	
}

