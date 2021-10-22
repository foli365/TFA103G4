package com.facilities.model;

import java.util.List;

public class FacilitiesService {
	
	private FacilitiesDAO_interface dao;
	
	public FacilitiesService() {
		dao = new FacilitiesDAO();
	}
	
	public FacilitiesVO addFacilities(Integer campId, Integer bbq, Integer wifi, Integer nosmoke, Integer pets) {
		
		FacilitiesVO facilitiesVO = new FacilitiesVO();
		facilitiesVO.setCampId(campId);
		facilitiesVO.setBbq(bbq);
		facilitiesVO.setWifi(wifi);
		facilitiesVO.setNosmoke(nosmoke);
		facilitiesVO.setPets(pets);
		
		dao.insert(facilitiesVO);
		return facilitiesVO;
	}
	
	public FacilitiesVO updateFacilities(Integer campId, Integer bbq, Integer wifi, Integer nosmoke, Integer pets) {

		FacilitiesVO facilitiesVO = new FacilitiesVO();

		facilitiesVO.setCampId(campId);
		facilitiesVO.setBbq(bbq);
		facilitiesVO.setWifi(wifi);
		facilitiesVO.setNosmoke(nosmoke);
		facilitiesVO.setPets(pets);
		dao.update(facilitiesVO);

		return facilitiesVO;
	}
	
	public void delete(Integer campId) {
		dao.delete(campId);
	};
	
	public FacilitiesVO getOneCamp(Integer facilitiesId) {
		return dao.findByPrimaryKey(facilitiesId);
	}
	
	public FacilitiesVO getByCampId(Integer campId) {
		return dao.findbyCampId(campId);
	}
	public List<FacilitiesVO> getAll(){
		return dao.getAll();
	};
	
}
