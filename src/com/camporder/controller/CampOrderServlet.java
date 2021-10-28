	package com.camporder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camporder.model.CampOrderDAO;
import com.camporder.model.CampOrderService;
import com.camporder.model.CampOrderVO;
import com.campsitetentstatus.model.CampsiteTentStatusService;
import com.customerplan.model.CustomerPlanVO;

@WebServlet("/campOrder.do")
public class CampOrderServlet extends HttpServlet {
	public static boolean isWithinRange(Date date, Date startDate, Date endDate) {
		return !(date.before(startDate) || date.after(endDate));
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String url = "/campsite/reserve_campsite.jsp";
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// �إ߭q��򥻸��
		if ("book".equals(action)) {
			try {
				// �J����
				String from = req.getParameter("from");
				java.sql.Date checkedIn = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsed = dateFormat.parse(from);
					checkedIn = new java.sql.Date(parsed.getTime());
				} catch (Exception e) {
					throw new Exception(e);
				}
				// �h����
				String to = req.getParameter("to");
				java.sql.Date checkedOut = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsed = dateFormat.parse(to);
					checkedOut = new java.sql.Date(parsed.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					throw new Exception(e);
				}
				// �`�H��
				Integer headCount = new Integer(req.getParameter("headCount"));

				// �`����
				Integer price = new Integer(req.getParameter("price"));
				// �|��ID
				Integer memberId = new Integer(req.getParameter("memberId"));
				// ��aID
				Integer campId = new Integer(req.getParameter("campId"));
				// �U�w�ɶ�

				String today = req.getParameter("orderDate");
				Timestamp orderDate = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date parsedDate = dateFormat.parse(today);
					orderDate = new java.sql.Timestamp(parsedDate.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					throw new Exception(e);
				}
				// �I�ڴ���
				String deadline = req.getParameter("deadline");
				Timestamp expired = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date parsedDate = dateFormat.parse(deadline);
					expired = new java.sql.Timestamp(parsedDate.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					throw new Exception(e);
				}
				//�ˬd�w�q�����Ѿl�Ŧ�
				CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
				try {
					if (!CTSSvc.isTentAvailiblewithGuestNumberandTimeRange(campId, headCount, checkedIn, checkedOut)) {
						req.setAttribute("noSpace", "您所預定的區間剩餘空位不足");
						RequestDispatcher failedView = req.getRequestDispatcher(url);
						failedView.forward(req, res);
						return;
					}
				} catch (ParseException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CampOrderService COSvc = new CampOrderService();
				// �ˬd�P�϶��P�|�������ƭq��
				List<CampOrderVO> orderList = COSvc.getByMemberId(memberId);
				for (CampOrderVO order : orderList) {
					java.sql.Date confirmDate = checkedIn;
					while (confirmDate.compareTo(checkedOut) <= 0) {
						Date usedCheckedInday = order.getCheckInDate();
						Date usedCheckedOutday = order.getCheckOutDate();
						if (isWithinRange(checkedIn, usedCheckedInday, usedCheckedOutday)) {
							RequestDispatcher repeat = req.getRequestDispatcher(url);
							req.setAttribute("repeat", "您在此段時間已有訂單");
							repeat.forward(req, res);
							return;
						}
						Calendar c = Calendar.getInstance();
						c.setTime(confirmDate);
						c.add(Calendar.DATE, 1);
						confirmDate = new java.sql.Date(c.getTimeInMillis());
					}
				}
				// �N�H�W�ݩʷs�W�ܷs����a�q�檫��
				CampOrderVO campOrderVO = new CampOrderVO();
				campOrderVO.setCampId(campId);
				campOrderVO.setGuestNumber(headCount);
				campOrderVO.setMemberId(memberId);
				campOrderVO.setCheckInDate(checkedIn);
				campOrderVO.setCheckOutDate(checkedOut);
				campOrderVO.setOrderDate(orderDate);
				campOrderVO.setPaymentDeadline(expired);
				campOrderVO.setOrderTotal(price);
				HttpSession session = req.getSession();
				session.setAttribute("campOrderVO", campOrderVO);
				res.sendRedirect("bookings/extra_flavour.jsp");
			} catch (Exception e) {
				req.setAttribute("missing", "請輸入所有欄位");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} 

		}

		// �إ߭q��
		if ("createOrder".equals(action)) {
			Integer finalPrice = new Integer(req.getParameter("finalPrice"));
			HttpSession session = req.getSession();
			CampOrderVO campOrderVO = (CampOrderVO) session.getAttribute("campOrderVO");
			List<CustomerPlanVO> list = (List<CustomerPlanVO>) session.getAttribute("planList");

			// �إ߭q��M�U�Ȥ��
			campOrderVO.setOrderTotal(finalPrice);
			CampOrderDAO dao = new CampOrderDAO();
			try {
				dao.insertWithPlans(campOrderVO, list);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
