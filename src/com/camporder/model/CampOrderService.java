package com.camporder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class CampOrderService {

	private CampOrderDAO_interface dao;

	public CampOrderService() {
		dao = new CampOrderDAO();
	}

	public CampOrderVO addCampOrder(Integer campId, Integer memberId, Integer guestNumber, Date checkInDate,
			Date checkOutDate, Timestamp orderDate, Timestamp paymentDeadline, String orderStatus, Integer orderTotal,
			String comment, byte[] picture1, byte[] picture2, byte[] picture3) {
		
		CampOrderVO campOrderVO = new CampOrderVO();
		
		campOrderVO.setCampId(campId);
		campOrderVO.setMemberId(memberId);
		campOrderVO.setGuestNumber(guestNumber);
		campOrderVO.setCheckInDate(checkInDate);
		campOrderVO.setCheckOutDate(checkOutDate);
		campOrderVO.setOrderDate(orderDate);
		campOrderVO.setPaymentDeadline(paymentDeadline);
		campOrderVO.setOrderStatus(orderStatus);
		campOrderVO.setOrderTotal(orderTotal);
		campOrderVO.setComment(comment);
		campOrderVO.setPicture1(picture1);
		campOrderVO.setPicture2(picture2);
		campOrderVO.setPicture3(picture3);
		dao.add(campOrderVO);
		
		return campOrderVO;
	}
	
	public CampOrderVO updateCampOrder(Integer memberId, Integer guestNumber, Date checkInDate,
			Date checkOutDate, Timestamp orderDate, Timestamp paymentDeadline, String orderStatus, Integer orderTotal,
			String comment, byte[] picture1, byte[] picture2, byte[] picture3, Integer campOrderId) {
		
		CampOrderVO campOrderVO = new CampOrderVO();
		
		campOrderVO.setMemberId(memberId);
		campOrderVO.setGuestNumber(guestNumber);
		campOrderVO.setCheckInDate(checkInDate);
		campOrderVO.setCheckOutDate(checkOutDate);
		campOrderVO.setOrderDate(orderDate);
		campOrderVO.setPaymentDeadline(paymentDeadline);
		campOrderVO.setOrderStatus(orderStatus);
		campOrderVO.setOrderTotal(orderTotal);
		campOrderVO.setComment(comment);
		campOrderVO.setPicture1(picture1);
		campOrderVO.setPicture2(picture2);
		campOrderVO.setPicture3(picture3);
		campOrderVO.setCampOrderId(campOrderId);
		dao.update(campOrderVO);
		
		return campOrderVO;
	}
	
	public void deleteCampOrder(Integer campOrderId) {
		dao.delete(campOrderId);
	}
	
	public CampOrderVO getOneCampOrder(Integer campOrderId) {
		return dao.findbyPrimaryKey(campOrderId);
	}
	
	public List<CampOrderVO> getAll() {
		return dao.getAll();
	}
	public CampOrderVO updateOrder(Integer campId,Integer memberId, Integer guestNumber, Date checkInDate,
			Date checkOutDate, Timestamp orderDate, Timestamp paymentDeadline, String orderStatus, Integer orderTotal,
			Integer campOrderId) {
		
		CampOrderVO campOrderVO = new CampOrderVO();
		campOrderVO.setCampId(campId);
		campOrderVO.setMemberId(memberId);
		campOrderVO.setGuestNumber(guestNumber);
		campOrderVO.setCheckInDate(checkInDate);
		campOrderVO.setCheckOutDate(checkOutDate);
		campOrderVO.setOrderDate(orderDate);
		campOrderVO.setPaymentDeadline(paymentDeadline);
		campOrderVO.setOrderStatus(orderStatus);
		campOrderVO.setOrderTotal(orderTotal);
		campOrderVO.setCampOrderId(campOrderId);
		dao.updateOrder(campOrderVO);
		
		return campOrderVO;
	}
		
	
}