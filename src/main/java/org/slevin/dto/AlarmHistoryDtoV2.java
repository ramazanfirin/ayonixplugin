package org.slevin.dto;

import java.util.Date;

public class AlarmHistoryDtoV2 {
	private Long id;
	private Long userId;
	private String name;
	private Date date;
	private String location;
	private Long score;
	private String gender;
	private Long alarmParametersId;
	private String category;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getAlarmParametersId() {
		return alarmParametersId;
	}
	public void setAlarmParametersId(Long alarmParametersId) {
		this.alarmParametersId = alarmParametersId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
