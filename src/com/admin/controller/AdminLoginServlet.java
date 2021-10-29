package com.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	  protected boolean allowUser(String adminaccount, String adminpassword) {
		    if ("1001".equals(adminaccount) && "jacky0229".equals(adminpassword))
		      return true;
		    else
		      return false;
		  }

	

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = res.getWriter();
	 // �i���o�ϥΪ� �b��(account) �K�X(password)�j
	    String adminaccount = req.getParameter("adminaccount");
	    String adminpassword = req.getParameter("adminpassword");

	    // �i�ˬd�ӱb�� , �K�X�O�_���ġj
	    if (!allowUser(adminaccount, adminpassword)) {          //�i�b�� , �K�X�L�Įɡj
	      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	      out.println("<BODY>�A���b�� , �K�X�L��!<BR>");
	      out.println("�Ы������s�n�J <A HREF="+req.getContextPath()+"/backendLogin/backendLogin.jsp>���s�n�J</A>");
	      out.println("</BODY></HTML>");
	    }else {                                       //�i�b�� , �K�X���Į�, �~���H�U�u�@�j
	      HttpSession session = req.getSession();
	      session.setAttribute("adminaccount", adminaccount);   //*�u�@1: �~�bsession�����w�g�n�J�L������
	      
	       try {                                                        
	         String location = (String) session.getAttribute("Location");
	         if (location != null) {
	           session.removeAttribute("Location");   //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
	           res.sendRedirect(location);            
	           return;
	         }
	       }catch (Exception ignored) { }

	      res.sendRedirect(req.getContextPath()+"/backendLogin/home.jsp");  //*�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
	    }
	}

}
