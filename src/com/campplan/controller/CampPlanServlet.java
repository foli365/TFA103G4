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

				String str = req.getParameter("campId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�t�M�s��");
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
					errorMsgs.add("�t�M�s���榡�����~");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				PlanService planSvc = new PlanService();
				ArrayList<PlanVO> list = planSvc.getPlans(planId);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("list", list);

				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
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
				PlanVO planVO = planSvc.getOnePlanByPlanId(planId);

				req.setAttribute("planVO", planVO);
				String url = "/camprelease/updatePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listOnePlan.jsp");
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
					errorMsgs.add("�W��: �ФŪť�");
				} else if (!planName.trim().matches(planNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				Integer planGuestLimit = null;
				try {
					planGuestLimit = new Integer(req.getParameter("planGuestLimit").trim());
				} catch (NumberFormatException e) {
					planGuestLimit = 0;
					errorMsgs.add("�ж�Ʀr.");
				}

				Integer planAgeLimit = null;
				try {
					planAgeLimit = new Integer(req.getParameter("planAgeLimit").trim());
				} catch (NumberFormatException e) {
					planAgeLimit = 0;
					errorMsgs.add("�ж�Ʀr.");
				}

				Integer planPrice = null;
				try {
					planPrice = new Integer(req.getParameter("planPrice").trim());
				} catch (NumberFormatException e) {
					planPrice = 0;
					errorMsgs.add("�����ж�Ʀr");
				}

				String planOutline = req.getParameter("planOutline").trim();
				if (planOutline == null || planOutline.trim().length() == 0) {
					errorMsgs.add("���нж�g");
				} 

				PlanVO planVO = new PlanVO();
				planVO.setPlanId(planId);
				planVO.setCampId(campId);
				planVO.setPlanName(planName);
				planVO.setPlanGuestLimit(planGuestLimit);
				planVO.setPlanAgeLimit(planAgeLimit);
				planVO.setPlanPrice(planPrice);
				planVO.setPlanOutline(planOutline);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("planVO", planVO);

					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updatePlan.jsp");
					failureView.forward(req, res);
					return;
				}

				PlanService planSvc = new PlanService();
				planVO = planSvc.updatePlan(campId, planName, planGuestLimit, planAgeLimit, planPrice, planOutline, planId);
				PlanService ps = new PlanService();
				ArrayList<PlanVO> list = ps.getPlans(campId);
				req.setAttribute("list", list);
				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���");
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

				Integer campId = new Integer(req.getParameter("campId"));

				PlanService planSvc = new PlanService();
				planSvc.deletebyCampId(campId);

				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listOnePlan.jsp");
				failureView.forward(req, res);
			}
		}

		if ("go_to_addPlan".equals(action)) {

//			try {
				Integer campId = new Integer(req.getParameter("campId"));
				System.out.println(campId);
				req.setAttribute("campId", campId);
				String url = "/camprelease/addPlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:" + e.getMessage());
//				System.out.println("����");
//				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
//				failureView.forward(req, res);
			}

//		}

		if ("insert_plan".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
//				Integer planId = new Integer(req.getParameter("planId").trim());
				
				String planName = req.getParameter("planName");
				String planNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (planName == null || planName.trim().length() == 0) {
					errorMsgs.add("�W��: �ФŪť�");
				} else if(!planName.trim().matches(planNameReg)) { 
					errorMsgs.add("�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				Integer planGuestLimit = null;
				try {
					planGuestLimit = new Integer(req.getParameter("planGuestLimit").trim());
				} catch (NumberFormatException e) {
					planGuestLimit = 0;
					errorMsgs.add("�ж�Ʀr.");
				}

				Integer planAgeLimit = null;
				try {
					planAgeLimit = new Integer(req.getParameter("planAgeLimit").trim());
				} catch (NumberFormatException e) {
					planAgeLimit = 0;
					errorMsgs.add("�ж�Ʀr.");
				}

				Integer planPrice = null;
				try {
					planPrice = new Integer(req.getParameter("planPrice").trim());
				} catch (NumberFormatException e) {
					planPrice = 0;
					errorMsgs.add("�����ж�Ʀr");
				}

				String planOutline = req.getParameter("planOutline").trim();
				if (planOutline == null || planOutline.trim().length() == 0) {
					errorMsgs.add("���нж�g");
				}
				Integer campId = new Integer(req.getParameter("campId").trim());
				
				PlanVO planVO = new PlanVO();
//				planVO.setPlanId(planId);
				planVO.setCampId(campId);
				planVO.setPlanName(planName);
				planVO.setPlanGuestLimit(planGuestLimit);
				planVO.setPlanAgeLimit(planAgeLimit);
				planVO.setPlanPrice(planPrice);
				planVO.setPlanOutline(planOutline);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("planVO", planVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addPlan.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.�}�l�s�W��� ***************************************/
				PlanService planSvc = new PlanService();
				planVO = planSvc.addPlan(campId, planName, planGuestLimit, planAgeLimit, planPrice, planOutline);
				List<PlanVO> list = planSvc.getByCampId(campId);
				req.setAttribute("planVOList", list);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addPlan.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
