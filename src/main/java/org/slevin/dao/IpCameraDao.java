package org.slevin.dao;

import org.slevin.common.v2.IpCamera;


public interface IpCameraDao extends EntityDao<IpCamera> {
	public void updateStatus(Long id,String status);
}
