package org.slevin.common.v2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Alarm")
public class Alarm {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	Person person;
	
	@ManyToOne
	IpCamera ipCamera;

	@Column
	Date insertDate;
	
	@Column
	Float score;
	
	@Column
	String alarmImage;
	
	@ManyToOne
	ClazzLectureRelation clazzLectureRelation;
	
	@Column
	String className;
	
	@Column
	String lecture;
	
	@Column
	String teacher;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public IpCamera getIpCamera() {
		return ipCamera;
	}

	public void setIpCamera(IpCamera ipCamera) {
		this.ipCamera = ipCamera;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getAlarmImage() {
		return alarmImage;
	}

	public void setAlarmImage(String alarmImage) {
		this.alarmImage = alarmImage;
	}

	public ClazzLectureRelation getClazzLectureRelation() {
		return clazzLectureRelation;
	}

	public void setClazzLectureRelation(ClazzLectureRelation clazzLectureRelation) {
		this.clazzLectureRelation = clazzLectureRelation;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
}
