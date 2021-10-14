package com.camprelease.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.camprelease.model.*;

public class CampReleaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

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
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("campreleaseVO", campreleaseVO); // ��Ʈw���X��campreleaseVO����,�s�Jreq
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
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listCampRel.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�updateCampRel.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer campId = new Integer(req.getParameter("campId").trim());

				String campName = req.getParameter("campName");
				String campNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (campName == null || campName.trim().length() == 0) {
					errorMsgs.add("��a�W��: �ФŪť�");
				} else if (!campName.trim().matches(campNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("��a�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String location = req.getParameter("location").trim();
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
				String campDescriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{5,50}$";
				if (campDescription == null || campDescription.trim().length() == 0) {
					errorMsgs.add("��a���нж�g");
				} else if (!campDescription.trim().matches(campDescriptionReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("��a����: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb5��50����");
				}


				Integer campPrice = null;
				try {
					campPrice = new Integer(req.getParameter("campPrice").trim());
				} catch (NumberFormatException e) {
					campPrice = 0;
					errorMsgs.add("�����ж�Ʀr");
				}

				java.sql.Timestamp listedTime = null;
				try {
					listedTime = java.sql.Timestamp.valueOf(req.getParameter("listedTime").trim());
				} catch (IllegalArgumentException e) {
					listedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

//				// Handle with byte array data
//				public static void readPicture(byte[] bytes) throws IOException {
//					FileOutputStream fos = new FileOutputStream("PICTURE1");
//					fos.write(bytes);
//					fos.flush();
//					fos.close();
//				}
				Integer memberId = new Integer(req.getParameter("memberId").trim());
				
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					String filename = getFileNameFromPart(part);
					if (filename != null && part.getContentType() != null) {
						InputStream in1 = req.getPart("picture1").getInputStream();
						byte[] picture1 = null;

						if (in1.available() != 0) {
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

						CampReleaseVO campreleaseVO = new CampReleaseVO();
						campreleaseVO.setCampId(campId);
						campreleaseVO.setCampName(campName);
						campreleaseVO.setLocation(location);
						campreleaseVO.setLatitude(latitude);
						campreleaseVO.setLongtitude(longtitude);
						campreleaseVO.setCampDescription(campDescription);
						campreleaseVO.setCampPrice(campPrice);
						campreleaseVO.setListedTime(listedTime);
						campreleaseVO.setPicture1(picture1);
						campreleaseVO.setPicture2(picture2);
						campreleaseVO.setPicture3(picture3);
						campreleaseVO.setPicture4(picture4);
						campreleaseVO.setPicture5(picture5);
						campreleaseVO.setMemberId(memberId);

//				try {
//					campaddVO.setPicture1(getPictureByteArray(picture1));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("campreleaseVO", campreleaseVO); // �t����J�榡���~��campreleaseVO����,�]�s�Jreq
							RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updateCampRel.jsp");
							failureView.forward(req, res);
							return; // �{�����_
						}

						/*************************** 2.�}�l�ק��� *****************************************/
						CampReleaseService campreleaseSvc = new CampReleaseService();
						campreleaseVO = campreleaseSvc.updateCampRelease(campName, location, latitude, longtitude,
								campDescription, campPrice, listedTime, picture1, picture2, picture3, picture4,
								picture5, memberId, campId);

						/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
						req.setAttribute("campreleaseVO", campreleaseVO); // ��Ʈwupdate���\��,���T����campaddVO����,�s�Jreq
						String url = "/camprelease/listOneRel.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneRel.jsp
						successView.forward(req, res);
					}
				}
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
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
				String campName = req.getParameter("campName");
				String campNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (campName == null || campName.trim().length() == 0) {
					errorMsgs.add("��a�W��: �ФŪť�");
				} else if (!campName.trim().matches(campNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("��a�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String location = req.getParameter("location").trim();
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

//				java.util.Date date = new java.util.Date();
//				java.sql.Timestamp listedTime = new java.sql.Timestamp(date.getTime());
				java.sql.Timestamp listedTime = null;
				try {
					listedTime = java.sql.Timestamp.valueOf(req.getParameter("listedTime").trim());
				} catch (IllegalArgumentException e) {
					listedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�ФŪť�");
				}
				
				Integer memberId = new Integer(req.getParameter("memberId").trim());

				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					String filename = getFileNameFromPart(part);
					if (filename != null && part.getContentType() != null) {
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

						CampReleaseVO campreleaseVO = new CampReleaseVO();
						campreleaseVO.setCampName(campName);
						campreleaseVO.setLocation(location);
						campreleaseVO.setLatitude(latitude);
						campreleaseVO.setLongtitude(longtitude);
						campreleaseVO.setCampDescription(campDescription);
						campreleaseVO.setCampPrice(campPrice);
						campreleaseVO.setListedTime(listedTime);
						campreleaseVO.setPicture1(picture1);
						campreleaseVO.setPicture2(picture2);
						campreleaseVO.setPicture3(picture3);
						campreleaseVO.setPicture4(picture4);
						campreleaseVO.setPicture5(picture5);
						campreleaseVO.setMemberId(memberId);
						
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

						/*************************** 2.�}�l�s�W��� ***************************************/
						CampReleaseService campreleaseSvc = new CampReleaseService();
						campreleaseVO = campreleaseSvc.addCampRelease(campName, location, latitude, longtitude,
								campDescription, campPrice, listedTime, picture1, picture2, picture3, picture4,
								picture5, memberId);

						/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
						String url = "/camprelease/listCampRel.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAddCamp.jsp
						successView.forward(req, res);
					}
				}
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addCampRel.jsp");
				failureView.forward(req, res);
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
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
