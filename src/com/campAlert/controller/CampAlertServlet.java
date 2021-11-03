package com.campAlert.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.campAlert.model.*;
import com.camporder.model.CampOrderService;
import com.camporder.model.CampOrderVO;

//@WebServlet("/backendLogin/CampAlert.do")
public class CampAlertServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		Integer id = Integer.parseInt(req.getParameter("Id"));
		Integer imgid = Integer.parseInt(req.getParameter("img"));
		CampAlertDAO dao = new CampAlertDAO();
		CampAlertVO vo = dao.findByPrimaryKey(id);

		try {
			switch (imgid) {
			case 1:
				out.write(vo.getPicture1());
				break;
			case 2:
				out.write(vo.getPicture2());
				break;
			default:
				out.write(vo.getPicture3());
			}

		} catch (Exception e) {
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		//�R���\��
		if ("delete".equals(action)) {
			Integer id = new Integer(req.getParameter("Id"));
			CampAlertService alertSvc = new CampAlertService();
			alertSvc.deleteCampAlertDAO(id);
			String url = "/backendLogin/alert.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//�d�߹Ϥ��\��
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String alertId = req.getParameter("alertId");
				if (alertId == null || (alertId.trim()).length() == 0) {
					errorMsgs.add("�п�J��a���|�s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer alert = null;
				try {
					alert= new Integer(alertId);
				} catch (Exception e) {
					errorMsgs.add("���|�s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.�}�l�d�߸�� *****************************************/
				CampAlertService alertSvc = new CampAlertService();
				CampAlertVO campAlertVO = alertSvc.getOneEmp(alert);
				if (campAlertVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("campAlertVO",campAlertVO);
				String url = "/backendLogin/campAlertListone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
