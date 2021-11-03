package com.admin.model;

import java.util.List;

import com.admin.model.AdminListVO;
import com.admin.model.AdminListDAO;
import com.admin.model.AdminListDao_interface;

public class AdminService {
		private AdminListDao_interface dao;

		public AdminService() {
			dao = new AdminListDAO();
		}

		public AdminListVO addAdminListVO(Integer adminId, String adminPwd,String adminName) {

			AdminListVO vo = new AdminListVO();

			vo.setAdminId(adminId);
			vo.setAdminPwd(adminPwd);
			vo.setAdminName(adminName);
			dao.add(vo);

			return vo;
		}

		public AdminListVO updateAdminListVO(Integer adminId, String adminPwd,String adminName) {

			AdminListVO vo = new AdminListVO();

			vo.setAdminId(adminId);
			vo.setAdminPwd(adminPwd);
			vo.setAdminName(adminName);
			dao.update(vo);

			return vo;
		}

		public void deleteAdminListDAO(Integer adminId) {
			dao.delete(adminId);
		}

		public AdminListVO getOneAdminList(Integer adminId) {
			return dao.findByPrimaryKey(adminId);
		}

		public List<AdminListVO> getAll() {
			return dao.getALL();
		}
	}