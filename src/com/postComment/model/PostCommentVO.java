package com.postComment.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostCommentVO implements Serializable {
	private Integer commentId;
	private Integer articleId;
	private Integer memberId;
	private String content;
	private Timestamp created;
	
	public Integer getCommentId() {
		return commentId;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
