package com.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.admin.model.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected boolean allowUser(Integer adminId, String adminPwd) {
		AdminService adminSvc = new AdminService();
		AdminListVO adminListVO = adminSvc.getOneAdminList(adminId);
		String md5password = MD5Utils.md5(adminPwd);
		if (adminListVO.getAdminPwd().equals(md5password)) {
			return true;
		} else {
			return false;
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		// �i���o�ϥΪ� �b��(account) �K�X(password)
		Integer adminId = null;
		try {
			adminId = new Integer(req.getParameter("adminId"));
		} catch (NumberFormatException e) {
			adminId = 0;
			errorMsgs.add("�ж�g�޲z���s��");
		}
		// Send the use back to the form, if there were errors
		

		System.out.println("adminId = " + req.getParameter("adminId"));
		System.out.println("errorMsgs = " + errorMsgs);
		
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/backendLogin.jsp");
			failureView.forward(req, res);
			return;// �{�����_
		}
		String adminPwd = req.getParameter("adminPwd");

		// �i�ˬd�ӱb�� , �K�X�O�_���ġj
		if (!allowUser(adminId, adminPwd)) { // �i�b�� , �K�X�L�Įɡj
			AdminService adminService = new AdminService();
			AdminListVO adminListVO = adminService.getOneAdminList(adminId);
			if (adminListVO == null) {
				errorMsgs.add("�d�L�s���αK�X");
			}

			if (adminListVO != null) {
				req.setAttribute("adminId", adminListVO.getAdminId());
				req.setAttribute("noPassword", "�A�ҿ�J���K�X���~�C");
				String url = "/backendLogin/backendLogin.jsp";
				RequestDispatcher failedView = req.getRequestDispatcher(url);
				failedView.forward(req, res);
			} else {
				req.setAttribute("noAdminId", "�A�ҿ�J���޲z���s�����~");
				req.setAttribute("noPassword", "�A�ҿ�J���K�X���~�C");
				String url = "/backendLogin/backendLogin.jsp";
				RequestDispatcher failedView = req.getRequestDispatcher(url);
				failedView.forward(req, res);
			}
		} else { // �i�b�� , �K�X���Į�, �~���H�U�u�@�j
			AdminService adminService = new AdminService();
			HttpSession session = req.getSession();
			session.setAttribute("admin", adminService.getOneAdminList(adminId).getAdminName()); // *�u�@1:
																									// �~�bsession�����w�g�n�J�L������
			session.setAttribute("adminid", adminService.getOneAdminList(adminId).getAdminId());
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/backendLogin/home.jsp"); // *�u�@3:
																				// (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
		}
	}

}
