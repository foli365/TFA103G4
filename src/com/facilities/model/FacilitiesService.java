package com.facilities.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FacilitiesService {
	private FacilitiesDAO_interface dao;
	
	public FacilitiesService() {
		dao = new FacilitiesDAO();
	}
	
	public FacilitiesVO addFacilities(Integer campId, String facilities) {
		FacilitiesVO facilitiesVO = new FacilitiesVO();
		facilitiesVO.setCampId(campId);
		try {
			facilitiesVO.setFacilities(getPictureByteArray(facilities));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.insert(facilitiesVO);
		return facilitiesVO;
	}
	
	public void delete(Integer facilitiesId) {
		dao.delete(facilitiesId);
	};
	
	public List<FacilitiesVO> getAllByFacilitiesId(Integer campId){
		return dao.getAllByCampId(campId);
		
	};
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
