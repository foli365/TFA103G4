package com.plan.model;

import java.util.List;

public interface PlanDAO_interface {
	void add(PlanVO planVO);
	void update(PlanVO planVO);
	void delete(Integer planId);
	PlanVO findbyPrimaryKey(Integer planId);
	List<PlanVO> getAll();
}