package org.slevin.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name="EnrollPerson")

public class EnrollPerson {

	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "AGE")
	private Long age;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "LASTACTIVITYDATE")
	private Date lastActivityDate;
	
	@Column(name = "ENROLLMENTDATE")
	private Date enrollmentDate;
	
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "MAINIMAGEPOSE")
	private String mainImagePose;
	
	@Column(name = "ALARM_SCREEN")
	private Boolean alarmScreen;
	
	@Column(name = "ALARM_EMAIL")
	private Boolean alarmEmail;
	
	@Column(name = "ALARM_EMAIL_VALUE")
	private String alarmEmailValue;
	
	@Column(name = "ALARM_COMMAND")
	private Boolean alarmCommand;
	
	@Column(name = "ALARM_COMMAND_VALUE")
	private String alarmCommandValue;
	
	@Column(name = "CATEGORY")
	private Long category;
	
	@Column(name = "ALARM_SOUND")
	private Boolean alarmSound;
	
	@Column(name = "ALARM_SOUND_TYPE")
	private String alarmSoundValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMainImagePose() {
		return mainImagePose;
	}

	public void setMainImagePose(String mainImagePose) {
		this.mainImagePose = mainImagePose;
	}

	public Boolean getAlarmScreen() {
		return alarmScreen;
	}

	public void setAlarmScreen(Boolean alarmScreen) {
		this.alarmScreen = alarmScreen;
	}

	public Boolean getAlarmEmail() {
		return alarmEmail;
	}

	public void setAlarmEmail(Boolean alarmEmail) {
		this.alarmEmail = alarmEmail;
	}

	public String getAlarmEmailValue() {
		return alarmEmailValue;
	}

	public void setAlarmEmailValue(String alarmEmailValue) {
		this.alarmEmailValue = alarmEmailValue;
	}

	public Boolean getAlarmCommand() {
		return alarmCommand;
	}

	public void setAlarmCommand(Boolean alarmCommand) {
		this.alarmCommand = alarmCommand;
	}

	public String getAlarmCommandValue() {
		return alarmCommandValue;
	}

	public void setAlarmCommandValue(String alarmCommandValue) {
		this.alarmCommandValue = alarmCommandValue;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Boolean getAlarmSound() {
		return alarmSound;
	}

	public void setAlarmSound(Boolean alarmSound) {
		this.alarmSound = alarmSound;
	}

	public String getAlarmSoundValue() {
		return alarmSoundValue;
	}

	public void setAlarmSoundValue(String alarmSoundValue) {
		this.alarmSoundValue = alarmSoundValue;
	}
	
	
	

}
