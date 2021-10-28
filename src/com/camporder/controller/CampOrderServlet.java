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
		// ï¿½Ø¥ß­qï¿½ï¿½ò¥»¸ï¿½ï¿½
		if ("book".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// ï¿½Jï¿½ï¿½ï¿½ï¿½
				String from = req.getParameter("from");
				java.sql.Date checkedIn = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsed = dateFormat.parse(from);
					checkedIn = new java.sql.Date(parsed.getTime());
				} catch (Exception e) {
					throw new Exception(e);
				}
				// ï¿½hï¿½ï¿½ï¿½ï¿½
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
<<<<<<< HEAD
				// Á`¤H¼Æ
				String strHeadCount = req.getParameter("headCount");

				Integer headCount = null;
				try {
					headCount = new Integer(strHeadCount);
				} catch (Exception e) {
					errorMsgs.add("¤H¼Æ®æ¦¡¤£¥¿½T");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// µ{¦¡¤¤Â_
				}
				
				// Á`»ù¿ú
=======
				// ï¿½`ï¿½Hï¿½ï¿½
				Integer headCount = new Integer(req.getParameter("headCount"));

				// ï¿½`ï¿½ï¿½ï¿½ï¿½
>>>>>>> ab8d8a50a6cc87afa7f38fd7276d321d31b87625
				Integer price = new Integer(req.getParameter("price"));
				// ï¿½|ï¿½ï¿½ID
				Integer memberId = new Integer(req.getParameter("memberId"));
				// ï¿½ï¿½aID
				Integer campId = new Integer(req.getParameter("campId"));
				// ï¿½Uï¿½wï¿½É¶ï¿½

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
				// ï¿½Iï¿½Ú´ï¿½ï¿½ï¿½
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
<<<<<<< HEAD
				
				//ÀË¬d¹w­q´Á¶¡³Ñ¾lªÅ¦ì
=======
				//ï¿½Ë¬dï¿½wï¿½qï¿½ï¿½ï¿½ï¿½ï¿½Ñ¾lï¿½Å¦ï¿½
>>>>>>> ab8d8a50a6cc87afa7f38fd7276d321d31b87625
				CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
				try {
					if (!CTSSvc.isTentAvailiblewithGuestNumberandTimeRange(campId, headCount, checkedIn, checkedOut)) {
						req.setAttribute("noSpace", "æ‚¨æ‰€é å®šçš„å€é–“å‰©é¤˜ç©ºä½ä¸è¶³");
						RequestDispatcher failedView = req.getRequestDispatcher(url);
						failedView.forward(req, res);
						return;
					}
				} catch (ParseException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CampOrderService COSvc = new CampOrderService();
				// ï¿½Ë¬dï¿½Pï¿½Ï¶ï¿½ï¿½Pï¿½|ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æ­qï¿½ï¿½
				List<CampOrderVO> orderList = COSvc.getByMemberId(memberId);
				for (CampOrderVO order : orderList) {
					java.sql.Date confirmDate = checkedIn;
					while (confirmDate.compareTo(checkedOut) <= 0) {
						Date usedCheckedInday = order.getCheckInDate();
						Date usedCheckedOutday = order.getCheckOutDate();
						if (isWithinRange(checkedIn, usedCheckedInday, usedCheckedOutday)) {
							RequestDispatcher repeat = req.getRequestDispatcher(url);
							req.setAttribute("repeat", "æ‚¨åœ¨æ­¤æ®µæ™‚é–“å·²æœ‰è¨‚å–®");
							repeat.forward(req, res);
							return;
						}
						Calendar c = Calendar.getInstance();
						c.setTime(confirmDate);
						c.add(Calendar.DATE, 1);
						confirmDate = new java.sql.Date(c.getTimeInMillis());
					}
				}
<<<<<<< HEAD
				
				// ±N¥H¤WÄİ©Ê·s¼W¦Ü·sªºÀç¦a­q³æª«¥ó
=======
				// ï¿½Nï¿½Hï¿½Wï¿½İ©Ê·sï¿½Wï¿½Ü·sï¿½ï¿½ï¿½ï¿½aï¿½qï¿½æª«ï¿½ï¿½
>>>>>>> ab8d8a50a6cc87afa7f38fd7276d321d31b87625
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
				req.setAttribute("missing", "è«‹è¼¸å…¥æ‰€æœ‰æ¬„ä½");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} 

		}

		// ï¿½Ø¥ß­qï¿½ï¿½
		if ("createOrder".equals(action)) {
			Integer finalPrice = new Integer(req.getParameter("finalPrice"));
			HttpSession session = req.getSession();
			CampOrderVO campOrderVO = (CampOrderVO) session.getAttribute("campOrderVO");
			List<CustomerPlanVO> list = (List<CustomerPlanVO>) session.getAttribute("planList");

			// ï¿½Ø¥ß­qï¿½ï¿½Mï¿½Uï¿½È¤ï¿½ï¿½
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
