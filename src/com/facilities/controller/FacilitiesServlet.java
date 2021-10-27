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
				
				String str = req.getParameter("facilitiesId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入設施編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer facilitiesId = null;
				try {
					facilitiesId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("設施編號格式有錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				FacilitiesService facilitiesSvc = new FacilitiesService();
				FacilitiesVO facilitiesVO = facilitiesSvc.getOneFacilities(facilitiesId);
				if (facilitiesVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("facilitiesVO", facilitiesVO);

				String url = "/camprelease/listOneFac.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
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

				Integer facilitiesId = new Integer(req.getParameter("FacilitiesId"));

				FacilitiesService facilitiesSvc = new FacilitiesService();
				FacilitiesVO facilitiesVO = facilitiesSvc.getOneFacilities(facilitiesId);

				req.setAttribute("facilitiesVO", facilitiesVO);
				String url = "/camprelease/updateFacil.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listFac.jsp");
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
				
//				Integer bbq = new Integer(req.getParameter("bbq".trim()));
//				Integer wifi = new Integer(req.getParameter("wifi".trim()));
//				Integer nosmoke = new Integer(req.getParameter("nosmoke".trim()));
//				Integer pets = new Integer(req.getParameter("pets".trim()));
				
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

				req.setAttribute("facilitiesVO", facilitiesVO);
				String url = "/camprelease/listOneFac.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗");
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
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/camprelease/listFac.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

