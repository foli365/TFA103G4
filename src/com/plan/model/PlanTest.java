package com.plan.model;

import java.util.List;

public class PlanTest {
	public static void main(String args[]) {
		
		// �s�W
		/*PlanVO planVO = new PlanVO();
		
		planVO.setPlanId(103);
		planVO.setCampId(5001);
		planVO.setPlanName("�N��");
		planVO.setPlanGuestLimit(30);
		planVO.setPlanAgeLimit(0);
		planVO.setPlanPrice(1000);
		
		PlanDAO_interface plan = new PlanDAO();
		plan.add(planVO);*/
		
		//==============================================================
		
		// �ק�
		/*PlanVO planVO = new PlanVO();
		
		planVO.setPlanName("����");
		planVO.setPlanGuestLimit(12);
		planVO.setPlanAgeLimit(65);
		planVO.setPlanPrice(1500);
		planVO.setPlanId(103);
		
		PlanDAO_interface plan = new PlanDAO();
		plan.update(planVO);*/
		
		//==============================================================
		
		// �R��
		/*PlanDAO_interface plan = new PlanDAO();
		plan.delete(103);*/
		
		//==============================================================
		
		// �d�߳浧
		/*PlanDAO_interface plan = new PlanDAO();
		PlanVO planVO = plan.findbyPrimaryKey(101);
		
		System.out.println(planVO);*/
		
		//==============================================================
		
		// �d�ߦh��
		/*PlanDAO_interface plan = new PlanDAO();
		List<PlanVO> planList = plan.getAll();
		
		for(PlanVO planVO : planList) {
			System.out.println(planVO);
		}*/
	}
}
