package com.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adminList.model.AdminListVO;
import com.adminList.model.AdminService;
import com.campAlert.model.CampAlertService;
import com.campAlert.model.CampAlertVO;
import com.campsite.model.*;
import com.campAlert.model.*;

@MultipartConfig
public class CampsearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String campid= req.getParameter("campId");
				if (campid== null || (campid.trim()).length() == 0) {
					errorMsgs.add("�п�J��a�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/camp.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer camp = null;
				try {
					camp = new Integer(campid);
				} catch (Exception e) {
					errorMsgs.add("�޲z���s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/camp.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************2.�}�l�d�߸��*****************************************/
				CampsiteService campSvc = new CampsiteService();
				CampsiteVO campsiteVO = campSvc.getOneCampsite(camp);
				if (campsiteVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/camp.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("campsiteVO",campsiteVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backendLogin/camp-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backendLogin/camp.jsp");
				failureView.forward(req, res);
			}
		}	
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllCampsite.jsp���ШD
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer campId = new Integer(req.getParameter("campId"));
			
				/*************************** 2.�}�l�d�߸�� ****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				CampsiteVO campsiteVO = campsiteSvc.getOneCampsite(campId);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("campsiteVO", campsiteVO); // ��Ʈw���X��campsiteVO����,�s�Jreq
				String url = "/backendLogin/updateCamplist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_campsite_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/camp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_campsite_input.jsp���ШD
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer campId = new Integer(req.getParameter("campId").trim());
				Integer memberId = new Integer(req.getParameter("memberId").trim());
				System.out.println(memberId);
				String campName = req.getParameter("campName");
				String campNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,15}$";
				if (campName == null || campName.trim().length() == 0) {
					errorMsgs.add("��a�W��: �ФŪť�");
				} else if (!campName.trim().matches(campNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("��a�W��: �u��O���B�^��r���B�Ʀr, �B���ץ��ݦb2��15����");
				}

				String location = req.getParameter("location");
				String locationReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{10,25}$";
				if (location == null || location.trim().length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				} else if (!location.trim().matches(locationReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�a�}: �u��O���B�^��r���B�Ʀr, �B���ץ��ݦb10��25����");
				}

				Double latitude = null;
				try {
					latitude = new Double(req.getParameter("latitude").trim());
					System.out.println(latitude);
				} catch (NumberFormatException e) {
					latitude = 0.0;
					errorMsgs.add("�g�׽ж�Ʀr.");
				}

				Double longtitude = null;
				try {
					longtitude = new Double(req.getParameter("longtitude").trim());
				} catch (NumberFormatException e) {
					longtitude = 0.0;
					errorMsgs.add("�n�׽ж�Ʀr.");
				}

				String campDescription = req.getParameter("campDescription").trim();
				if (campDescription == null || campDescription.trim().length() == 0) {
					errorMsgs.add("��a�����ФŪť�");
				}

				Integer campPrice = null;
				try {
					campPrice = new Integer(req.getParameter("campPrice").trim());
				} catch (NumberFormatException e) {
					campPrice = 0;
					errorMsgs.add("��a����ж񥿾��.");
				}

				Integer campLimit = null;
				try {
					campLimit = new Integer(req.getParameter("campLimit").trim());
				} catch (NumberFormatException e) {
					campLimit = 0;
					errorMsgs.add("�H�ƤW���ж񥿾��.");
				}

				java.sql.Timestamp listedTime = null;
				try {
					listedTime = java.sql.Timestamp.valueOf(req.getParameter("listedTime").trim());
				} catch (IllegalArgumentException e) {
					listedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
//				java.sql.Date listedTime = null;
//				java.sql.Timestamp listedTimeTS = null;
//				try {
//					listedTime = java.sql.Date.valueOf(req.getParameter("listedTime").trim());
//					listedTimeTS = new java.sql.Timestamp(listedTime.getTime());
//				} catch (IllegalArgumentException e) {
//					listedTimeTS = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}

				Integer siteState = new Integer(req.getParameter("siteState").trim());

				Integer lovedCount = null;
				try {
					lovedCount = new Integer(req.getParameter("lovedCount").trim());
				} catch (NumberFormatException e) {
					lovedCount = 0;
					errorMsgs.add("���w�H�ƽж񥿾��.");
				}

				Integer reportedCount = null;
				try {
					reportedCount = new Integer(req.getParameter("reportedCount").trim());
				} catch (NumberFormatException e) {
					reportedCount = 0;
					errorMsgs.add("���|�H�ƽж񥿾��.");
				}

				InputStream in = req.getPart("campLicense").getInputStream();
				byte[] campLicense = null;
				if (in.available() != 0) {
					campLicense = new byte[in.available()];
					in.read(campLicense);
					in.close();
				} else {
					errorMsgs.add("�ФW����~����");
				}

				InputStream in1 = req.getPart("picture1").getInputStream();
				byte[] picture1 = null;
				if (in1.available() != 0) {
					picture1 = new byte[in1.available()];
					in1.read(picture1);
					in1.close();
				} else {
					CampsiteService campsiteSvc = new CampsiteService();
					picture1 = campsiteSvc.getOneCampsite(campId).getPicture1();
				}

				byte[] picture2 = null;
				byte[] picture3 = null;
				byte[] picture4 = null;
				byte[] picture5 = null;

				CampsiteVO campsiteVO = new CampsiteVO();
				campsiteVO.setCampId(campId);
				campsiteVO.setMemberId(memberId);
				campsiteVO.setCampName(campName);
				campsiteVO.setLocation(location);
				campsiteVO.setLatitude(latitude);
				campsiteVO.setLongtitude(longtitude);
				campsiteVO.setCampDescription(campDescription);
				campsiteVO.setCampPrice(campPrice);
				campsiteVO.setCampLimit(campLimit);
				campsiteVO.setListedTime(listedTime);
				campsiteVO.setSiteState(siteState);
				campsiteVO.setLovedCount(lovedCount);
				campsiteVO.setReportedCount(reportedCount);
				campsiteVO.setCampLicense(campLicense); // :(
				campsiteVO.setPicture1(picture1); // :(

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campsiteVO", campsiteVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/updateCamplist.jsp");
					failureView.forward(req, res);
					return; //�{�����_	
				}
			
				/*************************** 2.�}�l�ק��� *****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				campsiteVO = campsiteSvc.updateCampsite(memberId, campName, location, latitude,
						longtitude, campDescription, campPrice, campLimit, listedTime,
						siteState, lovedCount, reportedCount, campLicense, picture1,
						picture2, picture3, picture4, picture5, campId);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("campsiteVO", campsiteVO); // ��Ʈwupdate���\��,���T����campsiteVO����,�s�Jreq
				System.out.println(campsiteVO);
				System.out.println(campsiteVO);
				String url = "/backendLogin/camp-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneCampsite.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/updateCamplist.jsp");
				failureView.forward(req, res);
			}
		}
		
//��s���|		
		if ("addOne".equals(action)) { 
		req.setAttribute("errorMsgs", errorMsgs);

		String i =req.getParameter("campId");
//		CampsiteService campsiteService = new CampsiteService();
//		CampsiteVO campsiteVO=campsiteService.getOneCampsite(new Integer(i));
//		campsiteService.updateForOne(campsiteVO);
		
		String a = req.getParameter("alertId");
		CampAlertService campAlertService= new CampAlertService();
		CampAlertVO campAlertVO=campAlertService.updateStatus(new Integer(a), new Integer(i));
		
		
		req.setAttribute("campsiteVO", campAlertVO); // ��Ʈwupdate���\��,���T����campsiteVO����,�s�Jreq
		String url1 = "/backendLogin/alert.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url1); // �ק令�\��,���listOneCampsite.jsp
		successView.forward(req, res);
		}
	}
}
