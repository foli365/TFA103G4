package com.campsitetentstatus.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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

	
	public List<CampsiteTentStatusVO> getAllCampStatusofOneCamp(Integer campId) {
		List<CampsiteTentStatusVO> list = dao.getAll();
		List<CampsiteTentStatusVO> newlist = list.stream().filter(e -> e.getCampId().equals(campId))
				.collect(Collectors.toList());
		return newlist;
	}

	// 當得知營地ID和預訂人數後，取得無法預定的日期
	public ArrayList<String> getUnavailibleDatewithGuestNumberOnly(Integer campId, Integer guestCount) throws ParseException {
		CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
		 ArrayList<CampsiteTentStatusVO> list = (ArrayList<CampsiteTentStatusVO>) CTSSvc.getAllCampStatusofOneCamp(campId);
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String today = simpleDateFormat.format(new java.util.Date());
		java.util.Date parsedToday = simpleDateFormat.parse(today);
		Calendar c = Calendar.getInstance();
		c.setTime(parsedToday);
		c.add(Calendar.DATE, 60);
		java.util.Date sixtyDaysAfter = new java.util.Date(c.getTimeInMillis());
		ArrayList<String> unavilibleDate = new ArrayList<String>();
		while (parsedToday.compareTo(sixtyDaysAfter) <= 0) {
			if(getIndexByProperty(list, parsedToday, campId) != -1) {
				if (list.get(getIndexByProperty(list, parsedToday, campId)).getCampOpeningTime().equals(parsedToday)) {
					if (list.get(getIndexByProperty(list, parsedToday, campId)).getEmptyCampLeft()<guestCount) {
						String matchDay = simpleDateFormat.format(parsedToday);
						unavilibleDate.add("\"" + matchDay + "\"");
					}
				}
			};
			Calendar c1 = Calendar.getInstance();
			c1.setTime(parsedToday);
			c1.add(Calendar.DATE, 1);
			parsedToday = new java.util.Date(c1.getTimeInMillis());
		}
		return unavilibleDate;
	}
	
	public Boolean isTentAvailiblewithGuestNumberandTimeRange(Integer campId, Integer guestNumber, java.util.Date start, java.util.Date end) throws ParseException {
		CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
		ArrayList<CampsiteTentStatusVO> list = (ArrayList<CampsiteTentStatusVO>) CTSSvc.getAllCampStatusofOneCamp(campId);
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		ArrayList<String> unavilibleDate = new ArrayList<String>();
		Integer guestCount = null;
		try {
			guestCount = new Integer (guestNumber);
		} catch (NumberFormatException NFE) {
			guestCount = 0;
		}
		while (start.compareTo(end) <= 0) {
			if(getIndexByProperty(list, start, campId) != -1) {
				if (list.get(getIndexByProperty(list, start, campId)).getCampOpeningTime().equals(start)) {
					if (guestCount == 0) {
						if (list.get(getIndexByProperty(list, start, campId)).getEmptyCampLeft() == 0) {
							String matchDay = simpleDateFormat.format(start);
							unavilibleDate.add("\"" + matchDay + "\"");
						}
					} else {
						if (list.get(getIndexByProperty(list, start, campId)).getEmptyCampLeft() < guestCount) {
							String matchDay = simpleDateFormat.format(start);
							unavilibleDate.add("\"" + matchDay + "\"");
						}
					}
				}
			}
			Calendar c1 = Calendar.getInstance();
			c1.setTime(start);
			c1.add(Calendar.DATE, 1);
			start = new java.util.Date(c1.getTimeInMillis());
		}
		try {
			unavilibleDate.get(0);
			return false;
		} catch (IndexOutOfBoundsException i) {
			return true;
		}
	}
	
	 private int getIndexByProperty(ArrayList<CampsiteTentStatusVO> list, java.util.Date targetDate, Integer campid) {
	        for (int i = 0; i < list.size(); i++) {
	            if (list.get(i) !=null && list.get(i).getCampOpeningTime().equals(targetDate)) {
	                return i;
	            }
	        }
	        return -1;
	    }
	 
}
