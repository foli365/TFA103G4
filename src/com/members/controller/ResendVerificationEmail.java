package com.members.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@WebServlet("/resend")
public class ResendVerificationEmail extends HttpServlet {
	private static final String SECRET = "0zy^=Q5&nZpw#Cm'+?&TdlaB0=DeiV*>/x:Pv.amM\"NE;m4k/Mm{Sb;Qx[hN9hP!";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		DecodedJWT jwt = JWT.decode(json);
		String password = jwt.getClaim("password").asString();
		String name = jwt.getClaim("name").asString();
		String email = jwt.getSubject();
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 10);
			java.util.Date exp = cal.getTime();
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			String newtoken = JWT.create().withSubject(email).withClaim("password", password).withClaim("name", name)
					.withExpiresAt(exp).sign(algorithm);
			MailService mailService = new MailService();
			String content = "親愛的" + name + "您好:\n\t請在10分鐘內透過此連結開通帳號:\n\n" + "http://localhost:8081"
					+ req.getContextPath() + "/account/register.do?token=" + newtoken;
			mailService.sendMail(email, "帳號開通", content);
			req.setAttribute("success", "帳號開通信已寄出，請確認");
			RequestDispatcher success = req.getRequestDispatcher("/register_and_login/validate_result.jsp");
			success.forward(req, resp);
		} catch (JWTCreationException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
