package com.campplan.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plan.model.*;

@MultipartConfig
public class CampPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("getOnePlan_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String str = req.getParameter("planId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入配套編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				Integer planId = null;
				try {
					planId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("配套編號格式有錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				PlanService planSvc = new PlanService();
				PlanVO planVO = planSvc.getOnePlan(planId);
				if (planVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("planVO", planVO);

				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOnePlan_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer planId = new Integer(req.getParameter("planId"));

				PlanService planSvc = new PlanService();
				PlanVO planVO = planSvc.getOnePlan(planId);

				req.setAttribute("planVO", planVO);
				String url = "/camprelease/updatePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listPlan.jsp");
				failureView.forward(req, res);
			}
		}

		if ("plan_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer planId = new Integer(req.getParameter("planId").trim());

				Integer campId = new Integer(req.getParameter("campId").trim());

				String planName = req.getParameter("planName");
				String planNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (planName == null || planName.trim().length() == 0) {
					errorMsgs.add("名稱: 請勿空白");
				} else if (!planName.trim().matches(planNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer planGuestLimit = null;
				try {
					planGuestLimit = new Integer(req.getParameter("planGuestLimit").trim());
				} catch (NumberFormatException e) {
					planGuestLimit = 0;
					errorMsgs.add("請填數字.");
				}

				Integer planAgeLimit = null;
				try {
					planAgeLimit = new Integer(req.getParameter("planAgeLimit").trim());
				} catch (NumberFormatException e) {
					planAgeLimit = 0;
					errorMsgs.add("請填數字.");
				}

				Integer planPrice = null;
				try {
					planPrice = new Integer(req.getParameter("planPrice").trim());
				} catch (NumberFormatException e) {
					planPrice = 0;
					errorMsgs.add("價錢請填數字");
				}

//				String planDescription = req.getParameter("planDescription").trim();
//				if (planDescription == null || planDescription.trim().length() == 0) {
//					errorMsgs.add("介紹請填寫");
//				} 

				PlanVO planVO = new PlanVO();
				planVO.setPlanId(planId);
				planVO.setCampId(campId);
				planVO.setPlanName(planName);
				planVO.setPlanGuestLimit(planGuestLimit);
				planVO.setPlanAgeLimit(planAgeLimit);
				planVO.setPlanPrice(planPrice);
//				planVO.setPlanDescription(planDescription);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("planVO", planVO);

					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updatePlan.jsp");
					failureView.forward(req, res);
					return;
				}

				PlanService planSvc = new PlanService();
				planVO = planSvc.updatePlan(campId, planName, planGuestLimit, planAgeLimit, planPrice, planId);

				req.setAttribute("planVO", planVO);
				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗");
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updatePlan.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete_plan".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				
				Integer planId = new Integer(req.getParameter("planId"));
				
				
				PlanService planSvc = new PlanService();
				planSvc.deletePlan(planId);
					
				String url = "/camprelease/listPlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/camprelease/listPlan.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
