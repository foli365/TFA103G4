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

		// ï¿½iï¿½ï¿½ï¿½oï¿½Ï¥Îªï¿½ ï¿½bï¿½ï¿½(account) ï¿½Kï¿½X(password)ï¿½j
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// ï¿½iï¿½Ë¬dï¿½Ó±bï¿½ï¿½ , ï¿½Kï¿½Xï¿½Oï¿½_ï¿½ï¿½ï¿½Ä¡j
		if (!allowUser(email, password)) {
			MemberService memSvc = new MemberService();
			MembersVO membersVO = memSvc.findByEmail(email);
			String url = "/register_and_login/login.jsp";
			RequestDispatcher failedView = req.getRequestDispatcher(url);
			if (membersVO != null) {
				req.setAttribute("email", membersVO.getEmail());
				req.setAttribute("noPassword", "±K½X¿ù»~¡C");
				failedView.forward(req, res);
				return;
			} else {
				req.setAttribute("noEmail", "±b¸¹¿ù»~");
				req.setAttribute("noPassword", "±K½X¿ù»~¡C");
				failedView.forward(req, res);
			}

		} else {
			// ï¿½iï¿½bï¿½ï¿½ , ï¿½Kï¿½Xï¿½ï¿½ï¿½Ä®ï¿½, ï¿½~ï¿½ï¿½ï¿½Hï¿½Uï¿½uï¿½@ï¿½j
			MemberService memSvc = new MemberService();
			HttpSession session = req.getSession();
			session.setAttribute("account", memSvc.findByEmail(email).getName()); // *ï¿½uï¿½@1: ï¿½~ï¿½bsessionï¿½ï¿½ï¿½ï¿½ï¿½wï¿½gï¿½nï¿½Jï¿½Lï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			session.setAttribute("id", memSvc.findByEmail(email).getMemberId());
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *ï¿½uï¿½@2: ï¿½Ý¬Ý¦ï¿½ï¿½Lï¿½Ó·ï¿½ï¿½ï¿½ï¿½ï¿½ (-->ï¿½pï¿½ï¿½ï¿½Ó·ï¿½ï¿½ï¿½ï¿½ï¿½:ï¿½hï¿½ï¿½ï¿½É¦Ü¨Ó·ï¿½ï¿½ï¿½ï¿½ï¿½)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/homepage/index.jsp"); // *ï¿½uï¿½@3: (-->ï¿½pï¿½Lï¿½Ó·ï¿½ï¿½ï¿½ï¿½ï¿½:ï¿½hï¿½ï¿½ï¿½É¦ï¿½login_success.jsp)
		}

	}
}
