package org.slevin.dto;

import org.slevin.common.v2.IpCamera;

public class AlarmDto {
	private byte[] afid;
	private byte[] image;
	private float score;
	private IpCamera item;
	public byte[] getAfid() {
		return afid;
	}
	public void setAfid(byte[] afid) {
		this.afid = afid;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public IpCamera getItem() {
		return item;
	}
	public void setItem(IpCamera item) {
		this.item = item;
	}
	
	
}
