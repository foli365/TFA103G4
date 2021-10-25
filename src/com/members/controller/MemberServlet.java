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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.members.model.MemberService;
import com.members.model.MembersVO;

import at.favre.lib.crypto.bcrypt.BCrypt;

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
		String url = "/account/edit_profile.jsp";

		if ("update".equals(action)) {
			try {
				Integer id = new Integer(req.getParameter("id").trim());
				MemberService memSvc = new MemberService();
				MembersVO memVO = memSvc.findByPrimaryKey(id);

				InputStream in = req.getPart("photo").getInputStream();
				byte[] buf = null;
				if (in.available() != 0) {
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
				HttpSession session = req.getSession();
				session.setAttribute("account", updatedMemVO.getName());
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Throwable e) {
				e.printStackTrace();
			}

		}

		if ("passwordUpdate".equals(action)) {
			RequestDispatcher View = req.getRequestDispatcher(url);
			MemberService memSvc = new MemberService();
			String currentPassword = req.getParameter("currentPword");
			String newPassword = req.getParameter("newPword");
			String confirmNewPword = req.getParameter("confirmNewPword");
			if (currentPassword.trim().length() == 0 || newPassword.trim().length() == 0
					|| confirmNewPword.trim().length() == 0) {
				req.setAttribute("invalid", "�п�J�Ҧ����");
				return;
			}
			HttpSession session = req.getSession();
			Integer id = (Integer) session.getAttribute("id");
			String bcryptHashString = memSvc.findByPrimaryKey(id).getPassword();
			BCrypt.Result result = BCrypt.verifyer().verify(currentPassword.toCharArray(), bcryptHashString);
			if (!result.verified) {
				req.setAttribute("wrongPword", "�K�X�νT�{�K�X��J���~�A�Э��s�ˬd");
				req.setAttribute("index", "2");
				View.forward(req, res);
				return;
			} else {
				String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
				if (!newPassword.matches(passwordReg)) {
					req.setAttribute("pwordTooWeak", "�K�X���פ��o�p��8�B�ܤֶ����@�r��");
					req.setAttribute("index", "2");
					View.forward(req, res);
					return;
				} else if (newPassword.equals(currentPassword)) {
					req.setAttribute("samePword", "�s�K�X�ФŻP�±K�X�ۦP");
					req.setAttribute("index", "2");
					View.forward(req, res);
					return;
				} else if (!newPassword.equals(confirmNewPword)) {
					req.setAttribute("wrongPword", "�K�X�νT�{�K�X��J���~�A�Э��s�ˬd");
					req.setAttribute("index", "2");
					View.forward(req, res);
					return;
				} else {
					String hashedPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
					memSvc.updatePassword(hashedPassword, memSvc.findByPrimaryKey(id).getEmail());
					req.setAttribute("success", "�K�X��s���\");
					req.setAttribute("index", "2");
					View.forward(req, res);
				}
			}
		}

	}

	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
