package com.camprelease.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.camprelease.model.*;
import com.facilities.model.*;
import com.plan.model.*;

@MultipartConfig
public class CampReleaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("getOne_For_Display".equals(action)) { // 來自Select_Page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("campId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入營地編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer campId = null;
				try {
					campId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("營地編號格式有錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CampReleaseService campreleaseSvc = new CampReleaseService();
				CampReleaseVO campreleaseVO = campreleaseSvc.getOneCampRelease(campId);
				if (campreleaseVO == null) {
					errorMsgs.add("查無資料");
				}
				
				PlanService planSvc = new PlanService();
				PlanVO planVO = planSvc.getCampId(campId);
				if (planVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campreleaseVO", campreleaseVO); // 資料庫取出的campreleaseVO物件,存入req
				req.setAttribute("planVO", planVO); // 資料庫取出的campreleaseVO物件,存入req
				
				String url = "/camprelease/listOneRel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRel.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listCamp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer campId = new Integer(req.getParameter("campId"));

				/*************************** 2.開始查詢資料 ****************************************/
				CampReleaseService campreleaseSvc = new CampReleaseService();
				CampReleaseVO campreleaseVO = campreleaseSvc.getOneCampRelease(campId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("campreleaseVO", campreleaseVO); // 資料庫取出的campaddVO物件,存入req
				String url = "/camprelease/updateCampRel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateAddCamp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listCampRel.jsp");
				failureView.forward(req, res);
			}
		}
		
//	if("getUpdate".equals(action)) {
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				Integer productno = new Integer(req.getParameter("productno"));
//				
//				ProductService proSvc = new ProductService();
//				ProductVO productVO = proSvc.getOneproduct(productno);
//				
//
//				req.setAttribute("productVO", productVO);
//				
//				String url = "/product/update_product.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/product/selectAll.jsp");
//				failureView.forward(req, res);
//			}
//			
//		}

		if ("update".equals(action)) { // 來自updateCampRel.jsp的請求
			Map<String, String> errorMsgs = new HashMap<String, String>();
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer campId = new Integer(req.getParameter("campId").trim());

				Integer memberId = new Integer(req.getParameter("memberId").trim());
				
				String campName = req.getParameter("campName");
				String campNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (campName == null || campName.trim().length() == 0) {
					errorMsgs.put("campNameError","營地名稱: 請勿空白");
				} else if (!campName.trim().matches(campNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("campNameError","營地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String location = req.getParameter("location").trim();
				String locationReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,30}$";
				if (location == null || location.trim().length() == 0) {
					errorMsgs.put("locationError","地址請勿空白");
				} else if (!location.trim().matches(locationReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("locationError","地址: 只能是中、英文字母、數字和_ , 且長度必需在10到30之間");
				}

				Double latitude = null;
				try {
					latitude = new Double(req.getParameter("latitude").trim());
				} catch (NumberFormatException e) {
					latitude = 0.0;
					errorMsgs.put("latitudeError","經度請填數字.");
				}

				Double longtitude = null;
				try {
					longtitude = new Double(req.getParameter("longtitude").trim());
				} catch (NumberFormatException e) {
					longtitude = 0.0;
					errorMsgs.put("longtitudeError","緯度請填數字.");
				}

				String campDescription = req.getParameter("campDescription").trim();
				if (campDescription == null || campDescription.trim().length() == 0) {
					errorMsgs.put("campDescriptionError","營地介紹請填寫");
				} 

				Integer campPrice = null;
				try {
					campPrice = new Integer(req.getParameter("campPrice").trim());
				} catch (NumberFormatException e) {
					campPrice = 0;
					errorMsgs.put("campPriceError","價錢請填數字");
				}

				java.sql.Timestamp listedTime = null;
				try {
					listedTime = java.sql.Timestamp.valueOf(req.getParameter("listedTime").trim());
				} catch (IllegalArgumentException e) {
					listedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.put("listedTimeError","請輸入日期!");
				}
				
//				InputStream in1 = req.getPart("picture1").getInputStream();
//				byte[] picture1 = null;
//				if (in1.available() != 0) {
//					picture1 = new byte[in1.available()];
//					in1.read(picture1);
//					in1.close();
//				} else {
//					CampsiteService campsiteSvc = new CampsiteService();
//					picture1 = campsiteSvc.getOneCampsite(campId).getPicture1();
//				}

				
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					String filename = getFileNameFromPart(part);
					if (filename != null && part.getContentType() != null) {
						InputStream in1 = req.getPart("picture1").getInputStream();
						byte[] picture1 = null;

						if (in1.available() != 0) {
							picture1 = new byte[in1.available()];
							in1.read(picture1);
							in1.close();
						} else {
							CampReleaseService campreleaseSvc = new CampReleaseService();
							picture1 = campreleaseSvc.getOneCampRelease(campId).getPicture1();
						}
						InputStream in2 = req.getPart("picture2").getInputStream();
						byte[] picture2 = null;

						if (in2.available() != 0) {
							picture2 = new byte[in2.available()];
							in2.read(picture2);
							in2.close();
						} else {
							picture2 = null;
						}
						InputStream in3 = req.getPart("picture3").getInputStream();
						byte[] picture3 = null;

						if (in3.available() != 0) {
							picture3 = new byte[in3.available()];
							in3.read(picture3);
							in3.close();
						} else {
							picture3 = null;
						}
						InputStream in4 = req.getPart("picture4").getInputStream();
						byte[] picture4 = null;

						if (in4.available() != 0) {
							picture4 = new byte[in4.available()];
							in4.read(picture4);
							in4.close();
						} else {
							picture4 = null;
						}
						InputStream in5 = req.getPart("picture5").getInputStream();
						byte[] picture5 = null;

						if (in5.available() != 0) {
							picture5 = new byte[in5.available()];
							in5.read(picture5);
							in5.close();
						} else {
							picture5 = null;
						}
						
//				Part part1 = req.getPart("picture1");
//				InputStream in1 = part1.getInputStream();
//				byte[] picture1 = new byte[in1.available()];
//				in1.read(picture1);
//				in1.close();
////					picture1 = new byte[in1.available()];
////					in1.read(picture1);
////					in1.close();
////				} else {
////					CampReleaseService campreleaseSvc = new CampReleaseService();
////					picture1 = campreleaseSvc.getOneCampRelease(campId).getPicture1();
////				}
//				Part part2 = req.getPart("picture2");
//				InputStream in2 = part2.getInputStream();
//				byte[] picture2 = new byte[in2.available()];
//				in2.read(picture2);
//				in2.close();
////				if (in2.available() != 0) {
////					picture2 = new byte[in2.available()];
////					in2.read(picture2);
////					in2.close();
////				} else {
////					CampReleaseService campreleaseSvc = new CampReleaseService();
////					picture2 = campreleaseSvc.getOneCampRelease(campId).getPicture2();
////				}
//				Part part3 = req.getPart("picture3");
//				InputStream in3 = part3.getInputStream();
//				byte[] picture3 = new byte[in3.available()];
//				in3.read(picture3);
//				in3.close();
//				
//				Part part4 = req.getPart("picture4");
//				InputStream in4 = part4.getInputStream();
//				byte[] picture4 = new byte[in4.available()];
//				in4.read(picture4);
//				in4.close();
//				
//				Part part5 = req.getPart("picture5");
//				InputStream in5 = part5.getInputStream();
//				byte[] picture5 = new byte[in5.available()];
//				in5.read(picture5);
//				in5.close();
//				
//				InputStream in3 = req.getPart("picture3").getInputStream();
//				byte[] picture3 = null;
//				if (in3.available() != 0) {
//					picture3 = new byte[in3.available()];
//					in3.read(picture3);
//					in3.close();
//				} else {
//					CampReleaseService campreleaseSvc = new CampReleaseService();
//					picture3 = campreleaseSvc.getOneCampRelease(campId).getPicture3();
//				}
//				InputStream in4 = req.getPart("picture4").getInputStream();
//				byte[] picture4 = null;
//				if (in4.available() != 0) {
//					picture4 = new byte[in4.available()];
//					in4.read(picture4);
//					in4.close();
//				} else {
//					CampReleaseService campreleaseSvc = new CampReleaseService();
//					picture4 = campreleaseSvc.getOneCampRelease(campId).getPicture4();
//				}
//				InputStream in5 = req.getPart("picture5").getInputStream();
//				byte[] picture5 = null;
//				if (in5.available() != 0) {
//					picture5 = new byte[in1.available()];
//					in5.read(picture5);
//					in5.close();
//				} else {
//					CampReleaseService campreleaseSvc = new CampReleaseService();
//					picture5 = campreleaseSvc.getOneCampRelease(campId).getPicture5();
//				}
						Integer facilitiesId = new Integer(req.getParameter("facilitiesId").trim());
						
						String strbbq = req.getParameter("bbq");
						Integer bbq = null;
						if(strbbq == null) {
							bbq = 0;
						} else {
							bbq = new Integer(strbbq);
						}
						
						String strwifi = req.getParameter("wifi");
						Integer wifi = null;
						if(strwifi == null) {
							wifi = 0;
						} else {
							wifi = new Integer(strwifi);
						}
						
						String strnosmoke = req.getParameter("nosmoke");
						Integer nosmoke = null;
						if(strnosmoke == null) {
							nosmoke = 0;
						} else {
							nosmoke = new Integer(strnosmoke);
						}
						
						String strpets = req.getParameter("pets");
						Integer pets = null;
						if(strpets == null) {
							pets = 0;
						} else {
							pets = new Integer(strpets);
						}		
						
				
				CampReleaseVO campreleaseVO = new CampReleaseVO();
				campreleaseVO.setCampId(campId);
				campreleaseVO.setMemberId(memberId);
				campreleaseVO.setCampName(campName);
				campreleaseVO.setLocation(location);
				campreleaseVO.setLatitude(latitude);
				campreleaseVO.setLongtitude(longtitude);
				campreleaseVO.setCampDescription(campDescription);
				campreleaseVO.setCampPrice(campPrice);
				campreleaseVO.setListedTime(listedTime);
				campreleaseVO.setPicture1(picture1);
				campreleaseVO.setPicture2(picture2);
				campreleaseVO.setPicture3(picture3);
				campreleaseVO.setPicture4(picture4);
				campreleaseVO.setPicture5(picture5);
				
				FacilitiesVO facilitiesVO = new FacilitiesVO();	
//				facilitiesVO.setFacilitiesId(facilitiesId);
				facilitiesVO.setBbq(bbq);
				facilitiesVO.setWifi(wifi);
				facilitiesVO.setNosmoke(nosmoke);
				facilitiesVO.setPets(pets);

//				try {
//					campaddVO.setPicture1(getPictureByteArray(picture1));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("campreleaseVO", campreleaseVO); // 含有輸入格式錯誤的campreleaseVO物件,也存入req
							
							req.setAttribute("facilitiesVO", facilitiesVO);
							RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updateCampRel.jsp");
							failureView.forward(req, res);
							return; // 程式中斷
						}

						/*************************** 2.開始修改資料 *****************************************/
//						CampReleaseService campreleaseSvc = new CampReleaseService();
//						campreleaseVO = campreleaseSvc.updateCampRelease(campName, location, latitude, longtitude,
//								campDescription, campPrice, listedTime, picture1, picture2, picture3, picture4,
//								picture5,campId);
						
						CampReleaseService campreleaseSvc = new CampReleaseService();
						campreleaseVO = campreleaseSvc.updateCampRelease(campId, memberId, campName, location, latitude, longtitude,
								campDescription, campPrice, listedTime, picture1, picture2, picture3, picture4,
								picture5);
						
						FacilitiesService facilitiesSvc = new FacilitiesService();
						facilitiesVO = facilitiesSvc.updateFacilities(campId, bbq, wifi, nosmoke, pets, facilitiesId);

						/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
						req.setAttribute("campreleaseVO", campreleaseVO); // 資料庫update成功後,正確的的campaddVO物件,存入req
						req.setAttribute("facilitiesVO", facilitiesVO);
						String url = "/camprelease/listOneRel.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRel.jsp
						successView.forward(req, res);
					}
				}
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("updateError","修改資料失敗");
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updateCampRel.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addCampRel.jsp的請求
            Map<String, String> errorMsgs = new HashMap<String, String>();
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer memberId = new Integer(req.getParameter("memberId").trim());
				
				String campName = req.getParameter("campName");
				String campNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (campName == null || campName.trim().length() == 0) {
					errorMsgs.put("campNameError","營地名稱: 請勿空白");
				} else if (!campName.trim().matches(campNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("campNameError","營地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String location = req.getParameter("location").trim();
				String locationReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,30}$";
				if (location == null || location.trim().length() == 0) {
					errorMsgs.put("locationError","地址請勿空白");
				} else if (!location.trim().matches(locationReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("locationError","地址: 只能是中、英文字母、數字和_ , 且長度必需在10到30之間");
				}

				Double latitude = null;
				try {
					latitude = new Double(req.getParameter("latitude").trim());
				} catch (NumberFormatException e) {
					latitude = 0.0;
					errorMsgs.put("latitudeError","經度請填數字.");
				}

				Double longtitude = null;
				try {
					longtitude = new Double(req.getParameter("longtitude").trim());
				} catch (NumberFormatException e) {
					longtitude = 0.0;
					errorMsgs.put("longtitudeError","緯度請填數字.");
				}

				String campDescription = req.getParameter("campDescription").trim();
				if (campDescription == null || campDescription.trim().length() == 0) {
					errorMsgs.put("campDescriptionError","營地介紹請填寫");
				} 

				Integer campPrice = null;
				try {
					campPrice = new Integer(req.getParameter("campPrice").trim());
				} catch (NumberFormatException e) {
					campPrice = 0;
					errorMsgs.put("campPriceError","價錢請填數字");
				}

//				java.util.Date date = new java.util.Date();
//				java.sql.Timestamp listedTime = new java.sql.Timestamp(date.getTime());
				java.sql.Timestamp listedTime = null;
				try {
					listedTime = java.sql.Timestamp.valueOf(req.getParameter("listedTime").trim());
				} catch (IllegalArgumentException e) {
					listedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.put("listedTimeError","請勿空白");
				}

//				Collection<Part> parts = req.getParts();
//				for (Part part : parts) {
//					String filename = getFileNameFromPart(part);
//					if (filename != null && part.getContentType() != null) {
//						InputStream in1 = req.getPart("picture1").getInputStream();
//						byte[] picture1 = null;
//
//						if (in1.available() != 0) {
//							picture1 = new byte[in1.available()];
//							in1.read(picture1);
//							in1.close();
//						} else {
//							picture1 = null;
//						}
//						InputStream in2 = req.getPart("picture2").getInputStream();
//						byte[] picture2 = null;
//
//						if (in2.available() != 0) {
//							picture2 = new byte[in2.available()];
//							in2.read(picture2);
//							in2.close();
//						} else {
//							picture2 = null;
//						}
//						InputStream in3 = req.getPart("picture3").getInputStream();
//						byte[] picture3 = null;
//
//						if (in3.available() != 0) {
//							picture3 = new byte[in3.available()];
//							in3.read(picture3);
//							in3.close();
//						} else {
//							picture3 = null;
//						}
//						InputStream in4 = req.getPart("picture4").getInputStream();
//						byte[] picture4 = null;
//
//						if (in4.available() != 0) {
//							picture4 = new byte[in4.available()];
//							in4.read(picture4);
//							in4.close();
//						} else {
//							picture4 = null;
//						}
//						InputStream in5 = req.getPart("picture5").getInputStream();
//						byte[] picture5 = null;
//
//						if (in5.available() != 0) {
//							picture5 = new byte[in5.available()];
//							in5.read(picture5);
//							in5.close();
//						} else {
//							picture5 = null;
//						}
				
				
//				InputStream in1 = req.getPart("picture1").getInputStream();
//				byte[] picture1 = null;
//				if (in1.available() != 0) {
//					picture1 = new byte[in1.available()];
//					in1.read(picture1);
//					in1.close();
//				} else {
//					picture1 = null;
//				}
				
//				 byte[] picture1 = null;
//				 byte[] picture2 = null;
//				 byte[] picture3 = null;
//				 byte[] picture4 = null;
//				 byte[] picture5 = null;
//				
//				try {
//					 Collection<Part> parts = req.getParts();						 						 
//					 
//					 Part part1 = req.getPart("img1");
//					 InputStream in1 = part1.getInputStream();
//					 if (in1.available() != 0) {
//						 in1.close();
//						 picture1 = new byte[in1.available()];
//						 in1.read(picture1);
//						 in1.close();				 
//					 } else {
//						 errorMsgs.add("圖片1: 請勿空白");
//					 }
//					 
//					 Part part2 = req.getPart("img2");
//					 InputStream in2 = part2.getInputStream();
//					 if(in2.available() != 0) {
//						 in2.close();
//						 picture2 = new byte[in2.available()];
//						 in2.read(picture2);
//						 in2.close();				 
//					 } else {
//						 errorMsgs.add("圖片2: 請勿空白");
//					 }
//					 
//					 Part part3 = req.getPart("img3");
//					 InputStream in3 = part3.getInputStream();
//					 if(in3.available() != 0) {
//						 in3.close();
//						 picture3 = new byte[in3.available()];
//						 in3.read(picture3);
//						 in3.close();				 
//					 } else {
//						 errorMsgs.add("圖片3: 請勿空白");
//					 }
//					 
//					 Part part4 = req.getPart("img4");
//					 InputStream in4 = part4.getInputStream();
//					 if(in4.available() != 0) {
//						 in4.close();
//						 picture4 = new byte[in4.available()];
//						 in4.read(picture4);
//						 in4.close();				 
//					 } else {
//						 errorMsgs.add("圖片4: 請勿空白");
//					 }
//					 Part part5 = req.getPart("img5");
//					 InputStream in5 = part5.getInputStream();
//					 if(in5.available() != 0) {
//						 in5.close();
//						 picture5 = new byte[in5.available()];
//						 in5.read(picture5);
//						 in5.close();				 
//					 } else {
//						 errorMsgs.add("圖片5: 請勿空白");
//					 }
//					 
//					 } catch(Exception e) {
//						 e.printStackTrace();
//					 }
				
				InputStream in1 = req.getPart("picture1").getInputStream();
				byte[] picture1 = null;
				if (in1.available() != 0) {
					picture1 = new byte[in1.available()];
					in1.read(picture1);
					in1.close();
				} else {
					picture1 = null;
				}
				
				InputStream in2 = req.getPart("picture2").getInputStream();
				byte[] picture2 = null;
				if (in2.available() != 0) {
					picture2 = new byte[in2.available()];
					in2.read(picture2);
					in2.close();
				} else {
					picture2 = null;
				}
				
				InputStream in3 = req.getPart("picture3").getInputStream();
				byte[] picture3 = null;
				if (in3.available() != 0) {
					picture3 = new byte[in3.available()];
					in3.read(picture3);
					in3.close();
				} else {
					picture3 = null;
				}
				
				InputStream in4 = req.getPart("picture4").getInputStream();
				byte[] picture4 = null;
				if (in4.available() != 0) {
					picture4 = new byte[in4.available()];
					in4.read(picture4);
					in4.close();
				} else {
					picture4 = null;
				}
				
				InputStream in5 = req.getPart("picture5").getInputStream();
				byte[] picture5 = null;
				if (in5.available() != 0) {
					picture5 = new byte[in5.available()];
					in5.read(picture5);
					in5.close();
				} else {
					picture5 = null;
				}
				
				String strbbq = req.getParameter("bbq");
				Integer bbq = null;
				if(strbbq == null) {
					bbq = 0;
				} else {
					bbq = new Integer(strbbq);
				}
				
				String strwifi = req.getParameter("wifi");
				Integer wifi = null;
				if(strwifi == null) {
					wifi = 0;
				} else {
					wifi = new Integer(strwifi);
				}
				
				String strnosmoke = req.getParameter("nosmoke");
				Integer nosmoke = null;
				if(strnosmoke == null) {
					nosmoke = 0;
				} else {
					nosmoke = new Integer(strnosmoke);
				}
				
				String strpets = req.getParameter("pets");
				Integer pets = null;
				if(strpets == null) {
					pets = 0;
				} else {
					pets = new Integer(strpets);
				}

						CampReleaseVO campreleaseVO = new CampReleaseVO();
						campreleaseVO.setMemberId(memberId);
						campreleaseVO.setCampName(campName);
						campreleaseVO.setLocation(location);
						campreleaseVO.setLatitude(latitude);
						campreleaseVO.setLongtitude(longtitude);
						campreleaseVO.setCampDescription(campDescription);
						campreleaseVO.setCampPrice(campPrice);
						campreleaseVO.setListedTime(listedTime);
						campreleaseVO.setPicture1(picture1);
						campreleaseVO.setPicture2(picture2);
						campreleaseVO.setPicture3(picture3);
						campreleaseVO.setPicture4(picture4);
						campreleaseVO.setPicture5(picture5);
						
						
						
						List<FacilitiesVO> facilitiesList = new ArrayList<FacilitiesVO>();
						FacilitiesVO facilitiesVO = new FacilitiesVO();
						facilitiesVO.setBbq(bbq);
						facilitiesVO.setWifi(wifi);
						facilitiesVO.setNosmoke(nosmoke);
						facilitiesVO.setPets(pets);
						facilitiesList.add(facilitiesVO);
//				try {
//					campaddVO.setPicture1(getPictureByteArray(picture1));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("campreleaseVO", campreleaseVO); // 含有輸入格式錯誤的campreleaseVO物件,也存入req
							req.setAttribute("facilitiesVO", facilitiesVO);
							
							RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addCampRel.jsp");
							failureView.forward(req, res);
							return; // 程式中斷
						}
						
//						CampReleaseDAO campreleaseDAO = new CampReleaseDAO();
//						campreleaseDAO.addCampRelease(campreleaseVO, facilitiesVO);

						/*************************** 2.開始新增資料 ***************************************/
//						CampReleaseService campreleaseSvc = new CampReleaseService();
//						campreleaseVO = campreleaseSvc.addCampRelease(memberId, campName, location, latitude, longtitude,
//								campDescription, campPrice, listedTime, picture1, picture2, picture3, picture4,
//								picture5);
						
						CampReleaseDAO campreleaseDAO = new CampReleaseDAO();
						campreleaseDAO.insertCamp(campreleaseVO, facilitiesList);

						/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
						String url = "/camprelease/listCampRel.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAddCamp.jsp
						successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("insertError", "wrong"); //e.getMessage()
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addCampRel.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listCampRel.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer campId = new Integer(req.getParameter("campId"));

				/*************************** 2.開始刪除資料 ***************************************/
				CampReleaseService campreleaseSvc = new CampReleaseService();
				campreleaseSvc.deleteCampRelease(campId);
				
				PlanService planSvc = new PlanService();
				planSvc.deletebyCampId(campId);
				
				FacilitiesService facilitiesSvc = new FacilitiesService();
				facilitiesSvc.deletebyCampId(campId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/camprelease/listCampRel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listCampRel.jsp");
				failureView.forward(req, res);
			}
		}
	}
	

//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part parts) {
		String header = parts.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
