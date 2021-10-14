package com.facilities.model;

import java.util.List;

public interface FacilitiesDAO_interface {
	
	public void insert(FacilitiesVO facilitiesVO);
	public void delete(Integer facilitiesId);
	public List<FacilitiesVO> getAllByCampId(Integer campId);

}
