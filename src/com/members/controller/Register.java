package com.members.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.members.model.MemberService;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class Register extends HttpServlet {
	private static final String SECRET = "0zy^=Q5&nZpw#Cm'+?&TdlaB0=DeiV*>/x:Pv.amM\"NE;m4k/Mm{Sb;Qx[hN9hP!";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberService memSvc = new MemberService();
		String token = req.getParameter("token");
		DecodedJWT jwt = JWT.decode(token);
		String password = jwt.getClaim("password").asString();
		String name = jwt.getClaim("name").asString();
		String email = jwt.getSubject();
		if (memSvc.findByEmail(email)!=null) {
			req.setAttribute("exist", "���b���w�s�b");
			RequestDispatcher success = req.getRequestDispatcher("/register_and_login/validate_result.jsp");
			success.forward(req, res);
			System.out.println("1");
			return;
		}
		String path = getServletContext().getRealPath("/img/avatar.jpg");
		try {
			System.out.println("2");
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			System.out.println("3");
			JWTVerifier verifier = JWT
					.require(algorithm)
					.build();
			System.out.println("4");
			jwt = verifier.verify(token);
			System.out.println("5");
			memSvc.addMember(name, password, email, getPictureByteArray(path));
			HttpSession session = req.getSession();
			session.setAttribute("account", name);
			session.setAttribute("id", memSvc.findByEmail(email).getMemberId());
			req.setAttribute("success", "�}�q���\");
			RequestDispatcher success = req.getRequestDispatcher("/register_and_login/validate_result.jsp");
			success.forward(req, res);
		} catch (Exception e) {
			req.setAttribute("invalid", "���s���w����");
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/validate_result.jsp");
			failed.forward(req, res);
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("passwordConfirm");
		String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
		String email = req.getParameter("email");
		MemberService memSvc = new MemberService();
		if (memSvc.findByEmail(email) != null) {
			req.setAttribute("emailRepeat", "���q�l�l��w�Q���U�L");
			req.setAttribute("name", name);
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/register.jsp");
			failed.forward(req, res);
			return;
		} else if (!password.matches(passwordReg)) {
			req.setAttribute("pwordTooWeak", "�K�X���פ��o�p��8�B�ܤֶ����@�r��");
			req.setAttribute("name", name);
			req.setAttribute("email", email);
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/register.jsp");
			failed.forward(req, res);
			return;
		} else if (!password.equals(passwordConfirm.trim())) {
			req.setAttribute("passwordDiff", "���ˬd�K�X�P�T�{�K�X�O�_�ۦP");
			req.setAttribute("name", name);
			req.setAttribute("email", email);
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/register.jsp");
			failed.forward(req, res);
			return;
		} else {
			try {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, 10);
				java.util.Date exp = cal.getTime();
				Algorithm algorithm = Algorithm.HMAC256(SECRET);
				String token = JWT.create()
						.withSubject(email)
						.withClaim("password", bcryptHashString)
						.withClaim("name", name)
						.withExpiresAt(exp)
						.sign(algorithm);
				MailService mailService = new MailService();
				String content = "�˷R��" + name + "�z�n:\n\t�Цb10�������z�L���s���}�q�b��:\n\n"+
				"http://localhost:8081"+req.getContextPath()+"/account/register.do?token="+token;
				mailService.sendMail(email, "�b���}�q", content);
				req.setAttribute("success", "�бz�b10�������e���q�l�H�c�}�q�b��");
				RequestDispatcher success = req.getRequestDispatcher("/register_and_login/register.jsp");
				success.forward(req, res);
			} catch (JWTCreationException e) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO: handle exception
			}
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
