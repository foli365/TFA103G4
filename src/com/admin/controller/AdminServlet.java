package com.admin.controller;

import java.util.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.log.SystemLogHandler;

import com.admin.model.*;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

//@WebServlet("/backendLogin/AdminServlet")
public class AdminServlet extends HttpServlet {
	public AdminServlet() {
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		// �R���\��
		if ("delete".equals(action)) {
			Integer id = new Integer(req.getParameter("adminId"));
			AdminService alertSvc = new AdminService();
			alertSvc.deleteAdminListDAO(id);
			String url = "/backendLogin/manager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// �s�W�\��
		if ("add".equals(action)) {
			req.setAttribute("errorMsgs", errorMsgs);
			Integer adminId = null;

			try {
				adminId = new Integer(req.getParameter("adminId").trim());
			} catch (NumberFormatException e) {
				adminId = 0;
				errorMsgs.add("�s���ж�g�|��Ʀr.");
			}
			String adminPwd = req.getParameter("adminPwd");
			String adminPwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{6,10}$";
			if (adminPwd == null || adminPwd.trim().length() == 0) {
				errorMsgs.add("�޲z���K�X: �ФŪť�");
			} else if (!adminPwd.trim().matches(adminPwdReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("�޲z���K�X: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb6��10����");
			}
			String md5password = MD5Utils.md5(adminPwd);
			String adminName = req.getParameter("adminName").trim();
			if (adminName == null || adminName.trim().length() == 0) {
				errorMsgs.add("�W�r�ФŪť�");
			}
			AdminListVO AdminListVO = new AdminListVO();
			AdminListVO.setAdminId(adminId);
			AdminListVO.setAdminPwd(md5password);
			AdminListVO.setAdminName(adminName);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("AdminListVO", AdminListVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/addAdmin.jsp");
				failureView.forward(req, res);
				return;
			}
			AdminService adminSvc = new AdminService();
			AdminListVO vo = adminSvc.addAdminListVO(adminId, md5password, adminName);
			req.setAttribute("AdminListVO", AdminListVO);
			String url = "/backendLogin/manager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// �ק�\��
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer adminId = new Integer(req.getParameter("adminId"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				AdminService adminSvc = new AdminService();
				AdminListVO adminListVO = adminSvc.getOneAdminList(adminId);
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("adminListVO", adminListVO);
				String url = "/backendLogin/updateAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/manager.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {
			req.setAttribute("errorMsgs", errorMsgs);
			Integer adminId = null;
			try {
				adminId = new Integer(req.getParameter("adminId").trim());

			} catch (NumberFormatException e) {
				adminId = 0;
				errorMsgs.add("�s���ж�g�|��Ʀr.");
			}
			String adminPwd = req.getParameter("adminPwd");
			String adminPwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{6,10}$";
			if (adminPwd == null || adminPwd.trim().length() == 0) {
				errorMsgs.add("�޲z���K�X: �ФŪť�");
			} else if (!adminPwd.trim().matches(adminPwdReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("�޲z���K�X: �u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb6��10����");
			}
			String adminName = req.getParameter("adminName").trim();
			if (adminName == null || adminName.trim().length() == 0) {
				errorMsgs.add("�W�r�ФŪť�");
			}
			AdminListVO AdminListVO = new AdminListVO();
			AdminListVO.setAdminId(adminId);
			AdminListVO.setAdminPwd(adminPwd);
			AdminListVO.setAdminName(adminName);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("adminListVO", AdminListVO);// �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/updateAdmin.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}

			/*************************** 2.�}�l�ק��� *****************************************/
			AdminService empSvc = new AdminService();
			AdminListVO = empSvc.updateAdminListVO(adminId, adminPwd, adminName);

			/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
			req.setAttribute("adminListVO", AdminListVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/backendLogin/manager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String admin= req.getParameter("adminId");
				if (admin== null || (admin.trim()).length() == 0) {
					errorMsgs.add("�п�J�޲z���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/manager.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer adminId = null;
				try {
					adminId = new Integer(admin);
				} catch (Exception e) {
					errorMsgs.add("�޲z���s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/manager.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************2.�}�l�d�߸��*****************************************/
				AdminService adminSvc = new AdminService();
				AdminListVO adminVO = adminSvc.getOneAdminList(adminId);
				if (adminVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/manager.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("adminListVO",adminVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backendLogin/manager-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backendLogin/manager.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}
