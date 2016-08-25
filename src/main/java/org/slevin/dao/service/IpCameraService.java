package org.slevin.dao.service;


import org.slevin.common.IpCamera;
import org.slevin.dao.IpCameraDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class IpCameraService extends EntityService<IpCamera> implements IpCameraDao {

	
}

