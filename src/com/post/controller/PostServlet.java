package com.post.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.post.model.PostService;
import com.post.model.PostVO;

public class PostServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("insert".equals(action)) {
			try {
				String article = req.getParameter("article");
				String title = req.getParameter("title");
				
				PostVO postVO = new PostVO();
				postVO.setArticle(article);
				postVO.setTitle(title);
				
				PostService postSvc = new PostService();
				postSvc.addPost(1, title, article);
				
				res.sendRedirect("index.jsp");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}
}
