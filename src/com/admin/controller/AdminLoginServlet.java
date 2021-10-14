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
	 // 【取得使用者 帳號(account) 密碼(password)】
	    String adminaccount = req.getParameter("adminaccount");
	    String adminpassword = req.getParameter("adminpassword");

	    // 【檢查該帳號 , 密碼是否有效】
	    if (!allowUser(adminaccount, adminpassword)) {          //【帳號 , 密碼無效時】
	      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
	      out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/backendLogin/backendLogin.jsp>重新登入</A>");
	      out.println("</BODY></HTML>");
	    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
	      HttpSession session = req.getSession();
	      session.setAttribute("adminaccount", adminaccount);   //*工作1: 才在session內做已經登入過的標識
	      
	       try {                                                        
	         String location = (String) session.getAttribute("Location");
	         if (location != null) {
	           session.removeAttribute("Location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
	           res.sendRedirect(location);            
	           return;
	         }
	       }catch (Exception ignored) { }

	      res.sendRedirect(req.getContextPath()+"/backendLogin/home.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
	    }
	}

}
