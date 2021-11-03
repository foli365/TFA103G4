package com.campsite.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsite.model.CampsiteService;
import com.campsite.model.CampsiteVO;
import com.campsite.model.StringToSQLDate;
import com.campsitetentstatus.model.CampsiteTentStatusService;

@MultipartConfig
public class CampsiteServlet  extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

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

				Integer campId = null;
				try {
					campId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("��a�s���榡�����T");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				CampsiteVO campsiteVO = campsiteSvc.getOneCampsite(campId);
				if (campsiteVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("campsiteVO", campsiteVO); // ��Ʈw���X��campsiteVO����,�s�Jreq
				String url = "/campsite/listOneCampsite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneCampsite.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listCampsites_ByCompositeQuery".equals(action)) { // �Ӧ�search_campsite.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.�N��J����ରMap **********************************/
//				String dateRange = req.getParameter("CAMP_OPENING_TIME");
//				if (dateRange == null || dateRange.trim().length() == 0) {
//					errorMsgs.add("�п�ܤ��!");
//				}

				// �ĥ�Map<String,String[]> getParameterMap()����k
				// �`�N:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				Set<String> keys = map.keySet();
				for (String key : keys) {
					String value = map.get(key)[0];
					System.out.println(key + " : " + value);
				}
				/*************************** 2.�}�l�ƦX�d�� ***************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				List<CampsiteVO> list = campsiteSvc.getAll(map);
				System.out.println("Servlet list= " + list);
				
				String str = req.getParameter("EMPTY_CAMP_LEFT");
				Integer customerNum = null;
				try {
					customerNum = new Integer(str);
				} catch (Exception e) {
					customerNum = 0;
				}
				
				String value = req.getParameter("CAMP_OPENING_TIME");
				java.sql.Date strDate;
				java.sql.Date endDate;
				try {
					String strDateString = value.substring(0, 10);
					String endDateString = value.substring(13);
					strDate = StringToSQLDate.convertToSQLDate(strDateString);
					endDate = StringToSQLDate.convertToSQLDate(endDateString);
				} catch (Exception e) {
					strDate = null;
					endDate = null;
				}
				
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				List<CampsiteVO> filterList = new ArrayList<CampsiteVO>();
				
				for (CampsiteVO campsiteVO : list) {
					if(campsiteVO.getSiteState() == 1) {
						System.out.println("vo: " + campsiteVO);
						if (strDate != null && endDate != null) {
							if (campsiteTentStatusSvc.isTentAvailiblewithGuestNumberandTimeRange(campsiteVO.getCampId(), customerNum, strDate, endDate)) {
								filterList.add(campsiteVO);
							}
						} else if (customerNum != null) {
							if(campsiteTentStatusSvc.getUnavailibleDatewithGuestNumberOnly(campsiteVO.getCampId(), customerNum).isEmpty() && campsiteSvc.getOneCampsite(campsiteVO.getCampId()).getCampLimit() >= customerNum) {
								filterList.add(campsiteVO);
							}
						}
					}
				}
				System.out.println("Servlet filterList " + filterList);
				
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listExist", "empty");
				req.setAttribute("campsiteList", filterList); // ��Ʈw���X��filterList����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/campsite/search_campsite.jsp"); // ���\���search_campsite.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/search_campsite.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getReserveCampsite".equals(action)) { // �Ӧ�select_page.jsp���ШD

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

				Integer campId = null;
				try {
					campId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("��a�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/search_campsite.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				CampsiteVO campsiteVO = campsiteSvc.getOneCampsite(campId);
				if (campsiteVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/search_campsite.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				
				List<Integer> picturesNum = new ArrayList<>();
				if (campsiteVO.getPicture2() != null) {
					picturesNum.add(2);
				}
				if (campsiteVO.getPicture3() != null) {
					picturesNum.add(3);
				}
				if (campsiteVO.getPicture4() != null) {
					picturesNum.add(4);
				}
				if (campsiteVO.getPicture5() != null) {
					picturesNum.add(5);
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("campsiteVO", campsiteVO); // ��Ʈw���X��campsiteVO����,�s�Jreq
				req.setAttribute("picturesNum", picturesNum);
				String url = "/campsite/reserve_campsite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneCampsite.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/search_campsite.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				Integer memberId = new Integer(req.getParameter("memberId").trim());

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
					picture1 = null;
				}

				byte[] picture2 = null;
				byte[] picture3 = null;
				byte[] picture4 = null;
				byte[] picture5 = null;

				CampsiteVO campsiteVO = new CampsiteVO();
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
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/addCampsite.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				campsiteVO = campsiteSvc.addCampsite(memberId, campName, location, latitude, longtitude,
						campDescription, campPrice, campLimit, listedTime, siteState, lovedCount, reportedCount,
						campLicense, picture1, picture2, picture3, picture4, picture5);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/campsite/listAllCampsite_byDAO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/addCampsite.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllCampsite.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
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
				String url = "/campsite/update_campsite_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_campsite_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/listAllCampsite_byDAO.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_campsite_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer campId = new Integer(req.getParameter("campId").trim());

				Integer memberId = new Integer(req.getParameter("memberId").trim());

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
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/update_campsite_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}
				/*************************** 2.�}�l�ק��� *****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				campsiteVO = campsiteSvc.updateCampsite(memberId, campName, location, latitude, longtitude,
						campDescription, campPrice, campLimit, listedTime, siteState, lovedCount, reportedCount,
						campLicense, picture1, picture2, picture3, picture4, picture5, campId);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("campsiteVO", campsiteVO); // ��Ʈwupdate���\��,���T����campsiteVO����,�s�Jreq
				String url = "/campsite/listOneCampsite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneCampsite.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/update_campsite_input.jsp");
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
				Integer campId = new Integer(req.getParameter("campId"));

				/*************************** 2.�}�l�R����� ***************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				campsiteSvc.deleteCampsite(campId);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/campsite/listAllCampsite_byDAO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����a����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/listAllCampsite_byDAO.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}