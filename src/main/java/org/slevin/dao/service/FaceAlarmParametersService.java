package org.slevin.dao.service;


import org.slevin.common.AlarmHistory;
import org.slevin.common.EnrollPerson;
import org.slevin.common.FaceAlarmParameters;
import org.slevin.dao.AlarmHistoryDao;
import org.slevin.dao.EnrollPersonDao;
import org.slevin.dao.FaceAlarmParametersDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class FaceAlarmParametersService extends EntityService<FaceAlarmParameters> implements FaceAlarmParametersDao {

	
}

