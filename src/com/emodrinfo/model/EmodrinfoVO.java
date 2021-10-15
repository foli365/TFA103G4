package com.emodrinfo.model;

public class EmodrinfoVO implements java.io.Serializable {
	private Integer emodr_infoid;
	private Integer emodr_id;
	private Integer product_no;
	private Integer qty;
	private Integer price;
	private String comm;

	public EmodrinfoVO() {
		super();
	}

	public Integer getEmodr_infoid() {
		return emodr_infoid;
	}

	public void setEmodr_infoid(Integer emodr_infoid) {
		this.emodr_infoid = emodr_infoid;
	}

	public Integer getEmodr_id() {
		return emodr_id;
	}

	public void setEmodr_id(Integer emodr_id) {
		this.emodr_id = emodr_id;
	}

	public Integer getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

}
