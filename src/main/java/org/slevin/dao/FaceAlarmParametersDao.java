package org.slevin.dao;

import java.util.Date;
import java.util.List;

import org.slevin.common.AlarmHistory;
import org.slevin.common.EnrollPerson;
import org.slevin.common.FaceAlarmParameters;


public interface FaceAlarmParametersDao extends EntityDao<FaceAlarmParameters> {
	public FaceAlarmParameters findByAlarmId(Long alarmId) throws Exception;
	public List<FaceAlarmParameters> find(Date date,String clazzName);
}
