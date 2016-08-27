package org.slevin.dao.service;


import org.slevin.common.ClazzLectureRelation;
import org.slevin.dao.ClazzLectureRelationDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class ClazzLectureRelationService extends EntityService<ClazzLectureRelation> implements ClazzLectureRelationDao {

	
}

