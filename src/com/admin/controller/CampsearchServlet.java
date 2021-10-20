package com.admin.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adminList.model.AdminListVO;
import com.adminList.model.AdminService;
import com.campsite.model.*;

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
				req.setAttribute("CampsiteVO",campsiteVO); // ��Ʈw���X��empVO����,�s�Jreq
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
	}
}
