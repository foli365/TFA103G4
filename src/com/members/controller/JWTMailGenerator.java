package com.members.controller;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.members.model.MemberService;
import com.members.model.MembersVO;

@WebServlet("/resetPassword.do")
public class JWTMailGenerator extends HttpServlet {
	static MemberService memSvc = new MemberService();
	private static final String SECRET = "0zy^=Q5&nZpw#Cm'+?&TdlaB0=DeiV*>/x:Pv.amM\"NE;m4k/Mm{Sb;Qx[hN9hP!";
	private static String SUB = "passwordReset";
	private static String ISSUER = "goCamping";
	protected boolean emailConfirm(String email) {
		MembersVO memVO = memSvc.findByEmail(email);
		if (memVO == null) {
			return false;
		} else {
			return true;
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = req.getParameter("token");
		try {
			DecodedJWT jwt = JWT.decode(token);
			String issuer = jwt.getIssuer();
			String subject = jwt.getSubject();
			String email = jwt.getKeyId();
			String password = memSvc.findByEmail(email.toString()).getPassword().substring(7);
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(issuer)
					.withSubject(subject)
					.withClaim("password", password)
					.build();
			jwt = verifier.verify(token);
			req.setAttribute("email", email);
			RequestDispatcher success = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
			success.forward(req, res);
		} catch (JWTVerificationException e) {
			req.setAttribute("invalid", "���s���w����");
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
			failed.forward(req, res);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 10);
		java.util.Date exp = cal.getTime();
		if (!emailConfirm(email)) {
			req.setAttribute("noEmail", "�H�e���ѡA�Э��s��J�H�c�a�}");
			RequestDispatcher noEmail = req.getRequestDispatcher("/register_and_login/search_by_email.jsp");
			noEmail.forward(req, res);
		} else {
			MembersVO memVO = memSvc.findByEmail(email);
			String password = memVO.getPassword();
			try {
				Algorithm algorithm = Algorithm.HMAC256(SECRET);
				String token = JWT.create()
						.withKeyId(email)
						.withIssuer(ISSUER)
						.withSubject(SUB)
						.withClaim("password", password.substring(7))
						.withExpiresAt(exp)
						.sign(algorithm);
				System.out.println(token);
				MailService mailService = new MailService();
				String content = memVO.getName() + "�z�n:\n\t�Цb10�������z�L���s�����]�K�X:\n\n"+
				"http://localhost:8081"+req.getContextPath()+"/resetPassword.do?token="+token;
				mailService.sendMail(email, "���]�K�X", content);
				req.setAttribute("success", "�Щ�10�������I���l�󤤪����}");
				RequestDispatcher success = req.getRequestDispatcher("/register_and_login/search_by_email.jsp");
				success.forward(req, res);
			} catch (JWTCreationException e) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
