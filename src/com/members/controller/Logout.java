package com.members.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			String location = (String) session.getAttribute("location");
			if (location != null) {
				session.invalidate();// *�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
				res.sendRedirect(location);
				return;
			}
		} catch (Exception ignored) {
		}
		session.invalidate();
		res.sendRedirect(req.getContextPath() + "/homepage/index.jsp"); // *�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		HttpSession session = req.getSession();
//		String location = (String) session.getAttribute("location");
//		session.invalidate();
//		res.sendRedirect(location);
//	}
}
