package com.camporder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adminList.model.AdminListVO;
import com.adminList.model.AdminService;
import com.camporder.model.CampOrderService;
import com.camporder.model.CampOrderVO;
import com.campsite.model.*;

public class CampOrderBackendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String campOrderId= req.getParameter("campOrderId");
				if (campOrderId== null || (campOrderId.trim()).length() == 0) {
					errorMsgs.add("請輸入露營訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/campOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer campOrder = null;
				try {
					campOrder = new Integer(campOrderId);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/campOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				CampOrderService coSvc = new CampOrderService();
				CampOrderVO campOrderVO = coSvc.getOneCampOrder(campOrder);
				if (campOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/campOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("campOrderVO",campOrderVO); // 資料庫取出的empVO物件,存入req
				String url = "/backendLogin/campOrder-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backendLogin/campOrder.jsp");
				failureView.forward(req, res);
			}
		}	
		
	}

}
