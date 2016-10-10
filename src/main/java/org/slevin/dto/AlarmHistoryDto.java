package org.slevin.dto;

import org.slevin.common.v2.AlarmHistory;
import org.slevin.common.v2.Category;
import org.slevin.common.v2.ClazzLectureRelation;
import org.slevin.common.v2.EnrollPerson;
import org.slevin.common.v2.FaceAlarmParameters;
import org.slevin.common.v2.IpCamera;

public class AlarmHistoryDto {
	AlarmHistory alarmHistory;
	FaceAlarmParameters faceAlarmParameters;
	EnrollPerson enrollPerson;
	Category category;
	IpCamera ipCamera;
	ClazzLectureRelation clazzLectureRelation;
	
	public AlarmHistory getAlarmHistory() {
		return alarmHistory;
	}
	public void setAlarmHistory(AlarmHistory alarmHistory) {
		this.alarmHistory = alarmHistory;
	}
	public FaceAlarmParameters getFaceAlarmParameters() {
		return faceAlarmParameters;
	}
	public void setFaceAlarmParameters(FaceAlarmParameters faceAlarmParameters) {
		this.faceAlarmParameters = faceAlarmParameters;
	}
	public EnrollPerson getEnrollPerson() {
		return enrollPerson;
	}
	public void setEnrollPerson(EnrollPerson enrollPerson) {
		this.enrollPerson = enrollPerson;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public IpCamera getIpCamera() {
		return ipCamera;
	}
	public void setIpCamera(IpCamera ipCamera) {
		this.ipCamera = ipCamera;
	}
	public ClazzLectureRelation getClazzLectureRelation() {
		return clazzLectureRelation;
	}
	public void setClazzLectureRelation(ClazzLectureRelation clazzLectureRelation) {
		this.clazzLectureRelation = clazzLectureRelation;
	}
	
}
