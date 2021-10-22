package com.camprelease.model;

import java.util.*;
import java.sql.*;

public class CampReleaseService {
	
	private CampReleaseDAO_interface dao;

	public CampReleaseService() {
		// TODO Auto-generated constructor stub
		dao = new CampReleaseDAO();
	}

	public CampReleaseVO addCampRelease(Integer memberId, String campName, String location, Double latitude, Double longtitude,
			String campDescription, Integer campPrice, Timestamp listedTime, byte[] picture1, byte[] picture2, byte[] picture3, byte[] picture4, byte[] picture5) {

		CampReleaseVO campreleaseVO = new CampReleaseVO();

		campreleaseVO.setCampName(campName);
		campreleaseVO.setLocation(location);
		campreleaseVO.setLatitude(latitude);
		campreleaseVO.setLongtitude(longtitude);
		campreleaseVO.setCampDescription(campDescription);
		campreleaseVO.setCampPrice(campPrice);
		campreleaseVO.setListedTime(listedTime);
		campreleaseVO.setPicture1(picture1);
		campreleaseVO.setPicture2(picture2);
		campreleaseVO.setPicture3(picture3);
		campreleaseVO.setPicture4(picture4);
		campreleaseVO.setPicture5(picture5);
		campreleaseVO.setMemberId(memberId);

		dao.insert(campreleaseVO);
		
		return campreleaseVO;
	}

	public CampReleaseVO updateCampRelease(Integer campId, Integer memberId, String campName, String location, Double latitude, Double longtitude,
			String campDescription, Integer campPrice,Timestamp listedTime, byte[] picture1, byte[] picture2, byte[] picture3, byte[] picture4, byte[] picture5) {

		CampReleaseVO campreleaseVO = new CampReleaseVO();

		campreleaseVO.setCampName(campName);
		campreleaseVO.setLocation(location);
		campreleaseVO.setLatitude(latitude);
		campreleaseVO.setLongtitude(longtitude);
		campreleaseVO.setCampDescription(campDescription);
		campreleaseVO.setCampPrice(campPrice);
		campreleaseVO.setListedTime(listedTime);
		campreleaseVO.setPicture1(picture1);
		campreleaseVO.setPicture2(picture2);
		campreleaseVO.setPicture3(picture3);
		campreleaseVO.setPicture4(picture4);
		campreleaseVO.setPicture5(picture5);
		campreleaseVO.setCampId(campId);
		campreleaseVO.setMemberId(memberId);
		
		dao.update(campreleaseVO);
		
		return campreleaseVO;
	}


	public void deleteCampRelease(Integer campId) {
		dao.delete(campId);
	}

	public CampReleaseVO getOneCampRelease(Integer campId) {
		return dao.findByPrimaryKey(campId);
	}
	
	public List<CampReleaseVO> getAll() {
		return dao.getAll();
	}
}
