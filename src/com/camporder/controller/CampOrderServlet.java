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
				//入住日期
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
				//退住日期
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
				//總人數
				Integer headCount = new Integer(req.getParameter("headCount"));
				//總價錢
				Integer price = new Integer(req.getParameter("price"));
				//會員ID
				Integer memberId = new Integer(req.getParameter("memberId"));
				//營地ID
				Integer campId = new Integer(req.getParameter("campId"));
				//下定時間
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
				//付款期限
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
				//將以上屬性新增至新的營地訂單物件
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
				req.setAttribute("missing", "請先填寫所有欄位後再預定");
				String url = "/campsite/camp_shopping_cart.jsp";
				RequestDispatcher failedView = req.getRequestDispatcher(url);
				failedView.forward(req, res);
			}
			
		}
		
		
	}
}
