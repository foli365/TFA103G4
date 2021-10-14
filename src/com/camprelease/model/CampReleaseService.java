package com.camprelease.model;

//import java.io.FileInputStream;
//import java.io.IOException;
import java.util.*;
import java.sql.*;

public class CampReleaseService {
	
	private CampReleaseDAO_interface dao;

	public CampReleaseService() {
		// TODO Auto-generated constructor stub
		dao = new CampReleaseDAO();
	}

	public CampReleaseVO addCampRelease(String campName, String location, Double latitude, Double longtitude,
			String campDescription, Integer campPrice, Timestamp listedTime, byte[] picture1, byte[] picture2, byte[] picture3, byte[] picture4, byte[] picture5, Integer memberId) {

		CampReleaseVO campreleaseVO = new CampReleaseVO();

		campreleaseVO.setCampName(campName);
		campreleaseVO.setLocation(location);
		campreleaseVO.setLatitude(latitude);
		campreleaseVO.setLongtitude(longtitude);
		campreleaseVO.setCampDescription(campDescription);
		campreleaseVO.setCampPrice(campPrice);
		campreleaseVO.setListedTime(listedTime);
//		try {
//			campaddVO.setPicture1(getPictureByteArray(picture1));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		campreleaseVO.setPicture1(picture1);
		campreleaseVO.setPicture2(picture2);
		campreleaseVO.setPicture3(picture3);
		campreleaseVO.setPicture4(picture4);
		campreleaseVO.setPicture5(picture5);
		campreleaseVO.setMemberId(memberId);

		dao.insert(campreleaseVO);
		
		return campreleaseVO;
	}

	public CampReleaseVO updateCampRelease(String campName, String location, Double latitude, Double longtitude,
			String campDescription, Integer campPrice,Timestamp listedTime, byte[] picture1, byte[] picture2, byte[] picture3, byte[] picture4, byte[] picture5,Integer memberId, Integer campId) {

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
//		try {
//			VO.setPicture1(getPictureByteArray(picture1));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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

//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}

}
