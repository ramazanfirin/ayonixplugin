package org.slevin.dao.service;


import javax.persistence.Query;

import org.slevin.common.v2.IpCamera;
import org.slevin.dao.IpCameraDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class IpCameraService extends EntityService<IpCamera> implements IpCameraDao {

	@Override
	public void updateStatus(Long id, String status) {
		Query query = getEntityManager().createQuery("update IpCamera set status=:status where id=:id");
		query.setParameter("status", status);
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}

	
}

