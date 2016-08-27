package org.slevin.dao.service;


import java.util.List;

import org.slevin.common.FaceAlarmParameters;
import org.slevin.dao.FaceAlarmParametersDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class FaceAlarmParametersService extends EntityService<FaceAlarmParameters> implements FaceAlarmParametersDao {

	public FaceAlarmParameters findByAlarmId(Long alarmId) throws Exception {
		List<FaceAlarmParameters> list=findByProperty("alarmId", alarmId);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}

	
}

