package com.camprelease.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class CampReleaseDAO implements CampReleaseDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/Gocamping?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO campsite (MEMBER_ID, CAMP_NAME, LOCATION, LATITUDE, LONGTITUDE, CAMP_DESCRIPTION, CAMP_PRICE, LISTED_TIME, PICTURE1, PICTURE2, PICTURE3, PICTURE4, PICTURE5) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT CAMP_ID, MEMBER_ID, CAMP_NAME, MEMBER_ID, LOCATION, LATITUDE, LONGTITUDE, CAMP_DESCRIPTION, CAMP_PRICE, LISTED_TIME, PICTURE1, PICTURE2, PICTURE3, PICTURE4, PICTURE5 FROM campsite order by CAMP_ID";
	private static final String GET_ONE_STMT = 
			"SELECT CAMP_ID, MEMBER_ID, CAMP_NAME, LOCATION, LATITUDE, LONGTITUDE, CAMP_DESCRIPTION, CAMP_PRICE, LISTED_TIME, PICTURE1, PICTURE2, PICTURE3, PICTURE4, PICTURE5 FROM campsite where CAMP_ID = ?";
	private static final String DELETE = 
			"DELETE FROM campsite where CAMP_ID = ?";
	private static final String UPDATE = 
			"UPDATE campsite set CAMP_NAME=?, LOCATION=?, LATITUDE=?, LONGTITUDE=?, CAMP_DESCRIPTION=?, CAMP_PRICE=?, LISTED_TIME=?,  PICTURE1=?, PICTURE2=?, PICTURE3=?, PICTURE4=?, PICTURE5=? where CAMP_ID = ?";

	@Override
	public void insert(CampReleaseVO campreleaseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, campreleaseVO.getMemberId());
			pstmt.setString(2, campreleaseVO.getCampName());
			pstmt.setString(3, campreleaseVO.getLocation());
			pstmt.setDouble(4, campreleaseVO.getLatitude());
			pstmt.setDouble(5, campreleaseVO.getLongtitude());
			pstmt.setString(6, campreleaseVO.getCampDescription());
			pstmt.setInt(7, campreleaseVO.getCampPrice());
			pstmt.setTimestamp(8, campreleaseVO.getListedTime());
			pstmt.setBytes(9, campreleaseVO.getPicture1());	
			pstmt.setBytes(10, campreleaseVO.getPicture2());
			pstmt.setBytes(11, campreleaseVO.getPicture3());
			pstmt.setBytes(12, campreleaseVO.getPicture4());
			pstmt.setBytes(13, campreleaseVO.getPicture5());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(CampReleaseVO campreleaseVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, campreleaseVO.getCampName());
			pstmt.setString(2, campreleaseVO.getLocation());
			pstmt.setDouble(3, campreleaseVO.getLatitude());
			pstmt.setDouble(4, campreleaseVO.getLongtitude());
			pstmt.setString(5, campreleaseVO.getCampDescription());
			pstmt.setInt(6, campreleaseVO.getCampPrice());
			pstmt.setTimestamp(7, campreleaseVO.getListedTime());
			pstmt.setBytes(8, campreleaseVO.getPicture1());
			pstmt.setBytes(9, campreleaseVO.getPicture2());
			pstmt.setBytes(10, campreleaseVO.getPicture3());
			pstmt.setBytes(11, campreleaseVO.getPicture4());
			pstmt.setBytes(12, campreleaseVO.getPicture5());
			pstmt.setInt(13, campreleaseVO.getCampId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer campId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, campId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public CampReleaseVO findByPrimaryKey(Integer campId) {
		// TODO Auto-generated method stub
		CampReleaseVO campreleaseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, campId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				campreleaseVO = new CampReleaseVO();
				campreleaseVO.setCampId(rs.getInt("CAMP_ID"));
				campreleaseVO.setMemberId(rs.getInt("MEMBER_ID"));
				campreleaseVO.setCampName(rs.getString("CAMP_NAME"));
				campreleaseVO.setLocation(rs.getString("LOCATION"));
				campreleaseVO.setLatitude(rs.getDouble("LATITUDE"));
				campreleaseVO.setLongtitude(rs.getDouble("LONGTITUDE"));
				campreleaseVO.setCampDescription(rs.getString("CAMP_DESCRIPTION"));
				campreleaseVO.setCampPrice(rs.getInt("CAMP_PRICE"));
				campreleaseVO.setListedTime(rs.getTimestamp("LISTED_TIME"));
				campreleaseVO.setPicture1(rs.getBytes("PICTURE1"));
				campreleaseVO.setPicture2(rs.getBytes("PICTURE2"));
				campreleaseVO.setPicture3(rs.getBytes("PICTURE3"));
				campreleaseVO.setPicture4(rs.getBytes("PICTURE4"));
				campreleaseVO.setPicture5(rs.getBytes("PICTURE5"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return campreleaseVO;
	}

	@Override
	public List<CampReleaseVO> getAll() {
		// TODO Auto-generated method stub
		List<CampReleaseVO> list = new ArrayList<CampReleaseVO>();
		CampReleaseVO campreleaseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				campreleaseVO = new CampReleaseVO();
				campreleaseVO.setCampId(rs.getInt("CAMP_ID"));
				campreleaseVO.setMemberId(rs.getInt("MEMBER_ID"));
				campreleaseVO.setCampName(rs.getString("CAMP_NAME"));
				campreleaseVO.setLocation(rs.getString("LOCATION"));
				campreleaseVO.setLatitude(rs.getDouble("LATITUDE"));
				campreleaseVO.setLongtitude(rs.getDouble("LONGTITUDE"));
				campreleaseVO.setCampDescription(rs.getString("CAMP_DESCRIPTION"));
				campreleaseVO.setCampPrice(rs.getInt("CAMP_PRICE"));
				campreleaseVO.setListedTime(rs.getTimestamp("LISTED_TIME"));
				campreleaseVO.setPicture1(rs.getBytes("PICTURE1"));
				campreleaseVO.setPicture2(rs.getBytes("PICTURE2"));
				campreleaseVO.setPicture3(rs.getBytes("PICTURE3"));
				campreleaseVO.setPicture4(rs.getBytes("PICTURE4"));
				campreleaseVO.setPicture5(rs.getBytes("PICTURE5"));
			
				list.add(campreleaseVO); // Store the row in the list
//				}
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		CampReleaseDAO dao = new CampReleaseDAO();

		// 新增
//		CampReleaseVO VO1 = new CampReleaseVO();
//		VO1.setMemberId(1);	
//		VO1.setCampName("空露營場ps");
//		VO1.setLocation("高雄市XX路1232134");
//		VO1.setLatitude(new Double(24.24324));
//		VO1.setLongtitude(new Double(54.2314));
//		VO1.setCampDescription("這也是露營地");
//		VO1.setCampPrice(888);
//		VO1.setListedTime(java.sql.Timestamp.valueOf("2021-05-20 00:00:00"));
//		
//		byte[] pic1 = null;
//		try {
//			pic1 = getPictureByteArray("WebContent/camprelease/images/camppic/17180.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		VO1.setPicture1(pic1);	
//		dao.insert(VO1);
//		System.out.println("OKKKKKKK");
//		
//		dao.insert(VO1);
//		System.out.println("success");
		
//
//		// 修改
//		CampReleaseVO VO2 = new CampReleaseVO();
//		VO2.setCampId(3201017);
//		VO2.setCampName("露營場");
//		VO2.setLocation("高潭市XX路");
//		VO2.setLatitude(new Double(23.24324));
//		VO2.setLongtitude(new Double(24.2314));
//		VO2.setCampDescription("露營地");
//		VO2.setCampPrice(777);
//		VO2.setListedTime(java.sql.Timestamp.valueOf("2021-05-20 00:00:00"));
//		byte[] pic2 = null;
//		try {
//			pic2 = getPictureByteArray("WebContent/camprelease/images/camppic/9448.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		VO2.setPicture1(pic2);		
//		dao.update(VO2);
//		System.out.println("success");
//
//		// 刪除
//		dao.delete(3201015);
//		System.out.println("success");

		// 查詢
		CampReleaseVO VO3 = dao.findByPrimaryKey(3201004);
		System.out.print(VO3.getCampId() + ",");
		System.out.print(VO3.getMemberId() + ",");
		System.out.print(VO3.getCampName() + ",");
		System.out.print(VO3.getLocation() + ",");
		System.out.print(VO3.getLatitude() + ",");
		System.out.print(VO3.getLongtitude() + ",");
		System.out.print(VO3.getCampDescription() + ",");
		System.out.println(VO3.getCampPrice() + ",");
		System.out.println(VO3.getListedTime() + ",");
		System.out.println(VO3.getPicture1() + ",");
		System.out.println(VO3.getPicture2() + ",");
		System.out.println(VO3.getPicture3() + ",");
		System.out.println(VO3.getPicture4() + ",");
		System.out.println(VO3.getPicture5() + ",");
		System.out.println("---------------------");
//
//		// 查詢
		List<CampReleaseVO> list = dao.getAll();
		for (CampReleaseVO a : list) {
			System.out.print(a.getCampId() + ",");
			System.out.print(a.getMemberId() + ",");
			System.out.print(a.getCampName() + ",");
			System.out.print(a.getLocation() + ",");
			System.out.print(a.getLatitude() + ",");
			System.out.print(a.getLongtitude() + ",");
			System.out.print(a.getCampDescription() + ",");
			System.out.print(a.getCampPrice() + ",");
			System.out.print(a.getListedTime() + ",");
			System.out.print(a.getPicture1() + ",");
			System.out.print(a.getPicture2() + ",");
			System.out.print(a.getPicture3() + ",");
			System.out.print(a.getPicture4() + ",");
			System.out.print(a.getPicture5() + ",");
			
			System.out.println();
		}
		
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
