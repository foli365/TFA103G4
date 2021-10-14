package com.campsitetentstatus.model;

import java.sql.Date;
import java.util.List;

public class CampsiteTentStatusService {

	private CampsiteTentStatusDAO_interface dao;

	public CampsiteTentStatusService() {
		dao = new CampsiteTentStatusDAO();
	}

	public CampsiteTentStatusVO addCampsiteTentStatus(Integer campId, Date campOpeningTime, Integer emptyCampLeft) {

		CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();

		campsiteTentStatusVO.setCampId(campId);
		campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
		campsiteTentStatusVO.setEmptyCampLeft(emptyCampLeft);
		dao.add(campsiteTentStatusVO);

		return campsiteTentStatusVO;
	}

	public CampsiteTentStatusVO updateCampsiteTentStatus(Integer emptyCampLeft, Integer campId, Date campOpeningTime) {

		CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();

		campsiteTentStatusVO.setEmptyCampLeft(emptyCampLeft);
		campsiteTentStatusVO.setCampId(campId);
		campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
		dao.update(campsiteTentStatusVO);

		return campsiteTentStatusVO;
	}

	public void deleteCampsiteTentStatus(Integer campId, Date campOpeningTime) {
		dao.delete(campId, campOpeningTime);
	}
	
	public CampsiteTentStatusVO getOneCampsiteTentStatus(Integer campId, Date campOpeningTime) {
		return dao.findbyPrimaryKey(campId, campOpeningTime);
	}
	
	public List<CampsiteTentStatusVO> getAll() {
		return dao.getAll();
	}
}
