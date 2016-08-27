package org.slevin.dao;

import java.util.List;

import org.slevin.common.AlarmHistory;
import org.slevin.dto.AlarmHistoryDto;
import org.slevin.dto.AlarmHistoryDtoV2;


public interface AlarmHistoryDao extends EntityDao<AlarmHistory> {
	
	List<AlarmHistoryDto> findAlarmHistory() throws Exception;
	List<AlarmHistory> findAllOrderByDate() throws Exception;
}
