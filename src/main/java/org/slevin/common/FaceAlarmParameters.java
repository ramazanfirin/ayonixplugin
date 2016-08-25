package org.slevin.common;

import java.io.ByteArrayInputStream;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Immutable
@Entity
@Table(name="FaceAlarmParameters")

public class FaceAlarmParameters {

	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Lob
	@Column(name = "ALARM_IMAGE")
	private byte[] alarmImage;
	
	@Column(name = "ALARM_DATE")
	private Date alarmDate;
	
	@Column(name = "ALARM_ID")
	private Long alarmId;
	
	@Column(name = "ALARM_CAMERA")
	private String alarmCamera;
	
	@Column(name = "MATCH_SCORE")
	private String matchScore;
	
	@Column(name = "MATCH_ID")
	private String matchId;
	
	@Column(name = "AGE")
	private Long age;
	
	@Column(name = "MATCH_GENDER_VALUE")
	private Float matchGenderValue;
	
	@Transient
	StreamedContent  image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getAlarmImage() {
		return alarmImage;
	}

	public void setAlarmImage(byte[] alarmImage) {
		this.alarmImage = alarmImage;
	}

	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public Long getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Long alarmId) {
		this.alarmId = alarmId;
	}

	public String getAlarmCamera() {
		return alarmCamera;
	}

	public void setAlarmCamera(String alarmCamera) {
		this.alarmCamera = alarmCamera;
	}

	public String getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(String matchScore) {
		this.matchScore = matchScore;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Float getMatchGenderValue() {
		return matchGenderValue;
	}

	public void setMatchGenderValue(Float matchGenderValue) {
		this.matchGenderValue = matchGenderValue;
	}

	public StreamedContent getImage() {//alarmImage.length
		image = new DefaultStreamedContent(new ByteArrayInputStream(alarmImage),"image/jpg");
		return image;
	}

	public void setImage(DefaultStreamedContent image) {
		this.image = image;
	}
	

	

}
