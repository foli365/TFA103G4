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

		// 【取得使用者 帳號(account) 密碼(password)】
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// 【檢查該帳號 , 密碼是否有效】
		if (!allowUser(email, password)) {
			MemberService memSvc = new MemberService();
			MembersVO membersVO = memSvc.findByEmail(email);
			if (membersVO != null) {
				req.setAttribute("email", membersVO.getEmail());
				req.setAttribute("noPassword", "你所輸入的密碼錯誤。");
				String url = "/register_and_login/login.jsp";
				RequestDispatcher failedView = req.getRequestDispatcher(url);
				failedView.forward(req, res);
			} else {
				req.setAttribute("noEmail", "你所輸入的電子信箱錯誤");
				req.setAttribute("noPassword", "你所輸入的密碼錯誤。");
				String url = "/register_and_login/login.jsp";
				RequestDispatcher failedView = req.getRequestDispatcher(url);
				failedView.forward(req, res);
			}
			
		} else {
			// 【帳號 , 密碼有效時, 才做以下工作】
			MemberService memSvc = new MemberService();
			HttpSession session = req.getSession();
			session.setAttribute("account", memSvc.findByEmail(email).getName()); // *工作1: 才在session內做已經登入過的標識
			session.setAttribute("id", memSvc.findByEmail(email).getMemberId());
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/homepage/index.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
		}

	}
}
