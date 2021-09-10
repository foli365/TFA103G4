package com.followed.model;

import java.io.Serializable;

public class FollowedVO implements Serializable{
	private Integer followedMappigID;
	private Integer memberId;
	private Integer followedId;
	
	public Integer getFollowedMappigID() {
		return followedMappigID;
	}
	public void setFollowedMappigID(Integer followedMappigID) {
		this.followedMappigID = followedMappigID;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getFollowedId() {
		return followedId;
	}
	public void setFollowedId(Integer followedId) {
		this.followedId = followedId;
	}
}
