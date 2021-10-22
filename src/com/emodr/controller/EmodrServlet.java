package com.emodr.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.emodr.model.*;

public class EmodrServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// 為了要接收來自select_page.jsp的請求參數

//更新
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmodr.jsp的請求

			List<String> errorMsgs = new LinkedList<String>(); // 建立一個errorMsgs集合來放錯誤字串訊息
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);// 設定屬性將錯誤碼送回

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer emodr_id = new Integer(req.getParameter("emodr_id"));// 因為req.getParameter("emodr_id")傳過來的是字串，所以要用Integer的建構子轉為Integer

				/*************************** 2.開始查詢資料 ****************************************/
				EmodrService emodrSvc = new EmodrService();
				EmodrVO emodrVO = emodrSvc.getOneEmodr(emodr_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("emodrVO", emodrVO); // 資料庫取出的emodrVO物件,存入req
				String url = "/emodr/update_emodr_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emodr_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
				failureView.forward(req, res);
			}
		}
//更新
		if ("update".equals(action)) {// 來自update_emodr_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer emodr_id = new Integer(req.getParameter("emodr_id").trim());
				Integer member_id = new Integer(req.getParameter("member_id").trim());

				java.sql.Date emodr_date = null;
				try {
					emodr_date = java.sql.Date.valueOf(req.getParameter("emodr_date").trim());
				} catch (IllegalArgumentException e) {
					emodr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String receipient = req.getParameter("receipient");
				String receipientReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (receipient == null || receipient.trim().length() == 0) {
					errorMsgs.add("收貨人: 請勿空白");
				} else if (!receipient.trim().matches(receipientReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收貨人: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String addr = req.getParameter("addr").trim();
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("收貨地址請勿空白");
				}

				String mobile = req.getParameter("mobile").trim();
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("收貨電話請勿空白");
				}

				Double totalprice = null;
				try {
					totalprice = new Double(req.getParameter("totalprice").trim());
				} catch (NumberFormatException e) {
					totalprice = 0.0;
					errorMsgs.add("總價請填數字.");
				}

				Boolean emodr_status = null;
				try {
					emodr_status = new Boolean(req.getParameter("emodr_status").trim());
				} catch (Exception e) {
					errorMsgs.add("訂單狀態請填1或0.");
				}

				EmodrVO emodrVO = new EmodrVO();
				emodrVO.setEmodr_id(emodr_id);
				emodrVO.setMember_id(member_id);
				emodrVO.setEmodr_date(emodr_date);
				emodrVO.setReceipient(receipient);
				emodrVO.setAddr(addr);
				emodrVO.setMobile(mobile);
				emodrVO.setTotalprice(totalprice);
				emodrVO.setEmodr_status(emodr_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("emodrVO", emodrVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/emodr/update_emodr_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmodrService emodrSvc = new EmodrService();
				emodrSvc.updateEmodr(emodr_id, member_id, emodr_date, receipient, addr, mobile, totalprice,
						emodr_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("emodrVO", emodrVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emodr/listOneEmodr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/update_emodr_input.jsp");
				failureView.forward(req, res);
			}
		}
//刪除(等於不顯示某一列資料)		
		if ("delete".equals(action)) {// 來自listAllEmodr.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer emodr_id = new Integer(req.getParameter("emodr_id"));

				/*************************** 2.開始查詢資料 ***************************************/
				EmodrService emodrSvc = new EmodrService();
				List<EmodrVO>list = emodrSvc.notDisplay(emodr_id);////
				req.setAttribute("list", list);////

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/emodr/listWantEmodr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");//錯誤訊息轉交回送出刪除的來源網頁
				failureView.forward(req, res);
		
			}
		} 
		
//訂單的新增(購物車結帳成立訂單使用到這塊)		
		if ("insert".equals(action)) {// 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				Integer emodr_id = new Integer(req.getParameter("emodr_id").trim());
				Integer member_id = new Integer(req.getParameter("member_id").trim());

				java.sql.Date emodr_date = null;
				try {
					emodr_date = java.sql.Date.valueOf(req.getParameter("emodr_date").trim());
				} catch (IllegalArgumentException e) {
					emodr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String receipient = req.getParameter("receipient");
				String receipientReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (receipient == null || receipient.trim().length() == 0) {
					errorMsgs.add("收貨人: 請勿空白");
				} else if (!receipient.trim().matches(receipientReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收貨人: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String addr = req.getParameter("addr").trim();
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("收貨地址請勿空白");
				}

				String mobile = req.getParameter("mobile").trim();
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("收貨電話請勿空白");
				}

				Double totalprice = null;
				try {
					totalprice = new Double(req.getParameter("totalprice").trim());
				} catch (NumberFormatException e) {
					totalprice = 0.0;
					errorMsgs.add("總價請填數字.");
				}

				Boolean emodr_status = null;
				try {
					emodr_status = new Boolean(req.getParameter("emodr_status").trim());
				} catch (Exception e) {
					errorMsgs.add("訂單狀態請填1或0.");
				}
				
				EmodrVO emodrVO = new EmodrVO();
				emodrVO.setMember_id(member_id);
				emodrVO.setEmodr_date(emodr_date);
				emodrVO.setReceipient(receipient);
				emodrVO.setAddr(addr);
				emodrVO.setMobile(mobile);
				emodrVO.setTotalprice(totalprice);
				emodrVO.setEmodr_status(emodr_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("emodrVO", emodrVO); // 含有輸入格式錯誤的emodrVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/eshop/pages/Checkout.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 *****************************************/
				EmodrService emodrSvc = new EmodrService();
				emodrSvc.addEmodr(member_id, emodr_date, receipient, addr, mobile, totalprice,
						emodr_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
				req.setAttribute("emodrVO", emodrVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emodr/listAllEmodrForCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllEmodr.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/addEmodr.jsp");
//				failureView.forward(req, res);
			}
		}
		
		
	}

}