package com.Alert.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.campAlert.model.*;

//@WebServlet("/backendLogin/CampAlert.do")
public class CampAlertServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		Integer id = Integer.parseInt(req.getParameter("Id"));
		Integer imgid = Integer.parseInt(req.getParameter("img"));
		CampAlertDAO dao = new CampAlertDAO();
		CampAlertVO vo = dao.findByPrimaryKey(id);

		try {
			switch (imgid) {
			case 1:
				out.write(vo.getPicture1());
				break;
			case 2:
				out.write(vo.getPicture2());
				break;
			default:
				out.write(vo.getPicture3());
			}

		} catch (Exception e) {
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		//刪除功能
		if ("delete".equals(action)) {
			Integer id = new Integer(req.getParameter("alertId"));
			CampAlertService alertSvc = new CampAlertService();
			alertSvc.deleteCampAlertDAO(id);
			String url = "/backendLogin/alert.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//查詢圖片功能
		
	}
}
