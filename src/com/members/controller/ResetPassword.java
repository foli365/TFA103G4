package com.members.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.algorithms.Algorithm;
import com.members.model.MemberService;
import com.members.model.MembersVO;

@WebServlet("/resetPassword.do")
public class ResetPassword extends HttpServlet {
	static MemberService memSvc = new MemberService();
	private static boolean emailConfirm(String email) {
		try {
			memSvc.findByEmail(email);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		if (!emailConfirm(email)) {
			req.setAttribute("noEmail", "查無此信箱");
			RequestDispatcher noEmail = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
			noEmail.forward(req, res);
		} else {
			MembersVO memVO = memSvc.findByEmail(email);
			try {
				Algorithm algorithm = Algorithm.HMAC256(memVO.getMemberId() + memVO.getEmail());				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
}
