package com.camporder.model;

import java.util.List;

public interface CampOrderDAO_interface {
	void add(CampOrderVO campOrderVO);
	void update(CampOrderVO campOrderVO);
	void delete(Integer campOrderId);
	CampOrderVO findbyPrimaryKey(Integer campOrderId);
	List<CampOrderVO> getAll();
	void updateOrder(CampOrderVO campOrderVO);
}
