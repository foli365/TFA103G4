package com.plan.model;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<PlanVO> getOnePlan(Integer campId) {
		return dao.findbyPrimaryKey(campId);
	}
	
	public PlanVO getOnePlanByPlanId(Integer planId) {
		List<PlanVO> list = dao.getAll();
		List<PlanVO> planVO = list.stream()
				.filter(e -> e.getPlanId().equals(planId))
				.collect(Collectors.toList());
		return planVO.get(0);
	}

	public List<PlanVO> getAll() {
		return dao.getAll();
	}
}
