package org.slevin.dao.service;


import org.slevin.common.AlarmHistory;
import org.slevin.dao.AlarmHistoryDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class AlarmHistoryService extends EntityService<AlarmHistory> implements AlarmHistoryDao {

	
}

