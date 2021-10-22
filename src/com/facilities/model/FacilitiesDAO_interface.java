package com.facilities.model;

import java.util.List;

public interface FacilitiesDAO_interface {
	
	public void insert(FacilitiesVO facilitiesVO);
	public void delete(Integer campId);
	public void update(FacilitiesVO facilitiesVO);
	public FacilitiesVO findByPrimaryKey(Integer facilitiesId);
	public List<FacilitiesVO> getAll();
	public void facilitiesInsertWithCampId(FacilitiesVO facilitiesVO, java.sql.Connection con);
	public FacilitiesVO findbyCampId(Integer campId);
}
