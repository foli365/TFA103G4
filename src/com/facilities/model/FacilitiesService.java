package com.facilities.model;

import java.util.List;

public class FacilitiesService {
	
	private FacilitiesDAO_interface dao;
	
	public FacilitiesService() {
		dao = new FacilitiesDAO();
	}
	
	public FacilitiesVO addFacilities(Integer facilitiesId, Integer campId, Integer bbq, Integer wifi, Integer nosmoke, Integer pets) {
		FacilitiesVO facilitiesVO = new FacilitiesVO();
		facilitiesVO.setFacilitiesId(facilitiesId);
		facilitiesVO.setCampId(campId);
		facilitiesVO.setBbq(bbq);
		facilitiesVO.setWifi(wifi);
		facilitiesVO.setNosmoke(nosmoke);
		facilitiesVO.setPets(pets);
		
		dao.insert(facilitiesVO);
		return facilitiesVO;
	}
	
	public FacilitiesVO updateFacilities(Integer bbq, Integer wifi, Integer nosmoke, Integer pets, Integer facilitiesId) {

		FacilitiesVO facilitiesVO = new FacilitiesVO();

		facilitiesVO.setBbq(bbq);
		facilitiesVO.setWifi(wifi);
		facilitiesVO.setNosmoke(nosmoke);
		facilitiesVO.setPets(pets);
		facilitiesVO.setFacilitiesId(facilitiesId);
		dao.update(facilitiesVO);

		return facilitiesVO;
	}
	
	public void delete(Integer facilitiesId) {
		dao.delete(facilitiesId);
	};
	
	public List<FacilitiesVO> getAllByFacilitiesId(Integer campId){
		return dao.getAllByCampId(campId);
	};
}
