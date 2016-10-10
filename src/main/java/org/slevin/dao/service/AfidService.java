package org.slevin.dao.service;


import java.util.List;

import org.slevin.common.v2.Afid;
import org.slevin.dao.AfidDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class AfidService extends EntityService<Afid> implements AfidDao {
	
	@Override
	public void removeAfid(Long id) throws Exception{
		Afid afid = findById(id);
		afid.setPerson(null);
		getEntityManager().remove(afid);
	}

	@Override
	public List<byte[]> loadAfidData() throws Exception {
		 return getEntityManager().createQuery("Select t.afid from " + getEntityClass().getSimpleName() + " t").getResultList();
	}

	

	
}

