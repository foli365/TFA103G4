package com.facilities.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class FacilitiesDAO implements FacilitiesDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/gocamping");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO CAMP_FACILITIES (CAMP_ID, FACILITIES) VALUES (?, ?)";
	private static final String DELETE = "DELETE FROM CAMP_FACILITIES where FACILITIES_ID = ?";
	private static final String GET_ALL_BY_CAMPID = "SELECT FACILITIES FROM CAMP_FACILITIES WHERE CAMP_ID = ?";

	@Override
	public void insert(FacilitiesVO facilitiesVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setInt(1, facilitiesVO.getCampId());
			pstmt.setBytes(2, facilitiesVO.getFacilities());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer facilitiesId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, facilitiesId);

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
	public List<FacilitiesVO> getAllByCampId(Integer campId) {
		// TODO Auto-generated method stub
				List<FacilitiesVO> list = new ArrayList<FacilitiesVO>();
				FacilitiesVO facilitiesVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALL_BY_CAMPID);

					pstmt.setInt(1, campId);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						facilitiesVO = new FacilitiesVO();
						facilitiesVO.setFacilities(rs.getBytes("FACILITIES"));
						byte[] imagesBytes = rs.getBytes("FACILITIES");
						if (imagesBytes != null) {
							String base64Img = Base64.getEncoder().encodeToString(imagesBytes);
							facilitiesVO.setBase64Image(base64Img);
						}
						list.add(facilitiesVO);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
			
			public static byte[] getPictureByteArray(String path) throws IOException {
				FileInputStream fis = new FileInputStream(path);
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				return buffer;
			}
			
		}