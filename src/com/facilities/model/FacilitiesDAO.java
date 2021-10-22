package com.facilities.model;

import java.sql.*;
import java.util.*;

public class FacilitiesDAO implements FacilitiesDAO_interface{
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/Gocamping?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	private static final String INSERT = "INSERT INTO FACILITIES (CAMP_ID, BBQ, WIFI, NOSMOKE, PETS) VALUES (?, ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM FACILITIES where CAMP_ID = ?";
	private static final String UPDATE = "UPDATE FACILITIES SET BBQ = ?, WIFI = ?, NOSMOKE = ?, PETS = ? WHERE CAMP_ID = ?";
	private static final String GET_ONE_STMT = "SELECT FACILITIES_ID, CAMP_ID, BBQ, WIFI, NOSMOKE, PETS FROM FACILITIES WHERE FACILITIES_ID = ?";
	private static final String GET_ALL_STMT = "SELECT FACILITIES_ID, CAMP_ID, BBQ, WIFI, NOSMOKE, PETS FROM FACILITIES";
	private static final String GET_ONE_CAMPID = "SELECT FACILITIES_ID, CAMP_ID, BBQ, WIFI, NOSMOKE, PETS FROM FACILITIES WHERE CAMP_ID = ?";

	@Override
	public void insert(FacilitiesVO facilitiesVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setInt(1, facilitiesVO.getCampId());
			pstmt.setInt(2, facilitiesVO.getBbq());
			pstmt.setInt(3, facilitiesVO.getWifi());
			pstmt.setInt(4, facilitiesVO.getNosmoke());
			pstmt.setInt(5, facilitiesVO.getPets());
			
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
	public void delete(Integer campId) {
		// TODO Auto-generated method stub
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
	public List<FacilitiesVO> getAll() {
		// TODO Auto-generated method stub
				List<FacilitiesVO> list = new ArrayList<FacilitiesVO>();
				FacilitiesVO facilitiesVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = DriverManager.getConnection(URL, USER, PASSWORD);
					pstmt = con.prepareStatement(GET_ALL_STMT);

					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						facilitiesVO = new FacilitiesVO();
						facilitiesVO.setFacilitiesId(rs.getInt("FACILITIES_ID"));
						facilitiesVO.setCampId(rs.getInt("CAMP_ID"));
						facilitiesVO.setBbq(rs.getInt("BBQ"));
						facilitiesVO.setNosmoke(rs.getInt("NOSMOKE"));
						facilitiesVO.setWifi(rs.getInt("WIFI"));
						facilitiesVO.setPets(rs.getInt("PETS"));
						list.add(facilitiesVO);
					}
				} catch (SQLException e) {
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

			@Override
			public void update(FacilitiesVO facilitiesVO) {
				// TODO Auto-generated method stub
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = DriverManager.getConnection(URL, USER, PASSWORD);
					pstmt = con.prepareStatement(UPDATE);
					
					pstmt.setInt(1, facilitiesVO.getBbq());
					pstmt.setInt(2, facilitiesVO.getWifi());
					pstmt.setInt(3, facilitiesVO.getNosmoke());
					pstmt.setInt(4, facilitiesVO.getPets());
					pstmt.setInt(5, facilitiesVO.getCampId());

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
			public FacilitiesVO findByPrimaryKey(Integer facilitiesId) {
				// TODO Auto-generated method stub
				FacilitiesVO facilitiesVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = DriverManager.getConnection(URL, USER, PASSWORD);
					pstmt = con.prepareStatement(GET_ONE_STMT);
					
					pstmt.setInt(1, facilitiesId);
					
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						facilitiesVO = new FacilitiesVO();
						facilitiesVO.setFacilitiesId(rs.getInt("FACILITIES_ID"));
						facilitiesVO.setCampId(rs.getInt("CAMP_ID"));
						facilitiesVO.setBbq(rs.getInt("BBQ"));
						facilitiesVO.setWifi(rs.getInt("WIFI"));
						facilitiesVO.setNosmoke(rs.getInt("NOSMOKE"));
						facilitiesVO.setPets(rs.getInt("PETS"));
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
				return facilitiesVO;
			}
			
			@Override
			public FacilitiesVO findbyCampId(Integer campId) {
				// TODO Auto-generated method stub
				FacilitiesVO facilitiesVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = DriverManager.getConnection(URL, USER, PASSWORD);
					pstmt = con.prepareStatement(GET_ONE_CAMPID);
					
					pstmt.setInt(1, campId);
					
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						facilitiesVO = new FacilitiesVO();
						facilitiesVO.setFacilitiesId(rs.getInt("FACILITIES_ID"));
						facilitiesVO.setCampId(rs.getInt("CAMP_ID"));
						facilitiesVO.setBbq(rs.getInt("BBQ"));
						facilitiesVO.setWifi(rs.getInt("WIFI"));
						facilitiesVO.setNosmoke(rs.getInt("NOSMOKE"));
						facilitiesVO.setPets(rs.getInt("PETS"));
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
				return facilitiesVO;
			}

			@Override
			public void facilitiesInsertWithCampId(FacilitiesVO facilitiesVO, Connection con) {
				// TODO Auto-generated method stub
				PreparedStatement pstmt = null;
				
				try {
					
					String cols[] = {"FACILITIES_ID"};
					pstmt = con.prepareStatement(INSERT, cols);
					
					
					pstmt.setInt(1, facilitiesVO.getCampId());
					pstmt.setInt(2, facilitiesVO.getBbq());
					pstmt.setInt(3, facilitiesVO.getWifi());
					pstmt.setInt(4, facilitiesVO.getNosmoke());
					pstmt.setInt(5, facilitiesVO.getPets());
					
					pstmt.executeUpdate();
					
					Integer nextFacilitiesId = null;
					ResultSet rs = pstmt.getGeneratedKeys();
					if(rs.next()) {
						nextFacilitiesId = rs.getInt(1);
					}
				} catch(SQLException se) {
					if(con != null) {
						try {
							con.rollback();
						} catch(SQLException sqle) {
							throw new RuntimeException("Rollback error occured" + sqle.getMessage());
						}
					}
					throw new RuntimeException("A database error occured. " + se.getMessage()); 
				} finally {
					if(pstmt != null) {
						try {
							pstmt.close();
						} catch(SQLException se) {
							se.printStackTrace();
						}
					}
				}
			}
			public static void main(String[] args) {
				
				FacilitiesDAO dao = new FacilitiesDAO();
				
				//insert
//				FacilitiesVO facilitiesVO = new FacilitiesVO();
//				facilitiesVO.setCampId(5004);
//				facilitiesVO.setBbq(1);
//				facilitiesVO.setWifi(0);
//				facilitiesVO.setNosmoke(0);
//				facilitiesVO.setPets(1);
//				dao.insert(facilitiesVO);
//				System.out.println("�w�w");
				
				//update
				FacilitiesVO facilitiesVO1 = new FacilitiesVO();
				facilitiesVO1.setCampId(5004);
				facilitiesVO1.setBbq(1);
				facilitiesVO1.setWifi(1);
				facilitiesVO1.setNosmoke(1);
				facilitiesVO1.setPets(1);
				dao.update(facilitiesVO1);
				System.out.println("�w�w");
				
//				// delete
//				dao.delete(5002);
//				System.out.println("success");
				
				// Search
				FacilitiesVO VO3 = dao.findByPrimaryKey(1);
				System.out.print(VO3.getFacilitiesId() + ",");
				System.out.print(VO3.getCampId() + ",");
				System.out.print(VO3.getBbq() + ",");
				System.out.print(VO3.getWifi() + ",");
				System.out.print(VO3.getNosmoke() + ",");
				System.out.print(VO3.getPets() + ",");
				System.out.println("---------------------");
		//
//				// Search All
				List<FacilitiesVO> list = dao.getAll();
				for (FacilitiesVO a : list) {
					System.out.print(a.getFacilitiesId() + ",");
					System.out.print(a.getCampId() + ",");
					System.out.print(a.getBbq() + ",");
					System.out.print(a.getWifi() + ",");
					System.out.print(a.getNosmoke() + ",");
					System.out.print(a.getPets() + ",");
					
					System.out.println();
				}
				
			}
		}