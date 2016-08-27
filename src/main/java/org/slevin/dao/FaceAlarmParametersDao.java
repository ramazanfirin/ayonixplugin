package org.slevin.dao;

import org.slevin.common.EnrollPerson;
import org.slevin.common.FaceAlarmParameters;


public interface FaceAlarmParametersDao extends EntityDao<FaceAlarmParameters> {
	public FaceAlarmParameters findByAlarmId(Long alarmId) throws Exception;
}
