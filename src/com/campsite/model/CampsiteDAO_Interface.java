package com.campsite.model;

import java.util.List;

public interface CampsiteDAO_Interface {
	void add(CampsiteVO campsiteVO);
	void update(CampsiteVO campsiteVO);
	void delete(Integer campId);
	CampsiteVO findbyPrimaryKey(Integer campId);
	List<CampsiteVO> getAll();
	List<CampsiteVO> getSearchCampsite(String campName);/*先試營地名稱*/
}
