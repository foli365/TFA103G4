package com.members.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.ZonedDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.members.model.MemberService;
import com.members.model.MembersVO;

import oracle.sql.DATE;

@WebServlet("/resetPassword.do")
public class ResetPassword extends HttpServlet {
	static MemberService memSvc = new MemberService();
	private static String SECRET = "0zy^=Q5&nZpw#Cm'+?&TdlaB0=DeiV*>/x:Pv.amM\"NE;m4k/Mm{Sb;Qx[hN9hP!";
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
		java.util.Date date = java.util.Calendar.getInstance().getTime();
		if (!emailConfirm(email)) {
			req.setAttribute("noEmail", "查無此信箱");
			RequestDispatcher noEmail = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
			noEmail.forward(req, res);
		} else {
			MembersVO memVO = memSvc.findByEmail(email);
			try {
				Algorithm algorithm = Algorithm.HMAC256(SECRET);
				String token = JWT.create()
						.withIssuer("goCamping")
						.withSubject("passwordReset")
						.withKeyId(email)
						.withExpiresAt(Date.from(ZonedDateTime.now().plusMinutes(5).toInstant()))
						.sign(algorithm);
				MailService mailService = new MailService();
				String content = memVO.getName() + "您好:\n\t\t請在5分鐘內透過此連結重設密碼:\n\t\t"+
				"http://localhost:8081/"+req.getContextPath()+"/post/index.jsp";
				mailService.sendMail(email, "重設密碼", content);
				
			} catch (JWTCreationException e) {
				// TODO: handle exception
			}
		}
	}
}
