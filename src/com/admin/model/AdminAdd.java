package com.admin.model;

import java.util.List;
import java.util.Scanner;

import com.admin.model.AdminListVO;

import com.admin.model.AdminListDAO;
import com.admin.model.AdminListDao_interface;
public class AdminAdd {
	public static void main(String[] args) {
		AdminListDAO dao = new AdminListDAO();

		// ·s¼W
		AdminListVO VO = new AdminListVO();
		VO.setAdminId(7005);
		VO.setAdminPwd("jacky0229");
		VO.setAdminName("§d¨f°a");
		dao.add(VO);

	}
}