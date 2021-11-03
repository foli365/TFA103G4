package com.campAlert.controller;

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
import com.camporder.model.CampOrderService;
import com.camporder.model.CampOrderVO;

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
			Integer id = new Integer(req.getParameter("Id"));
			CampAlertService alertSvc = new CampAlertService();
			alertSvc.deleteCampAlertDAO(id);
			String url = "/backendLogin/alert.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//查詢圖片功能
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String alertId = req.getParameter("alertId");
				if (alertId == null || (alertId.trim()).length() == 0) {
					errorMsgs.add("請輸入營地檢舉編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer alert = null;
				try {
					alert= new Integer(alertId);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				CampAlertService alertSvc = new CampAlertService();
				CampAlertVO campAlertVO = alertSvc.getOneEmp(alert);
				if (campAlertVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campAlertVO",campAlertVO);
				String url = "/backendLogin/campAlertListone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
