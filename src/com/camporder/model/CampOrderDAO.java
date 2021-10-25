package com.camporder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.customerplan.model.CustomerPlanDAO;
import com.customerplan.model.CustomerPlanVO;


public class CampOrderDAO implements CampOrderDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/GoCamping?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	public static final String INSERT_STMT = "INSERT INTO CAMP_ORDER (CAMP_ID, MEMBER_ID, GUEST_NUMBER, "
			+ "CHECK_IN_DATE, CHECK_OUT_DATE, ORDER_DATE, PAYMENT_DEADLINE, ORDER_STATUS, ORDER_TOTAL, "
			+ "COMMENT, PICTURE1, PICTURE2, PICTURE3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE CAMP_ORDER SET MEMBER_ID = ?, "
			+ "GUEST_NUMBER = ?, CHECK_IN_DATE = ?, CHECK_OUT_DATE = ?, ORDER_DATE = ?, PAYMENT_DEADLINE = ?, "
			+ "ORDER_STATUS = ?, ORDER_TOTAL = ?, COMMENT = ?, PICTURE1 = ?, PICTURE2 = ?, PICTURE3 = ? "
			+ "WHERE CAMP_ORDER_ID = ?"; // Foreign Key����update
	public static final String DELETE_STMT = "DELETE FROM CAMP_ORDER WHERE CAMP_ORDER_ID = ?";
	public static final String FIND_BY_PK = "SELECT CAMP_ORDER_ID, CAMP_ID, MEMBER_ID, GUEST_NUMBER, "
			+ "CHECK_IN_DATE, CHECK_OUT_DATE, ORDER_DATE, PAYMENT_DEADLINE, ORDER_STATUS, ORDER_TOTAL, COMMENT "
			+ "FROM CAMP_ORDER WHERE CAMP_ORDER_ID = ?";
	public static final String GET_ALL = "SELECT CAMP_ORDER_ID, CAMP_ID, MEMBER_ID, GUEST_NUMBER, "
			+ "CHECK_IN_DATE, CHECK_OUT_DATE, ORDER_DATE, PAYMENT_DEADLINE, ORDER_STATUS, ORDER_TOTAL, COMMENT "
			+ "FROM CAMP_ORDER ORDER BY CAMP_ORDER_ID";


	static { 
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void add(CampOrderVO campOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, campOrderVO.getCampId());
			pstmt.setInt(2, campOrderVO.getMemberId());
			pstmt.setInt(3, campOrderVO.getGuestNumber());
			pstmt.setDate(4, campOrderVO.getCheckInDate());
			pstmt.setDate(5, campOrderVO.getCheckOutDate());
			pstmt.setTimestamp(6, campOrderVO.getOrderDate());
			pstmt.setTimestamp(7, campOrderVO.getPaymentDeadline());
			pstmt.setString(8, campOrderVO.getOrderStatus());
			pstmt.setInt(9, campOrderVO.getOrderTotal());
			pstmt.setString(10, campOrderVO.getComment());
			pstmt.setBytes(11, campOrderVO.getPicture1());
			pstmt.setBytes(12, campOrderVO.getPicture2());
			pstmt.setBytes(13, campOrderVO.getPicture3());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(CampOrderVO campOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, campOrderVO.getMemberId());
			pstmt.setInt(2, campOrderVO.getGuestNumber());
			pstmt.setDate(3, campOrderVO.getCheckInDate());
			pstmt.setDate(4, campOrderVO.getCheckOutDate());
			pstmt.setTimestamp(5, campOrderVO.getOrderDate());
			pstmt.setTimestamp(6, campOrderVO.getPaymentDeadline());
			pstmt.setString(7, campOrderVO.getOrderStatus());
			pstmt.setInt(8, campOrderVO.getOrderTotal());
			pstmt.setString(9, campOrderVO.getComment());
			pstmt.setBytes(10, campOrderVO.getPicture1());
			pstmt.setBytes(11, campOrderVO.getPicture2());
			pstmt.setBytes(12, campOrderVO.getPicture3());
			pstmt.setInt(13, campOrderVO.getCampOrderId());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer campOrderId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, campOrderId);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public CampOrderVO findbyPrimaryKey(Integer campOrderId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CampOrderVO campOrderVO = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, campOrderId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				campOrderVO = new CampOrderVO();
				campOrderVO.setCampOrderId(campOrderId);
				campOrderVO.setCampId(rs.getInt("CAMP_ID"));
				campOrderVO.setMemberId(rs.getInt("MEMBER_ID"));
				campOrderVO.setGuestNumber(rs.getInt("GUEST_NUMBER"));
				campOrderVO.setCheckInDate(rs.getDate("CHECK_IN_DATE"));
				campOrderVO.setCheckOutDate(rs.getDate("CHECK_OUT_DATE"));
				campOrderVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				campOrderVO.setPaymentDeadline(rs.getTimestamp("PAYMENT_DEADLINE"));
				campOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				campOrderVO.setOrderTotal(rs.getInt("ORDER_TOTAL"));
				campOrderVO.setComment(rs.getString("COMMENT"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return campOrderVO;
	}

	@Override
	public List<CampOrderVO> getAll() {
		List<CampOrderVO> campOrderList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CampOrderVO campOrderVO = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				campOrderVO = new CampOrderVO();
				campOrderVO.setCampOrderId(rs.getInt("CAMP_ORDER_ID"));
				campOrderVO.setCampId(rs.getInt("CAMP_ID"));
				campOrderVO.setMemberId(rs.getInt("MEMBER_ID"));
				campOrderVO.setGuestNumber(rs.getInt("GUEST_NUMBER"));
				campOrderVO.setCheckInDate(rs.getDate("CHECK_IN_DATE"));
				campOrderVO.setCheckOutDate(rs.getDate("CHECK_OUT_DATE"));
				campOrderVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				campOrderVO.setPaymentDeadline(rs.getTimestamp("PAYMENT_DEADLINE"));
				campOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				campOrderVO.setOrderTotal(rs.getInt("ORDER_TOTAL"));
				campOrderVO.setComment(rs.getString("COMMENT"));
				
				campOrderList.add(campOrderVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return campOrderList;
	}
	
	public void insertWithPlans(CampOrderVO campOrderVO , List<CustomerPlanVO> list) throws ClassNotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;
		java.sql.CallableStatement cs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單
			String cols[] = {"CAMP_ORDER_ID"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setInt(1, campOrderVO.getCampId());
			pstmt.setInt(2, campOrderVO.getMemberId());
			pstmt.setInt(3, campOrderVO.getGuestNumber());
			pstmt.setDate(4, campOrderVO.getCheckInDate());
			pstmt.setDate(5, campOrderVO.getCheckOutDate());
			pstmt.setTimestamp(6, campOrderVO.getOrderDate());
			pstmt.setTimestamp(7, campOrderVO.getPaymentDeadline());
			pstmt.setString(8, campOrderVO.getOrderStatus());
			pstmt.setInt(9, campOrderVO.getOrderTotal());
			pstmt.setString(10, campOrderVO.getComment());
			pstmt.setBytes(11, campOrderVO.getPicture1());
			pstmt.setBytes(12, campOrderVO.getPicture2());
			pstmt.setBytes(13, campOrderVO.getPicture3());
			Statement stmt=	con.createStatement();
//			stmt.executeUpdate("set auto_increment_offset=1000;");    //自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;"); //自增主鍵-遞增
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_orderId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_orderId = rs.getString(1);
				System.out.println("自增主鍵值= " + next_orderId +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			CustomerPlanDAO dao = new CustomerPlanDAO();
			for (CustomerPlanVO aPlan : list) {
				aPlan.setCampOrderId(new Integer(next_orderId));
				dao.insert2(aPlan,con);
			}
			
			cs = con.prepareCall("{call filldates(?, ?, ?, ?)}");
			cs.setInt(1, campOrderVO.getCampId());
			cs.setDate(2, campOrderVO.getCheckInDate());
			cs.setDate(3, campOrderVO.getCheckOutDate());
			cs.setInt(4, campOrderVO.getGuestNumber());
			
			cs.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增訂單編號" + next_orderId + "時,共有自訂方案" + list.size()
					+ "組同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-campOrder");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (cs != null) {
				try {
					cs.close();
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

	}

}
