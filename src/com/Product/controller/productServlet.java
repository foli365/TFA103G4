package com.Product.controller;

import java.io.*;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mysql.cj.xdevapi.UpdateParams;
import com.Product.model.*;
import com.Product.model.ProductService;
import com.Product.model.ProductVO;


public class productServlet extends HttpServlet {
      

//	public void doGet(HttpServletRequest req, HttpServletResponse res) 
//				throws ServletException, IOException {
//		
//	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");		
		String action = req.getParameter("action");
		
		if("search".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("productno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer productno = null;
				try {
					productno = new Integer(str);
				} catch(Exception e) {
					errorMsgs.add("商品編號輸入不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneproduct(productno);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/product/selectOneproduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);
				
			}
		}
		
		
		
		
		if("getUpdate".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer productno = new Integer(req.getParameter("productno"));
				
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneproduct(productno);
				

				req.setAttribute("productVO", productVO);
				
				String url = "/product/update_product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);
			}
			
		}

		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
		
			try {
				
				Integer productno = new Integer(req.getParameter("productno").trim());
				
				String pname = req.getParameter("pname");
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} 
				
				Integer price = null;
			 try { 
			price = new Integer(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				price = 0;
				errorMsgs.add("請輸入價格");
			}
			 
			 Integer inventory = null;
			 try {
			 inventory = new Integer(req.getParameter("inventory").trim());
			 } catch (NumberFormatException e) {
				 inventory = 0;
				 errorMsgs.add("請輸入數量");
			 }
			 
			 String descript = req.getParameter("descript");
			 String descriptReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (descript == null || descript.trim().length() == 0) {
//					errorMsgs.add("商品介紹: 請勿空白");
					System.out.println(descript);
			 }
				
			 String psort  = req.getParameter("psort");
			 String psortReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			 	if (psort == null || pname.trim().length() == 0) {
					errorMsgs.add("商品類別: 請勿空白");
				 } 		
				
			 byte[] picture1 = null;
			 byte[] picture2 = null;
			 byte[] picture3 = null;
			 
			 try {
			 Collection<Part> parts = req.getParts();						 						 
			 
			 Part part1 = req.getPart("img1");
			 InputStream in1 = part1.getInputStream();
			 if (in1.available() != 0) {
				 in1.close();
				 picture1 = new byte[in1.available()];
				 in1.read(picture1);
				 in1.close();				 
			 } else {
				 errorMsgs.add("圖片1: 請勿空白");
			 }
			 
			 Part part2 = req.getPart("img2");
			 InputStream in2 = part2.getInputStream();
			 if(in2.available() != 0) {
				 in2.close();
				 picture2 = new byte[in2.available()];
				 in2.read(picture2);
				 in2.close();				 
			 } else {
				 errorMsgs.add("圖片2: 請勿空白");
			 }
			 
			 Part part3 = req.getPart("img3");
			 InputStream in3 = part3.getInputStream();
			 if(in3.available() != 0) {
				 in3.close();
				 picture3 = new byte[in3.available()];
				 in3.read(picture3);
				 in3.close();				 
			 } else {
				 errorMsgs.add("圖片3: 請勿空白");
			 }
			 
			 } catch(Exception e) {
				 e.printStackTrace();
			 }
			 
			 ProductVO productVO = new ProductVO();
			 productVO.setPname(pname);
			 productVO.setPrice(price);
			 productVO.setPsort(psort);			 
			 productVO.setDescript(descript);
			 productVO.setPicture1(picture1);
			 productVO.setPicture2(picture2);
			 productVO.setPicture3(picture3);
			
				 					 
			if (!errorMsgs.isEmpty()) {
			req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的productVO物件,也存入req
			RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
			failureView.forward(req, res);
			return; //程式中斷
					 }
			
		/***************************2.開始修改資料*****************************************/
			ProductService proSvc = new ProductService();
			productVO = proSvc.updateProduct(productno, pname, psort, price, inventory, descriptReg, picture1, picture2, picture3);
						
			req.setAttribute("productVO", productVO);
			String url = "/product/selectOneproduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		
			
		} catch(Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
			failureView.forward(req, res);
		}
	}
		
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String pname = req.getParameter("pname");
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} 
				
				Integer price = null;
			 try { 
			price = new Integer(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				price = 0;
				errorMsgs.add("請輸入價格");
			}
			 
			 

			 String psort  = req.getParameter("psort");
			 String psortReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (psort == null || pname.trim().length() == 0) {
					errorMsgs.add("商品類別: 請勿空白");
			 } 	
				
			 Integer inventory = null;
			 try {
			 inventory = new Integer(req.getParameter("inventory").trim());
			 } catch (NumberFormatException e) {
				 inventory = 0;
				 errorMsgs.add("請輸入數量");
			 }
			 
			 String descript = req.getParameter("descript");
			 String descriptReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (descript == null || descript.trim().length() == 0) {
					errorMsgs.add("商品介紹: 請勿空白");
			 }
				
			 byte[] picture1 = null;
			 byte[] picture2 = null;
			 byte[] picture3 = null;
			 
			 try {
			 Collection<Part> parts = req.getParts();						 						 
			 
			 Part part1 = req.getPart("img1");
			 InputStream in1 = part1.getInputStream();
			 if (in1.available() != 0) {
				 in1.close();
				 picture1 = new byte[in1.available()];
				 in1.read(picture1);
				 in1.close();				 
			 } else {
				 errorMsgs.add("圖片1: 請勿空白");
			 }
			 
			 Part part2 = req.getPart("img2");
			 InputStream in2 = part2.getInputStream();
			 if(in2.available() != 0) {
				 in2.close();
				 picture2 = new byte[in2.available()];
				 in2.read(picture2);
				 in2.close();				 
			 } else {
				 errorMsgs.add("圖片2: 請勿空白");
			 }
			 
			 Part part3 = req.getPart("img3");
			 InputStream in3 = part3.getInputStream();
			 if(in3.available() != 0) {
				 in3.close();
				 picture3 = new byte[in3.available()];
				 in3.read(picture3);
				 in3.close();				 
			 } else {
				 errorMsgs.add("圖片3: 請勿空白");
			 }
			 
			 } catch(Exception e) {
				 e.printStackTrace();
			 }
			 
			 ProductVO productVO = new ProductVO();
			 productVO.setPname(pname);
			 productVO.setPrice(price);
			 productVO.setPsort(psort);
			 productVO.setDescript(descript);
			 productVO.setPicture1(picture1);
			 productVO.setPicture2(picture2);
			 productVO.setPicture3(picture3);
			
				 					 
			if (!errorMsgs.isEmpty()) {
			req.setAttribute("productVO", productVO);
			RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
			failureView.forward(req, res);
			return; 
					 }	
			
	/***************************2.開始新增資料*****************************************/
			ProductService proSvc = new ProductService();
			productVO = proSvc.addProduct(pname, psort, price, inventory, descript, picture1, picture2, picture3);				 
		
					 
				
			 String url = "/product/select_product.jsp";
			 RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
				
		
		} catch(Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/product/addProduct.jsp");
			failureView.forward(req, res);
			return;
				
			}
		}
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer productno = new Integer (req.getParameter("productno"));

	/***************************2.開始刪除資料*****************************************/
				ProductService proSvc = new ProductService();
				proSvc .deleteProduct(productno);
				
				String url = "/product/productAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
}	
	
}