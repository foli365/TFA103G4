package com.campsitetentstatus.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CampsiteTentStatusService {

	private CampsiteTentStatusDAO_interface dao;

	public CampsiteTentStatusService() {
		dao = new CampsiteTentStatusDAO();
	}

	public CampsiteTentStatusVO addCampsiteTentStatus(Integer campId, Date campOpeningTime, Integer emptyCampLeft) {

		CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();

		campsiteTentStatusVO.setCampId(campId);
		campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
		campsiteTentStatusVO.setEmptyCampLeft(emptyCampLeft);
		dao.add(campsiteTentStatusVO);

		return campsiteTentStatusVO;
	}

	public CampsiteTentStatusVO updateCampsiteTentStatus(Integer emptyCampLeft, Integer campId, Date campOpeningTime) {

		CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();

		campsiteTentStatusVO.setEmptyCampLeft(emptyCampLeft);
		campsiteTentStatusVO.setCampId(campId);
		campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
		dao.update(campsiteTentStatusVO);

		return campsiteTentStatusVO;
	}

	public void deleteCampsiteTentStatus(Integer campId, Date campOpeningTime) {
		dao.delete(campId, campOpeningTime);
	}

	public CampsiteTentStatusVO getOneCampsiteTentStatus(Integer campId, Date campOpeningTime) {
		return dao.findbyPrimaryKey(campId, campOpeningTime);
	}

	public List<CampsiteTentStatusVO> getAll() {
		return dao.getAll();
	}

	public boolean isBookedCamp(Integer campId, Date stay) {
		List<CampsiteTentStatusVO> list = dao.getAll();
		List<CampsiteTentStatusVO> newList = list.stream()
				.filter(e -> e.getCampId().equals(campId))
				.filter(e -> e.getCampOpeningTime().equals(stay))
				.collect(Collectors.toList());
		try {
			newList.get(0);
			return true;
		} catch (IndexOutOfBoundsException Out) {
			return false;
		}
	}

	public boolean isBookedCampFullyBooked(Integer campId, Date stay, Integer guestNumber) {
		List<CampsiteTentStatusVO> list = dao.getAll();
		List<CampsiteTentStatusVO> newList = list.stream()
				.filter(e -> e.getCampId().equals(campId))
				.filter(e -> e.getCampOpeningTime().equals(stay))
				.filter(e -> e.getEmptyCampLeft().compareTo(guestNumber) < 0).collect(Collectors.toList());
		try {
			newList.get(0);
			return true;
		} catch (IndexOutOfBoundsException Out) {
			return false;
		}
	}
	
	//當得知營地ID，預訂人數和預定日期區間後，確認此區間是否能預定
	public boolean isCampAvailible(Integer campId, java.sql.Date start, java.sql.Date finish, Integer guestNumber) {
		CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
		while (start.compareTo(finish) <= 0) {

			if (CTSSvc.isBookedCamp(campId, start)) {
				if (CTSSvc.isBookedCampFullyBooked(campId, start, guestNumber)) {
					return false;
				}
			}
			Calendar c = Calendar.getInstance();
			c.setTime(start);
			c.add(Calendar.DATE, 1);
			start = new java.sql.Date(c.getTimeInMillis());
		}
		return true;
	}

	public List<CampsiteTentStatusVO> getAllCampStatusofOneCamp(Integer campId) {
		List<CampsiteTentStatusVO> list = dao.getAll();
		List<CampsiteTentStatusVO> newlist = list.stream()
				.filter(e -> e.getCampId().equals(campId))
				.collect(Collectors.toList());
		return newlist;
	}
	
	//當得知營地ID和預訂人數後，取得無法預定的日期
	public ArrayList<String> unavailibleDate(Integer campId, Integer guestCount) throws ParseException {
		CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
		ArrayList<CampsiteTentStatusVO> list = (ArrayList<CampsiteTentStatusVO>) CTSSvc.getAllCampStatusofOneCamp(campId);
		java.sql.Date SqlToday = null;
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String today = simpleDateFormat.format(new java.util.Date());
		java.util.Date parsed = simpleDateFormat.parse(today);
		SqlToday = new Date(parsed.getTime());
		Calendar c = Calendar.getInstance();
		c.setTime(SqlToday);
		c.add(Calendar.DATE, 60);
		java.sql.Date sixtyDaysAfter = new java.sql.Date(c.getTimeInMillis());
		ArrayList<String> unavilibleDate = new ArrayList<String>();
		int listSize = list.size();
		int loopCount = 0;
		while(SqlToday.compareTo(sixtyDaysAfter) <= 0) {
			if (loopCount == listSize) {
				return unavilibleDate;
			}
			for (CampsiteTentStatusVO campsiteTentStatusVO : list) {
				if (campsiteTentStatusVO.getCampOpeningTime().equals(SqlToday)) {
					if (campsiteTentStatusVO.getEmptyCampLeft() < guestCount) {
						unavilibleDate.add("\"" + campsiteTentStatusVO.getCampOpeningTime() + "\"");
					}
				}
				Calendar c1 = Calendar.getInstance();
				c1.setTime(SqlToday);
				c1.add(Calendar.DATE, 1);
				SqlToday = new java.sql.Date(c1.getTimeInMillis());
				loopCount++;
			}
		}
		return unavilibleDate;
	}
}
