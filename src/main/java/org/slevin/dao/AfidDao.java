package org.slevin.dao;

import java.util.List;

import org.slevin.common.v2.Afid;


public interface AfidDao extends EntityDao<Afid> {

	void removeAfid(Long id) throws Exception;
	
	List<byte[]> loadAfidData() throws Exception;
}
