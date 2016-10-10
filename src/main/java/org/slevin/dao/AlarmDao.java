package org.slevin.dao;

import java.util.Date;
import java.util.List;

import org.slevin.common.v2.Alarm;
import org.slevin.common.v2.IpCamera;
import org.slevin.dto.AlarmDto;
import org.slevin.dto.AlarmHistoryDto;


public interface AlarmDao extends EntityDao<Alarm> {

	public void insertAlarm(byte[] afid,byte[] image,float score,IpCamera item) throws Exception;
	public void insertAlarmList(List<AlarmDto> alarms) throws Exception;
	
	public Boolean searchAlarm(String cameraName,Long personId,Date atartDate,Date endDate);
	
	public List<Alarm> findAlarmHistory(Date date,Date endDate, Long classId) throws Exception;
}

