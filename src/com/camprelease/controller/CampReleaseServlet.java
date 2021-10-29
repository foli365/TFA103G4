package com.camprelease.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.camprelease.model.*;
import com.facilities.model.*;
import com.plan.model.*;

@MultipartConfig
public class CampReleaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("getOne_For_Display".equals(action)) { // �Ӧ�Select_Page.jsp���ШD

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
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer campId = null;
				try {
					campId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("��a�s���榡�����~");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				CampReleaseService campreleaseSvc = new CampReleaseService();
				CampReleaseVO campreleaseVO = campreleaseSvc.getOneCampRelease(campId);
				if (campreleaseVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				PlanService planSvc = new PlanService();
				ArrayList<PlanVO> list = planSvc.getCampId(campId);
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
				req.setAttribute("campreleaseVO", campreleaseVO); // ��Ʈw���X��campreleaseVO����,�s�Jreq
				req.setAttribute("planVOList", list); // ��Ʈw���X��campreleaseVO����,�s�Jreq
				
				String url = "/camprelease/listOneRel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneRel.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listCamp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer campId = new Integer(req.getParameter("campId"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				CampReleaseService campreleaseSvc = new CampReleaseService();
				CampReleaseVO campreleaseVO = campreleaseSvc.getOneCampRelease(campId);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("campreleaseVO", campreleaseVO); // ��Ʈw���X��campaddVO����,�s�Jreq
				String url = "/camprelease/updateCampRel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� updateAddCamp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listOneRel.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("update".equals(action)) { 
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
					errorMsgs.add("�п�J�W�r");
				} else if (!campName.trim().matches(campNameReg)) { 
					errorMsgs.add("�W�r�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��15����");
				}
				String location = req.getParameter("location");
				String locationReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{10,25}$";
				if (location == null || location.trim().length() == 0) {
					errorMsgs.add("�п�J�a�}");
				} else if (!location.trim().matches(locationReg)) { 
					errorMsgs.add("�a�}�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb10��25����");
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

				Integer campPrice = null;
				try {
					campPrice = new Integer(req.getParameter("campPrice").trim());
				} catch (NumberFormatException e) {
					campPrice = 0;
					errorMsgs.add("�����ж�Ʀr");
				}
				
                Integer campLimit = null;
				try {
					campLimit = new Integer(req.getParameter("campLimit").trim());
				} catch (NumberFormatException e) {
					campLimit = 0;
					errorMsgs.add("��a�H�ƭ���ж�Ʀr");
				}
				
				java.sql.Timestamp listedTime = null;
				try {
					listedTime = java.sql.Timestamp.valueOf(req.getParameter("listedTime").trim());
				} catch (IllegalArgumentException e) {
					listedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String campDescription = req.getParameter("campDescription").trim();
				if (campDescription == null || campDescription.trim().length() == 0) {
					errorMsgs.add("�п�J����");
				}	

				
//				InputStream in1 = req.getPart("picture1").getInputStream();
//				byte[] picture1 = null;
//				if (in1.available() != 0) {
//					picture1 = new byte[in1.available()];
//					in1.read(picture1);
//					in1.close();
//				} else {
//					CampsiteService campsiteSvc = new CampsiteService();
//					picture1 = campsiteSvc.getOneCampsite(campId).getPicture1();
//				}

				
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					String filename = getFileNameFromPart(part);
					if (filename != null && part.getContentType() != null) {
						InputStream in1 = req.getPart("picture1").getInputStream();
						byte[] picture1 = null;

						if (in1.available() != 0) {
							System.out.println(in1);
							picture1 = new byte[in1.available()];
							in1.read(picture1);
							in1.close();
						} else {
							CampReleaseService campreleaseSvc = new CampReleaseService();
							picture1 = campreleaseSvc.getOneCampRelease(campId).getPicture1();
						}
						InputStream in2 = req.getPart("picture2").getInputStream();
						byte[] picture2 = null;
						
						if (in2.available() != 0) {
						System.out.println("�w�w");	
							picture2 = new byte[in2.available()];
							in2.read(picture2);
							in2.close();
						} else {
							picture2 = null;
						}
						InputStream in3 = req.getPart("picture3").getInputStream();
						byte[] picture3 = null;

						if (in3.available() != 0) {
							System.out.println("�w�w");	
							picture3 = new byte[in3.available()];
							in3.read(picture3);
							in3.close();
						} else {
							picture3 = null;
						}
						InputStream in4 = req.getPart("picture4").getInputStream();
						byte[] picture4 = null;

						if (in4.available() != 0) {
							System.out.println("�w�w");	
							picture4 = new byte[in4.available()];
							in4.read(picture4);
							in4.close();
						} else {
							picture4 = null;
						}
						InputStream in5 = req.getPart("picture5").getInputStream();
						byte[] picture5 = null;

						if (in5.available() != 0) {
							System.out.println("�w�w");	
							picture5 = new byte[in5.available()];
							in5.read(picture5);
							in5.close();
						} else {
							picture5 = null;
						}

//				Part part1 = req.getPart("picture1");
//				InputStream in1 = part1.getInputStream();
//				byte[] picture1 = new byte[in1.available()];
//				in1.read(picture1);
//				in1.close();
////					picture1 = new byte[in1.available()];
////					in1.read(picture1);
////					in1.close();
////				} else {
////					CampReleaseService campreleaseSvc = new CampReleaseService();
////					picture1 = campreleaseSvc.getOneCampRelease(campId).getPicture1();
////				}
//				Part part2 = req.getPart("picture2");
//				InputStream in2 = part2.getInputStream();
//				byte[] picture2 = new byte[in2.available()];
//				in2.read(picture2);
//				in2.close();
////				if (in2.available() != 0) {
////					picture2 = new byte[in2.available()];
////					in2.read(picture2);
////					in2.close();
////				} else {
////					CampReleaseService campreleaseSvc = new CampReleaseService();
////					picture2 = campreleaseSvc.getOneCampRelease(campId).getPicture2();
////				}
//				Part part3 = req.getPart("picture3");
//				InputStream in3 = part3.getInputStream();
//				byte[] picture3 = new byte[in3.available()];
//				in3.read(picture3);
//				in3.close();
//				
//				Part part4 = req.getPart("picture4");
//				InputStream in4 = part4.getInputStream();
//				byte[] picture4 = new byte[in4.available()];
//				in4.read(picture4);
//				in4.close();
//				
//				Part part5 = req.getPart("picture5");
//				InputStream in5 = part5.getInputStream();
//				byte[] picture5 = new byte[in5.available()];
//				in5.read(picture5);
//				in5.close();
//				
//				InputStream in3 = req.getPart("picture3").getInputStream();
//				byte[] picture3 = null;
//				if (in3.available() != 0) {
//					picture3 = new byte[in3.available()];
//					in3.read(picture3);
//					in3.close();
//				} else {
//					CampReleaseService campreleaseSvc = new CampReleaseService();
//					picture3 = campreleaseSvc.getOneCampRelease(campId).getPicture3();
//				}
//				InputStream in4 = req.getPart("picture4").getInputStream();
//				byte[] picture4 = null;
//				if (in4.available() != 0) {
//					picture4 = new byte[in4.available()];
//					in4.read(picture4);
//					in4.close();
//				} else {
//					CampReleaseService campreleaseSvc = new CampReleaseService();
//					picture4 = campreleaseSvc.getOneCampRelease(campId).getPicture4();
//				}
//				InputStream in5 = req.getPart("picture5").getInputStream();
//				byte[] picture5 = null;
//				if (in5.available() != 0) {
//					picture5 = new byte[in1.available()];
//					in5.read(picture5);
//					in5.close();
//				} else {
//					CampReleaseService campreleaseSvc = new CampReleaseService();
//					picture5 = campreleaseSvc.getOneCampRelease(campId).getPicture5();
//				}					
				
				CampReleaseVO campreleaseVO = new CampReleaseVO();
				campreleaseVO.setCampId(campId);
				campreleaseVO.setMemberId(memberId);
				campreleaseVO.setCampName(campName);
				campreleaseVO.setLocation(location);
				campreleaseVO.setLatitude(latitude);
				campreleaseVO.setLongtitude(longtitude);
				campreleaseVO.setCampDescription(campDescription);
				campreleaseVO.setCampPrice(campPrice);
				campreleaseVO.setCampLimit(campLimit);
				campreleaseVO.setListedTime(listedTime);
				campreleaseVO.setPicture1(picture1);
				campreleaseVO.setPicture2(picture2);
				campreleaseVO.setPicture3(picture3);
				campreleaseVO.setPicture4(picture4);
				campreleaseVO.setPicture5(picture5);
		
//				try {
//					campaddVO.setPicture1(getPictureByteArray(picture1));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("campreleaseVO", campreleaseVO); 
							
							RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updateCampRel.jsp");
							failureView.forward(req, res);
							return; 
						}
						/*************************** 2.�}�l�ק��� *****************************************/
						CampReleaseService campreleaseSvc = new CampReleaseService();
						campreleaseVO = campreleaseSvc.updateCampRelease(memberId, campName, location, latitude, longtitude,
								campDescription, campPrice, campLimit, listedTime, picture1, picture2, picture3, picture4, picture5, campId);
						System.out.println(campId);	
//						CampReleaseService crs = new CampReleaseService();
//						ArrayList<CampReleaseVO> list = crs.getCamp(campId);
//						req.setAttribute("list", list);
						/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
						req.setAttribute("campreleaseVO", campreleaseVO); 
						String url = "/camprelease/listOneRel.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); 
						successView.forward(req, res);
					}
				}
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���");
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updateCampRel.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addCampRel.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				System.out.println("memberId= " + req.getParameter("memberId").trim());
				Integer memberId = new Integer(req.getParameter("memberId").trim());
				
				System.out.println("123");
				String campName = req.getParameter("campName");
				String campNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,15}$";
				if (campName == null || campName.trim().length() == 0) {
					errorMsgs.add("�W�ٽФŪť�");
				} else if (!campName.trim().matches(campNameReg)) { 
					errorMsgs.add("�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��15����");
				}

				String location = req.getParameter("location");
				String locationReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,30}$";
				if (location == null || location.trim().length() == 0) {
					errorMsgs.add("�a�}�ФŪť�");
				} else if (!location.trim().matches(locationReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�a�}: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb10��30����");
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
					errorMsgs.add("��a���нж�g");
				} 
				Integer campPrice = null;
				try {
					campPrice = new Integer(req.getParameter("campPrice").trim());
				} catch (NumberFormatException e) {
					campPrice = 0;
					errorMsgs.add("�����ж�Ʀr");
				}
				
				Integer campLimit = null;
				try {
					campLimit = new Integer(req.getParameter("campLimit").trim());
				} catch (NumberFormatException e) {
					campLimit = 0;
					errorMsgs.add("��a�H�ƭ���ж�Ʀr");
				}
				
				java.sql.Timestamp listedTime = null;
				try {
					listedTime = java.sql.Timestamp.valueOf(req.getParameter("listedTime").trim());
				} catch (IllegalArgumentException e) {
					listedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�ФŪť�");
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
				
				InputStream in2 = req.getPart("picture2").getInputStream();
				byte[] picture2 = null;
				if (in2.available() != 0) {
					picture2 = new byte[in2.available()];
					in2.read(picture2);
					in2.close();
				} else {
					picture2 = null;
				}
				
				InputStream in3 = req.getPart("picture3").getInputStream();
				byte[] picture3 = null;
				if (in3.available() != 0) {
					picture3 = new byte[in3.available()];
					in3.read(picture3);
					in3.close();
				} else {
					picture3 = null;
				}
				
				InputStream in4 = req.getPart("picture4").getInputStream();
				byte[] picture4 = null;
				if (in4.available() != 0) {
					picture4 = new byte[in4.available()];
					in4.read(picture4);
					in4.close();
				} else {
					picture4 = null;
				}
				
				InputStream in5 = req.getPart("picture5").getInputStream();
				byte[] picture5 = null;
				if (in5.available() != 0) {
					picture5 = new byte[in5.available()];
					in5.read(picture5);
					in5.close();
				} else {
					picture5 = null;
				}
				
//				System.out.println("errorMsgs = " + errorMsgs);
//
//				Integer campId = new Integer(req.getParameter("campId").trim());

							
						CampReleaseVO campreleaseVO = new CampReleaseVO();
						campreleaseVO.setMemberId(memberId);
						campreleaseVO.setCampName(campName);
						campreleaseVO.setLocation(location);
						campreleaseVO.setLatitude(latitude);
						campreleaseVO.setLongtitude(longtitude);
						campreleaseVO.setCampDescription(campDescription);
						campreleaseVO.setCampPrice(campPrice);
						campreleaseVO.setCampLimit(campLimit);						
						campreleaseVO.setListedTime(listedTime);
						campreleaseVO.setPicture1(picture1);
						campreleaseVO.setPicture2(picture2);
						campreleaseVO.setPicture3(picture3);
						campreleaseVO.setPicture4(picture4);
						campreleaseVO.setPicture5(picture5);
							
//						List<FacilitiesVO> facilitiesList = new ArrayList<FacilitiesVO>();
//						FacilitiesVO facilitiesVO = new FacilitiesVO();
//						facilitiesVO.setBbq(bbq);
//						facilitiesVO.setWifi(wifi);
//						facilitiesVO.setNosmoke(nosmoke);
//						facilitiesVO.setPets(pets);
//						facilitiesList.add(facilitiesVO);
						
//						List<PlanVO> planList = new ArrayList<PlanVO>();
//						PlanVO planVO = new PlanVO();
//						planVO.setPlanName(planName);
//						planVO.setPlanGuestLimit(planGuestLimit);
//						planVO.setPlanAgeLimit(planAgeLimit);
//						planVO.setPlanPrice(planPrice);
						
//				try {
//					campaddVO.setPicture1(getPictureByteArray(picture1));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("campreleaseVO", campreleaseVO); // �t����J�榡���~��campreleaseVO����,�]�s�Jreq
							
							RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addCampRel.jsp");
							failureView.forward(req, res);
							return; // �{�����_
						}
						
//						CampReleaseDAO campreleaseDAO = new CampReleaseDAO();
//						campreleaseDAO.addCampRelease(campreleaseVO, facilitiesVO);

						/*************************** 2.�}�l�s�W��� ***************************************/
						CampReleaseService campreleaseSvc = new CampReleaseService();
						campreleaseVO = campreleaseSvc.addCampRelease(memberId, campName, location, latitude, longtitude,
								campDescription, campPrice, campLimit, listedTime, picture1, picture2, picture3, picture4,
								picture5);
						
						req.setAttribute("campreleaseVO", campreleaseVO);
//						CampReleaseDAO campreleaseDAO = new CampReleaseDAO();
//						campreleaseDAO.insertCamp(campreleaseVO, facilitiesList);

						/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
						String url = "/camprelease/listOneRel.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAddCamp.jsp
						successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addCampRel.jsp");
				failureView.forward(req, res);
//				errorMsgs.put("insertError", "wrong"); //e.getMessage()
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listCampRel.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer campId = new Integer(req.getParameter("campId"));

				/*************************** 2.�}�l�R����� ***************************************/
				CampReleaseService campreleaseSvc = new CampReleaseService();
				campreleaseSvc.deleteCampRelease(campId);
				
//				PlanService planSvc = new PlanService();
//				planSvc.deletebyCampId(campId);
//				
//				FacilitiesService facilitiesSvc = new FacilitiesService();
//				facilitiesSvc.deletebyCampId(campId);
//				
//				CampAlertService campAlertSvc = new CampAlertService();
//				campAlertSvc.deletebyCampId(campId);
//				
//				FavoriteService favoriteSvc = new FavoriteService();
//				favoriteSvc.deletebyCampId(campId);
//				
//				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
//				campsiteTentStatusSvc.deletebyCampId(campId);
//				
//				CampOrderService campOrderSvc = new CampOrderService();
//				campOrderSvc.deletebyCampId(campId);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/camprelease/listCampRel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listCampRel.jsp");
				failureView.forward(req, res);
			}
		}
	}
	

//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}

	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part parts) {
		String header = parts.getHeader("content-disposition");
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
