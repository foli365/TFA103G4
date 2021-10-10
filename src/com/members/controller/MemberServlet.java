package com.members.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.members.model.MemberService;
import com.members.model.MembersVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("update".equals(action)) {
			
			try {	
				Integer id = new Integer(req.getParameter("id").trim());
				MemberService memSvc = new MemberService();
				MembersVO memVO = memSvc.findByPrimaryKey(id);
				
				InputStream in = req.getPart("photo").getInputStream();
				byte[] buf = null;
				if(in.available() != 0) {					
					buf = new byte[in.available()];
					in.read(buf);
					in.close();
				} else {
					buf = memVO.getThumbnail();
				}
				
				String name = req.getParameter("name");
				if (name == null || name.trim().length() == 0) {
					name = memVO.getName();
				}
				String phone = req.getParameter("phone");
				if (phone == null || phone.trim().length() == 0) {
					phone = memVO.getPhone();
				}
				String address = req.getParameter("address");
				if (address == null || address.trim().length() == 0) {
					address = memVO.getAddress();
				}
				Integer membership = null;
				try {
					membership = new Integer(req.getParameter("membership"));
				} catch (Exception e) {
					membership = memVO.getMembership();
				}
				
				Integer memberStatus = null;
				try {
					memberStatus = new Integer(req.getParameter("memberStatus"));
				} catch (Exception e) {
					memberStatus = memVO.getMembership();
				}
			
				MembersVO updatedMemVO = memSvc.updateMembers(id, name, phone, membership, memberStatus, buf, address);
				req.setAttribute("memVO", updatedMemVO);
				String url = "/account/edit_profile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		}
		
	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
}
