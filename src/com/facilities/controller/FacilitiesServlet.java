package com.facilities.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facilities.model.*;
import com.plan.model.PlanVO;

@MultipartConfig
public class FacilitiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("getOneFacilities_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String str = req.getParameter("campId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�]�I�s��");
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
					errorMsgs.add("�]�I�s���榡�����~");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				FacilitiesService facilitiesSvc = new FacilitiesService();
				List<FacilitiesVO> facilitiesList = facilitiesSvc.getByCampId(campId);
				if (facilitiesList == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("facilitiesList", facilitiesList);

				String url = "/camprelease/listOneFac.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneFacilities_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				Integer facilitiesId = new Integer(req.getParameter("facilitiesId"));

				FacilitiesService facilitiesSvc = new FacilitiesService();
				FacilitiesVO facilitiesVO = facilitiesSvc.getOneFacByFacilitiesId(facilitiesId);
				req.setAttribute("facilitiesVO", facilitiesVO);
				String url = "/camprelease/updateFacil.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listOneFac.jsp");
				failureView.forward(req, res);
			}
		}

		if ("facilities_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				Integer facilitiesId = new Integer(req.getParameter("facilitiesId").trim());
				
				Integer campId = new Integer(req.getParameter("campId").trim());
				String strbbq = req.getParameter("bbq");
				Integer bbq = null;
				if(strbbq == null) {
					bbq = 0;
				} else {
					bbq = new Integer(strbbq);
				}
				
				String strwifi = req.getParameter("wifi");
				Integer wifi = null;
				if(strwifi == null) {
					wifi = 0;
				} else {
					wifi = new Integer(strwifi);
				}
				
				String strnosmoke = req.getParameter("nosmoke");
				Integer nosmoke = null;
				if(strnosmoke == null) {
					nosmoke = 0;
				} else {
					nosmoke = new Integer(strnosmoke);
				}
				
				String strpets = req.getParameter("pets");
				Integer pets = null;
				if(strpets == null) {
					pets = 0;
				} else {
					pets = new Integer(strpets);
				}
				
				
				FacilitiesVO facilitiesVO = new FacilitiesVO();
				facilitiesVO.setFacilitiesId(facilitiesId);
				facilitiesVO.setCampId(campId);
				facilitiesVO.setBbq(bbq);
				facilitiesVO.setWifi(wifi);
				facilitiesVO.setNosmoke(nosmoke);
				facilitiesVO.setPets(pets);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("facilitiesVO", facilitiesVO);

					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updateFacil.jsp");
					failureView.forward(req, res);
					return;
				}

				FacilitiesService facilitiesSvc = new FacilitiesService();
				facilitiesVO = facilitiesSvc.updateFacilities(campId, bbq, wifi, nosmoke, pets, facilitiesId);
				FacilitiesService fs = new FacilitiesService();
				ArrayList<FacilitiesVO> faclist = fs.getCamp(campId);
				req.setAttribute("faclist", faclist);
				String url = "/camprelease/listOneFac.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//				return;
				
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���");
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updateFacil.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete_facilities".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				
				Integer facilitiesId = new Integer(req.getParameter("facilitiesId"));
				
				
				FacilitiesService facilitiesSvc = new FacilitiesService();
				facilitiesSvc.deleteFacilities(facilitiesId);
					
				String url = "/camprelease/listFac.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/camprelease/listFac.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("go_to_addFacil".equals(action)) {

//			try {
				Integer campId = new Integer(req.getParameter("campId"));
				System.out.println(campId);
				req.setAttribute("campId", campId);
				String url = "/camprelease/addFacil.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:" + e.getMessage());
//				System.out.println("����");
//				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
//				failureView.forward(req, res);
			}

//		}
		
		if ("insert_Facil".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
//Integer facilitiesId = new Integer(req.getParameter("facilitiesId").trim());
				
				
				String strbbq = req.getParameter("bbq");
				Integer bbq = null;
				if(strbbq == null) {
					bbq = 0;
				} else {
					bbq = new Integer(strbbq);
				}
				
				String strwifi = req.getParameter("wifi");
				Integer wifi = null;
				if(strwifi == null) {
					wifi = 0;
				} else {
					wifi = new Integer(strwifi);
				}
				
				String strnosmoke = req.getParameter("nosmoke");
				Integer nosmoke = null;
				if(strnosmoke == null) {
					nosmoke = 0;
				} else {
					nosmoke = new Integer(strnosmoke);
				}
				
				String strpets = req.getParameter("pets");
				Integer pets = null;
				if(strpets == null) {
					pets = 0;
				} else {
					pets = new Integer(strpets);
				}
				System.out.println(pets);
				Integer campId = new Integer(req.getParameter("campId").trim());
				System.out.println("123");
				
				FacilitiesVO facilitiesVO = new FacilitiesVO();
//				facilitiesVO.setFacilitiesId(facilitiesId);
				facilitiesVO.setCampId(campId);
				facilitiesVO.setBbq(bbq);
				facilitiesVO.setWifi(wifi);
				facilitiesVO.setNosmoke(nosmoke);
				facilitiesVO.setPets(pets);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("facilitiesVO", facilitiesVO);

					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addFacil.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.�}�l�s�W��� ***************************************/
				FacilitiesService facSvc = new FacilitiesService();
				facilitiesVO = facSvc.addFacilities(campId, bbq, wifi, nosmoke, pets);
				List<FacilitiesVO> list = facSvc.getByCampId(campId);
				req.setAttribute("facList", list);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/camprelease/listOneFac.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addFacil.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

