package com.members.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MemberService;
import com.members.model.MembersVO;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> errorMsgs = new LinkedList<String>();

		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("passwordConfirm");
		String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
		String email = req.getParameter("email");
		MemberService memSvc = new MemberService();
		if (memSvc.findByEmail(email) != null) {
			req.setAttribute("emailRepeat", "此電子郵件已被註冊過");
			req.setAttribute("name", name);
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/register.jsp");
			failed.forward(req, res);
		} else if (!password.trim().matches(passwordReg)) {
			req.setAttribute("pwordTooWeak", "密碼長度不得小於8且至少須有一字母");
			req.setAttribute("name", name);
			req.setAttribute("email", email);
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/register.jsp");
			failed.forward(req, res);
		} else if (!password.trim().equals(passwordConfirm.trim())) {
			req.setAttribute("passwordDiff", "請檢查密碼與確認密碼是否相同");
			req.setAttribute("name", name);
			req.setAttribute("email", email);
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/register.jsp");
			failed.forward(req, res);
		} else {
			String path = getServletContext().getRealPath("/img/avatar.jpg");
			MembersVO memVO = memSvc.addMember(name, bcryptHashString, email, getPictureByteArray(path));
			HttpSession session = req.getSession();
			session.setAttribute("account", name);
			session.setAttribute("id", memSvc.findByEmail(email).getMemberId());
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			} catch (Exception e) {
			}
			res.sendRedirect(req.getContextPath() + "/homepage/index.jsp");
		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
