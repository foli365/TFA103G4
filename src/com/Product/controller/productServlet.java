package com.Product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Product.model.ProductService;
import com.Product.model.ProductVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
public class productServlet extends HttpServlet {

//	public void doGet(HttpServletRequest req, HttpServletResponse res) 
//				throws ServletException, IOException {
//		
//	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String s = req.getParameter("productno");
		System.out.println("action=" + action);

		if ("search".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("pname");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�W��");

				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				
				String pname = req.getParameter("pname");
				System.out.println(pname);
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				}

//				Integer productno = null;
//				try {
//					productno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("�ӫ~�s����J�����T");
//				}
					
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				

				ProductService proSvc = new ProductService();
//				ProductVO productVO = proSvc.getOneproduct(productno);
				ProductVO productVO = proSvc.getOneproductName(pname);
				if (productVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				req.setAttribute("productVO", productVO);
				String url = "/product/selectOneproduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� selectOneproduct.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);

			}
		}
		
//		if("search".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				String pname = req.getParameter("pname");
//				System.out.println(pname);
//				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (pname == null || pname.trim().length() == 0) {
//					errorMsgs.add("�ӫ~�W��: �ФŪť�");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
//					failureView.forward(req, res);
//					return;// �{�����_
//				}
//				
//				ProductService proSvc = new ProductService();
//				ProductVO productVO = proSvc.getOneproductName(pname);
//				
//				if (productVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
//					failureView.forward(req, res);
//					return;// �{�����_
//				}
//				
//				req.setAttribute("productVO", productVO);
//				String url = "/product/selectOneproduct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� selectOneproduct.jsp
//				successView.forward(req, res);
//				
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
//				failureView.forward(req, res);
//			}
//			
//			
//		}
//		

		if ("getforUpdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
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
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) {
			System.out.println("hello");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer productno = new Integer(req.getParameter("productno").trim());

				String pname = req.getParameter("pname");
				System.out.println(pname);
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				}

				Integer price = null;

				try {
					price = new Integer(req.getParameter("price").trim());
			System.out.println(price);
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("�п�J����");
				}

				Integer inventory = null;
				try {
					inventory = new Integer(req.getParameter("inventory").trim());
			 System.out.println(inventory);
				} catch (NumberFormatException e) {
					inventory = 0;
					errorMsgs.add("�п�J�ƶq");
				}

				Integer admin_id = null;
				try {
					admin_id = new Integer(req.getParameter("admin_id").trim());
//			System.out.println(admin_id);
				} catch (NumberFormatException e) {
					errorMsgs.add("�W�[�̤����");
				}

				Integer situation = null;
				try {
					situation = new Integer(req.getParameter("situation").trim());
//			 System.out.println(inventory);
				} catch (NumberFormatException e) {
					errorMsgs.add("���A�����");
				}

				String descript = req.getParameter("descript");
//			 System.out.println(descript);
				String descriptReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (descript == null || descript.trim().length() == 0) {
					errorMsgs.add("�ӫ~����: �ФŪť�");
				}

				String psort = req.getParameter("psort");
				String psortReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (psort == null || pname.trim().length() == 0) {
					errorMsgs.add("�ӫ~���O: �ФŪť�");
				}

				byte[] picture1 = null;
				byte[] picture2 = null;
				byte[] picture3 = null;

				try {
					Collection<Part> parts = req.getParts();

					Part part1 = req.getPart("img1");
					InputStream in1 = part1.getInputStream();
//			 System.out.println("part1="+part1);
//			 System.out.println("in1="+in1);
					if (in1.available() != 0) {
						in1.close();
						picture1 = new byte[in1.available()];
						in1.read(picture1);
						in1.close();

					} else {
						errorMsgs.add("�Ϥ�1: �ФŪť�");
					}

					Part part2 = req.getPart("img2");
					InputStream in2 = part2.getInputStream();
					if (in2.available() != 0) {
						in2.close();
						picture2 = new byte[in2.available()];
						in2.read(picture2);
						in2.close();
					} else {
						errorMsgs.add("�Ϥ�2: �ФŪť�");
					}

					Part part3 = req.getPart("img3");
					InputStream in3 = part3.getInputStream();
					if (in3.available() != 0) {
						in3.close();
						picture3 = new byte[in3.available()];
						in3.read(picture3);
						in3.close();
					} else {
						errorMsgs.add("�Ϥ�3: �ФŪť�");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				ProductVO productVO = new ProductVO();
				productVO.setProductno(productno);
				productVO.setPname(pname);
				productVO.setPsort(psort);
				productVO.setPrice(price);
				productVO.setInventory(inventory);
				productVO.setAdmin_id(admin_id);
				productVO.setSituation(situation);
				productVO.setDescript(descript);
				productVO.setPicture1(picture1);
				productVO.setPicture2(picture2);
				productVO.setPicture3(picture3);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // �t����J�榡���~��productVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				ProductService proSvc = new ProductService();
				productVO = proSvc.updateProduct(productno, pname, psort, price, inventory, admin_id, situation,
						descript, picture1, picture2, picture3);

				req.setAttribute("productVO", productVO);
				String url = "/product/selectOneproduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pname = req.getParameter("pname");
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				}

				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("�п�J����");
				}

				String psort = req.getParameter("psort");
				String psortReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (psort == null || pname.trim().length() == 0) {
//					errorMsgs.add("�ӫ~���O: �ФŪť�");
				}

				Integer inventory = null;
				try {
					inventory = new Integer(req.getParameter("inventory").trim());
				} catch (NumberFormatException e) {
					inventory = 0;
					errorMsgs.add("�п�J�ƶq");
				}

				Integer admin_id = null;
				try {
					admin_id = new Integer(req.getParameter("admin_id").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�W�[�H�����ର��");
				}

				Integer situation = null;
				try {
					situation = new Integer(req.getParameter("situation").trim());
				} catch (NumberFormatException e) {
					inventory = 0;
					errorMsgs.add("���A�п�J");
				}

				String descript = req.getParameter("descript");
				String descriptReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (descript == null || descript.trim().length() == 0) {
					errorMsgs.add("�ӫ~����: �ФŪť�");
				}

				List<byte[]> picList = new ArrayList<>();

				try {
					Collection<Part> parts = req.getParts();

					for (Part part : parts) {
						String fileName = part.getSubmittedFileName();
						if (fileName != null && fileName.endsWith("jpg")) {
							InputStream in1 = part.getInputStream();
							if (in1.available() != 0) {
								byte[] picture = new byte[in1.available()];
								in1.read(picture);
								in1.close();
								picList.add(picture);
							} else {
								errorMsgs.add("�Ϥ�: �ФŪť�");
							}
						}
					}

//			 Part part1 = req.getPart("img1");
//			 InputStream in1 = part1.getInputStream();
//			 System.out.println("part1="+part1);
//			 System.out.println("in1="+in1);
//			 System.out.println();
//			 if (in1.available() != 0) {
//				 in1.close();
//				 picture1 = new byte[in1.available()];
//				 in1.read(picture1);
//				 in1.close();
//							 
//			 } else {
//				 errorMsgs.add("�Ϥ�: �ФŪť�");
//			 }

//			 Part part2 = req.getPart("img2");
//			 InputStream in2 = part2.getInputStream();
//			 if(in2.available() != 0) {
//				 in2.close();
//				 picture2 = new byte[in2.available()];
//				 in2.read(picture2);
//				 in2.close();
//				 
//			 } else {
//				 errorMsgs.add("�Ϥ�: �ФŪť�");
//			 }
//			 
//			 Part part3 = req.getPart("img3");
//			 InputStream in3 = part3.getInputStream();
//			 if(in3.available() != 0) {
//				 in3.close();
//				 picture3 = new byte[in3.available()];
//				 in3.read(picture3);
//				 in3.close();
//				 				 
//			 } 
//			   else {
//				 errorMsgs.add("�Ϥ�: �ФŪť�");
//			 }

				} catch (Exception e) {
					e.printStackTrace();
				}

//				System.out.println("Size = " + picList.size());

				byte[] picture1 = picList.get(0);
				byte[] picture2 = picList.get(1);
				byte[] picture3 = picList.get(2);

				ProductVO productVO = new ProductVO();
				productVO.setPname(pname);
				productVO.setPsort(psort);
				productVO.setPrice(price);
				productVO.setInventory(inventory);
				productVO.setAdmin_id(admin_id);
				productVO.setSituation(situation);
				productVO.setDescript(descript);
				productVO.setPicture1(picture1);
				productVO.setPicture2(picture2);
				productVO.setPicture3(picture3);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� *****************************************/
				ProductService proSvc = new ProductService();
				productVO = proSvc.addProduct(pname, psort, price, inventory, admin_id, situation, descript, picture1,
						picture2, picture3);

				String url = "/product/selectAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);
				return;

			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer productno = new Integer(req.getParameter("productno"));

				/*************************** 2.�}�l�R����� *****************************************/
				ProductService proSvc = new ProductService();
				proSvc.deleteProduct(productno);

				String url = "/product/selectAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);
			}

		}

	}

}