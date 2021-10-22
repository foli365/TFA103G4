package com.camprelease.model;

import java.util.List;

import com.facilities.model.*;

public interface CampReleaseDAO_interface {
	
	public void insert(CampReleaseVO campreleaseVO);
    public void update(CampReleaseVO campreleaseVO);
	public void delete(Integer campId);
    public CampReleaseVO findByPrimaryKey(Integer campId);
    public List<CampReleaseVO> getAll();
    public void insertCamp(CampReleaseVO campreleaseVO, List<FacilitiesVO> facilitiesList);
}
