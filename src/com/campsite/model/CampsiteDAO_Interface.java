package com.campsite.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface CampsiteDAO_Interface {
	void add(CampsiteVO campsiteVO);
	void update(CampsiteVO campsiteVO);
	void delete(Integer campId);
	CampsiteVO findbyPrimaryKey(Integer campId);
	List<CampsiteVO> getAll();
	List<CampsiteVO> getSearchCampsite(String campName);
	List<CampsiteVO> getMultiSearchCampsite(String campName, Date strDate, Date endDate, 
											Integer customerNum, Integer campPriceL, 
											Integer campPriceH); // �ۤv�����ƦX�d��
	List<CampsiteVO> getAll(Map<String, String[]> map); // �ƦX�d��
}
