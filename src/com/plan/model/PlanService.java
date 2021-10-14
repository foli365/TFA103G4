package com.plan.model;

import java.util.List;

public class PlanService {

	private PlanDAO_interface dao;

	public PlanService() {
		dao = new PlanDAO();
	}

	public PlanVO addPlan(Integer planId, Integer campId, String planName, Integer planGuestLimit, Integer planAgeLimit,
			Integer planPrice) {

		PlanVO planVO = new PlanVO();

		planVO.setPlanId(planId);
		planVO.setCampId(campId);
		planVO.setPlanName(planName);
		planVO.setPlanGuestLimit(planGuestLimit);
		planVO.setPlanAgeLimit(planAgeLimit);
		planVO.setPlanPrice(planPrice);
		dao.add(planVO);

		return planVO;
	}

	public PlanVO updatePlan(String planName, Integer planGuestLimit, Integer planAgeLimit, Integer planPrice,
			Integer planId) {

		PlanVO planVO = new PlanVO();

		planVO.setPlanName(planName);
		planVO.setPlanGuestLimit(planGuestLimit);
		planVO.setPlanAgeLimit(planAgeLimit);
		planVO.setPlanPrice(planPrice);
		planVO.setPlanId(planId);
		dao.update(planVO);

		return planVO;
	}

	public void deletePlan(Integer planId) {
		dao.delete(planId);
	}

	public PlanVO getOnePlan(Integer planId) {
		return dao.findbyPrimaryKey(planId);
	}

	public List<PlanVO> getAll() {
		return dao.getAll();
	}
}
