package com.adminList.model;

import java.util.List;
import java.util.Scanner;

import com.adminList.model.AdminListVO;

import com.adminList.model.AdminListDAO;
import com.adminList.model.AdminListDao_interface;
public class AdminAdd {
	public static void main(String[] args) {
		AdminListDAO dao = new AdminListDAO();

		// 新增
		AdminListVO VO = new AdminListVO();
		VO.setAdminId(7005);
		VO.setAdminPwd("jacky0229");
		VO.setAdminName("吳狄軒");
		dao.add(VO);

	}
}