package com.members.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adminList.model.AdminListVO;
import com.adminList.model.AdminService;
import com.members.model.*;

public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer memberId = new Integer(req.getParameter("memberId"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				MemberService memsvc = new MemberService();
				MembersVO membersVO = memsvc.findByPrimaryKey(memberId);
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("membersVO", membersVO);
				String url = "/backendLogin/updatemember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/member.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {

			try {
				Integer id = new Integer(req.getParameter("id").trim());
				MemberService memSvc = new MemberService();
				MembersVO memVO = memSvc.findByPrimaryKey(id);
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
				String url = "/backendLogin/member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Throwable e) {
				e.printStackTrace();
			}

		}

	}

}
