package com.post.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

public class PostVO implements Serializable{
	private Integer articleId;
	private Integer authorId;
	private String article;
	private Timestamp created;
	private byte[] pic1;
	private byte[] pic2;
	private byte[] pic3;
	
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public byte[] getPic1() {
		return pic1;
	}
	public void setPic1(byte[] pic1) {
		this.pic1 = pic1;
	}
	public byte[] getPic2() {
		return pic2;
	}
	public void setPic2(byte[] pic2) {
		this.pic2 = pic2;
	}
	public byte[] getPic3() {
		return pic3;
	}
	public void setPic3(byte[] pic3) {
		this.pic3 = pic3;
	}
	
}
