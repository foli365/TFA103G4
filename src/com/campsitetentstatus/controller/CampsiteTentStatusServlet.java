package com.campsitetentstatus.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camprelease.model.CampReleaseService;
import com.camprelease.model.CampReleaseVO;
import com.campsite.model.CampsiteService;
import com.campsite.model.CampsiteVO;
import com.campsitetentstatus.model.CampsiteTentStatusService;
import com.campsitetentstatus.model.CampsiteTentStatusVO;

public class CampsiteTentStatusServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAllOfOne".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("campId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J��a�s��");
				}
				
//				java.sql.Date campOpeningTime = null;
//				try {
//					campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
//				} catch (IllegalArgumentException e) {
//					campOpeningTime = new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}

				Integer campId = null;
				try {
					campId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("��a�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}


				/*************************** 2.�}�l�d�߸�� *****************************************/				
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				List<CampsiteTentStatusVO> list = campsiteTentStatusSvc.getAllCampStatusofOneCamp(campId);
//				System.out.println(campsiteTentStatusList);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("campsiteTentStatusList", list);
				String url = "/campsitetentstatus/listaddCampTent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				
				Integer campId = new Integer(req.getParameter("campId").trim()); // �n�j�w�u��s�W�ۤv��

				java.sql.Date campOpeningTime = null;
				try {
					campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("�п�J���!");
				}

				Integer emptyCampLeft = null;
				try {
					emptyCampLeft = new Integer(req.getParameter("emptyCampLeft").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�|�l�H�ƤW���ж񥿾��.");
				}
				CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();
				campsiteTentStatusVO.setCampId(campId);
				campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
				campsiteTentStatusVO.setEmptyCampLeft(emptyCampLeft);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("CampsiteTentStatusVO", campsiteTentStatusVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/campsitetentstatus/addCampTent.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				campsiteTentStatusVO = campsiteTentStatusSvc.addCampsiteTentStatus(campId, campOpeningTime, emptyCampLeft);
				
				req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO);
//				List<CampsiteTentStatusVO> list = campsiteTentStatusSvc.getByCampId(campId);
//				req.setAttribute("camptentList" ,list);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/campsitetentstatus/listaddCampTent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/campsitetentstatus/addCampTent.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer campId = new Integer(req.getParameter("campId"));

//				java.sql.Date campOpeningTime = null;
//				try {
//					campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
//				} catch (IllegalArgumentException e) {
//					campOpeningTime = new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
				/*************************** 2.�}�l�d�߸�� ****************************************/
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				CampsiteTentStatusVO campsiteTentStatusVO = campsiteTentStatusSvc.getOneCampsiteTentByCampId(campId);
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO);
				String url = "/campsitetentstatus/updateCampTent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/campsitetentstatus/listaddCampTent.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				Integer campId = new Integer(req.getParameter("campId").trim()); // �n�j�w�u��s�W�ۤv��
				System.out.println("��s");
				System.out.println("campId: " + campId);

				java.sql.Date campOpeningTime = null;
				try {
					campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("�п�J���!");
				}
				System.out.println("time: " + campOpeningTime);

				Integer emptyCampLeft = null;
				try {
					emptyCampLeft = new Integer(req.getParameter("emptyCampLeft").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�|�l�H�ƤW���ж񥿾��.");
				}
				System.out.println("�Ŧ�: " + emptyCampLeft);
				
				CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();
				campsiteTentStatusVO.setCampId(campId);
				campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
				campsiteTentStatusVO.setEmptyCampLeft(emptyCampLeft);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/campsitetentstatus/updateCampTent.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.�}�l�ק��� *****************************************/
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				campsiteTentStatusVO = campsiteTentStatusSvc.updateCampsiteTentStatus(emptyCampLeft, campId,
						campOpeningTime);
//				CampsiteTentStatusService csSvc = new CampsiteTentStatusService();
//				List<CampsiteTentStatusVO> csList =  csSvc.getByCampId(campId);
				

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
//				req.setAttribute("csList", csList);
				req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO); 
				String url = "/campsitetentstatus/listupdateCampsTent.jsp"; // �S���^��listOneCampsiteTentStatus�O�]������O�n���oList�~���
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
System.out.println("123");
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/campsitetentstatus/updateCampTent.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllCampsite.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer campId = new Integer(req.getParameter("campId").trim()); // �n�j�w�u��s�W�ۤv��

				java.sql.Date campOpeningTime = null;
				try {
					campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
				} catch (IllegalArgumentException e) {
					campOpeningTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				/*************************** 2.�}�l�R����� ***************************************/
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				campsiteTentStatusSvc.deleteCampsiteTentStatus(campId, campOpeningTime);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/campsitetentstatus/listAllCampsiteTentStatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����a����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsitetentstatus/listAllCampsiteTentStatus.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("go_to_addCampTent".equals(action)) {

//			try {
				Integer campId = new Integer(req.getParameter("campId"));
//				java.sql.Date campOpeningTime = null;
//				campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
				System.out.println("���: " + campId);
				req.setAttribute("campId", campId);
//				req.setAttribute("campOpeningTime", campOpeningTime);
				String url = "/campsitetentstatus/addCampTent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:" + e.getMessage());
//				System.out.println("����");
//				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
//				failureView.forward(req, res);
			}

//		}
	}
}
