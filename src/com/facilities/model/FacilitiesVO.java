package com.facilities.model;

import java.io.Serializable;

public class FacilitiesVO implements Serializable{
	
	private Integer facilitiesId;
	private Integer campId;
	private byte[] facilities;
	private String base64Image;
	
	public Integer getFacilitiesId() {
		return facilitiesId;
	}
	public void setFacilitiesId(Integer facilitiesId) {
		this.facilitiesId = facilitiesId;
	}
	public Integer getCampId() {
		return campId;
	}
	public void setCampId(Integer campId) {
		this.campId = campId;
	}
	public byte[] getFacilities() {
		return facilities;
	}
	public void setFacilities(byte[] facilities) {
		this.facilities = facilities;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
}
