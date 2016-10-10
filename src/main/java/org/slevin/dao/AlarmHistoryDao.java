package org.slevin.dao;

import java.util.Date;
import java.util.List;

import org.slevin.common.v2.AlarmHistory;
import org.slevin.common.v2.Clazz;
import org.slevin.common.v2.ClazzLectureRelation;
import org.slevin.dto.AlarmHistoryDto;


public interface AlarmHistoryDao extends EntityDao<AlarmHistory> {
	
	List<AlarmHistoryDto> findAlarmHistory(Date date,Date endDate,String clazzName) throws Exception;
	List<AlarmHistory> findAllOrderByDate() throws Exception;
	public List<AlarmHistory> find(Date date,Date endDate,String clazzName) throws Exception;
	

}
