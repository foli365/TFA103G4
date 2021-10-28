package com.members.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MemberService;
import com.members.model.MembersVO;

import at.favre.lib.crypto.bcrypt.BCrypt;

@WebServlet("/loginhandler")
public class LoginHandler extends HttpServlet {

	protected boolean allowUser(String email, String password) {
		MemberService memSvc = new MemberService();
		String bcryptHashString;
		try {
			bcryptHashString = memSvc.findByEmail(email).getPassword();
		} catch (Throwable e) {
			return false;
		}
		BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
		return result.verified;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");

		// �i���o�ϥΪ� �b��(account) �K�X(password)�j
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// �i�ˬd�ӱb�� , �K�X�O�_���ġj
		if (!allowUser(email, password)) {
			MemberService memSvc = new MemberService();
			MembersVO membersVO = memSvc.findByEmail(email);
			String url = "/register_and_login/login.jsp";
			RequestDispatcher failedView = req.getRequestDispatcher(url);
			if (membersVO != null) {
				req.setAttribute("email", membersVO.getEmail());
				req.setAttribute("noPassword", "密碼錯誤。");
				failedView.forward(req, res);
				return;
			} else {
				req.setAttribute("noEmail", "帳號錯誤");
				req.setAttribute("noPassword", "密碼錯誤。");
				failedView.forward(req, res);
			}

		} else {
			// �i�b�� , �K�X���Į�, �~���H�U�u�@�j
			MemberService memSvc = new MemberService();
			HttpSession session = req.getSession();
			session.setAttribute("account", memSvc.findByEmail(email).getName()); // *�u�@1: �~�bsession�����w�g�n�J�L������
			session.setAttribute("id", memSvc.findByEmail(email).getMemberId());
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/homepage/index.jsp"); // *�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
		}

	}
}
