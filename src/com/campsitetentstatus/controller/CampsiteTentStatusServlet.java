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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsitetentstatus/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer campId = null;
				try {
					campId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("��a�s���榡�����T");
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				List<CampsiteTentStatusVO> campsiteTentStatusList = campsiteTentStatusSvc.getAllCampStatusofOneCamp(campId);
//				System.out.println(campsiteTentStatusList);
				if (campsiteTentStatusList.isEmpty()) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsitetentstatus/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("campsiteTentStatusList", campsiteTentStatusList);
				String url = "/campsitetentstatus/listOneCampsiteTentStatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsitetentstatus/select_page.jsp");
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
					campOpeningTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				Integer emptyCampLeft = null;
				try {
					emptyCampLeft = new Integer(req.getParameter("emptyCampLeft").trim());
				} catch (NumberFormatException e) {
					emptyCampLeft = 0;
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
							.getRequestDispatcher("/campsitetentstatus/addCampsiteTentStatus.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				campsiteTentStatusVO = campsiteTentStatusSvc.addCampsiteTentStatus(campId, campOpeningTime,
						emptyCampLeft);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/campsitetentstatus/listAllCampsiteTentStatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/campsitetentstatus/addCampsiteTentStatus.jsp");
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

				java.sql.Date campOpeningTime = null;
				try {
					campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
				} catch (IllegalArgumentException e) {
					campOpeningTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				/*************************** 2.�}�l�d�߸�� ****************************************/
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				CampsiteTentStatusVO campsiteTentStatusVO = campsiteTentStatusSvc.getOneCampsiteTentStatus(campId,
						campOpeningTime);
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO);
				String url = "/campsitetentstatus/updateCampsiteTentStatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/campsitetentstatus/listAllCampsiteTentStatus.jsp");
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

				java.sql.Date campOpeningTime = null;
				try {
					campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
				} catch (IllegalArgumentException e) {
					campOpeningTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				Integer emptyCampLeft = null;
				try {
					emptyCampLeft = new Integer(req.getParameter("emptyCampLeft").trim());
				} catch (NumberFormatException e) {
					emptyCampLeft = 0;
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
							.getRequestDispatcher("/campsitetentstatus/updateCampsiteTentStatus.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.�}�l�ק��� *****************************************/
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				campsiteTentStatusVO = campsiteTentStatusSvc.updateCampsiteTentStatus(emptyCampLeft, campId,
						campOpeningTime);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO);
				String url = "/campsitetentstatus/listAllCampsiteTentStatus.jsp"; // �S���^��listOneCampsiteTentStatus�O�]������O�n���oList�~���
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/campsitetentstatus/updateCampsiteTentStatus.jsp");
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
	}
}
