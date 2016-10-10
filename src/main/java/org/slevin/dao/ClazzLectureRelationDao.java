package org.slevin.dao;

import java.util.Date;
import java.util.List;

import org.slevin.common.v2.ClazzLectureRelation;


public interface ClazzLectureRelationDao extends EntityDao<ClazzLectureRelation> {
	public ClazzLectureRelation findNearest(Date date,String clazzDate);
	public List<ClazzLectureRelation> find(Date date,String clazzName);
	public ClazzLectureRelation findNearest(Date date,Long classId);
}
