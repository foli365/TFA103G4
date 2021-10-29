package com.Alert.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.campAlert.model.*;

//@WebServlet("/backendLogin/CampAlert.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
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
		if ("delete".equals(action)) {
			Integer id = new Integer(req.getParameter("alertId"));
			CampAlertService alertSvc = new CampAlertService();
			alertSvc.deleteCampAlertDAO(id);
			String url = "/backendLogin/alert.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		if("insert".equals(action)) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			Integer campId = Integer.parseInt(req.getParameter("campId"));
			String comment = req.getParameter("comment");
			Date reportTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String reportTimeString = formatter.format(reportTime);
			Integer status = 0;
			Integer handler = 0;
			List<byte[]> picList = new ArrayList<>();

			try {
				List<Part> parts = (List<Part>)req.getParts();

				for (Part part : parts) {
					String fileName = part.getSubmittedFileName();
					System.out.println("file: " + fileName);
					if ((fileName != null && fileName.endsWith("jpg"))||(fileName != null && fileName.endsWith("png"))) {
						InputStream in1 = part.getInputStream();
						if (in1.available() != 0) {
							byte[] picture = new byte[in1.available()];
							in1.read(picture);
							in1.close();
							picList.add(picture);
						} else {
							
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte[] picture1;
			byte[] picture2;
			byte[] picture3;
			try {
				picture1 = picList.get(0);
			} catch (Exception e) {
				picture1 = null;
			}
			try {
				picture2 = picList.get(1);
			} catch (Exception e) {
				// TODO: handle exception
				picture2 = null;
			}
			try {
				picture3 = picList.get(2);
			} catch (Exception e) {
				// TODO: handle exception
				picture3 = null;
			}
			CampAlertService casvc = new CampAlertService();
			casvc.insertcCampAlertVO(id, campId, reportTimeString, comment, picture1, picture2, picture3, status, handler);
			
		}
		
	}
}
