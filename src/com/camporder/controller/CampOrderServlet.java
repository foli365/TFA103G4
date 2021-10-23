	package com.camporder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camporder.model.CampOrderVO;

@WebServlet("/campOrder.do")
public class CampOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("book".equals(action)) {
			try {
				//�J����
				String from = req.getParameter("from");
				java.sql.Date checkedIn = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsed = dateFormat.parse(from);
				    checkedIn = new java.sql.Date(parsed.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				//�h����
				String to = req.getParameter("to");
				java.sql.Date checkedOut = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsed = dateFormat.parse(to);
				    checkedOut = new java.sql.Date(parsed.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				//�`�H��
				Integer headCount = new Integer(req.getParameter("headCount"));
				//�`����
				Integer price = new Integer(req.getParameter("price"));
				//�|��ID
				Integer memberId = new Integer(req.getParameter("memberId"));
				//��aID
				Integer campId = new Integer(req.getParameter("campId"));
				//�U�w�ɶ�
				String today = req.getParameter("orderDate");
				Timestamp orderDate = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date parsedDate = dateFormat.parse(today);
					orderDate = new java.sql.Timestamp(parsedDate.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				//�I�ڴ���
				String deadline = req.getParameter("deadline");
				Timestamp expired = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				    Date parsedDate = dateFormat.parse(deadline);
				    expired = new java.sql.Timestamp(parsedDate.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				//�N�H�W�ݩʷs�W�ܷs����a�q�檫��
				CampOrderVO campOrderVO = new CampOrderVO();
				campOrderVO.setCampId(campId);
				campOrderVO.setGuestNumber(headCount);
				campOrderVO.setMemberId(memberId);
				campOrderVO.setCheckInDate(checkedIn);
				campOrderVO.setCheckOutDate(checkedOut);
				campOrderVO.setOrderDate(orderDate);
				campOrderVO.setPaymentDeadline(expired);
				campOrderVO.setOrderTotal(price);
				req.setAttribute("campOrderVO", campOrderVO);
				String url = "/bookings/extra_flavour.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (NumberFormatException NFE) {
				req.setAttribute("missing", "�Х���g�Ҧ�����A�w�w");
				String url = "/campsite/camp_shopping_cart.jsp";
				RequestDispatcher failedView = req.getRequestDispatcher(url);
				failedView.forward(req, res);
			}
			
		}
		
		
	}
}
