package com.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
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
		
		if ("update".equals(action)) {
			try {
				Integer postid = new Integer(req.getParameter("postId").trim());
				String title = req.getParameter("title");
				String article = req.getParameter("article");
				
				PostVO postVO = new PostVO();
				postVO.setPostId(postid);
				postVO.setTitle(title);
				postVO.setArticle(article);
				
				PostService postSvc = new PostService();
				postSvc.updatePost(postid, title, article);
				
				res.sendRedirect("index.jsp");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
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
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer postId = new Integer(req.getParameter("postId"));
				
				/***************************2.開始刪除資料***************************************/
				PostService postSvc = new PostService();
				postSvc.delete(postId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				
				res.sendRedirect("index.jsp");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
