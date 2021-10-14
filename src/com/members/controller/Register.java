package com.members.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MemberService;
import com.members.model.MembersVO;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Register extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
		
		String email = req.getParameter("email");
	
		MemberService memSvc = new MemberService();
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
		res.sendRedirect(req.getContextPath() +"/homepage/index.jsp");
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
