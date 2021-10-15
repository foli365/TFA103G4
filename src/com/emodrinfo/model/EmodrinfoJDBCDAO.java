package com.emodrinfo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmodrinfoJDBCDAO implements EmodrinfoDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO emodrinfo (emodr_id,product_no,qty,price,comm) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT emodr_infoid,emodr_id,product_no,qty,price,comm FROM emodrinfo order by emodr_infoid";
	private static final String GET_ONE_STMT = "SELECT emodr_infoid,emodr_id,product_no,qty,price,comm FROM emodrinfo where emodr_infoid = ?";
	private static final String DELETE = "DELETE FROM emodrinfo where emodr_infoid = ?";
	private static final String UPDATE = "UPDATE emodrinfo set emodr_id=?, product_no=?, qty=?, price=?, comm=? where emodr_infoid = ?";

	@Override
	public void insert(EmodrinfoVO emodrinfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, emodrinfoVO.getEmodr_id());
			pstmt.setInt(2, emodrinfoVO.getProduct_no());
			pstmt.setInt(3, emodrinfoVO.getQty());
			pstmt.setInt(4, emodrinfoVO.getPrice());
			pstmt.setString(5, emodrinfoVO.getComm());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(EmodrinfoVO emodrinfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, emodrinfoVO.getEmodr_id());
			pstmt.setInt(2, emodrinfoVO.getProduct_no());
			pstmt.setInt(3, emodrinfoVO.getQty());
			pstmt.setInt(4, emodrinfoVO.getPrice());
			pstmt.setString(5, emodrinfoVO.getComm());
			pstmt.setInt(6, emodrinfoVO.getEmodr_infoid());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer emodr_infoid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emodr_infoid);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public EmodrinfoVO findByPrimaryKey(Integer emodr_infoid) {
		EmodrinfoVO emodrinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emodr_infoid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// emodrinfoVO 也稱為 Domain objects
				emodrinfoVO = new EmodrinfoVO();
				emodrinfoVO.setEmodr_infoid(rs.getInt("emodr_infoid"));
				emodrinfoVO.setEmodr_id(rs.getInt("emodr_id"));
				emodrinfoVO.setProduct_no(rs.getInt("product_no"));
				emodrinfoVO.setQty(rs.getInt("qty"));
				emodrinfoVO.setPrice(rs.getInt("price"));
				emodrinfoVO.setComm(rs.getString("comm"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return emodrinfoVO;
	}

	@Override
	public List<EmodrinfoVO> getAll() {
		List<EmodrinfoVO> list = new ArrayList<EmodrinfoVO>();
		EmodrinfoVO emodrinfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// emodrinfoVO 也稱為 Domain objects
				emodrinfoVO = new EmodrinfoVO();
				emodrinfoVO.setEmodr_infoid(rs.getInt("emodr_infoid"));
				emodrinfoVO.setEmodr_id(rs.getInt("emodr_id"));
				emodrinfoVO.setProduct_no(rs.getInt("product_no"));
				emodrinfoVO.setQty(rs.getInt("qty"));
				emodrinfoVO.setPrice(rs.getInt("price"));
				emodrinfoVO.setComm(rs.getString("comm"));
				list.add(emodrinfoVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	
//測試用=====================================================
	public static void main(String[] args) {

		EmodrinfoJDBCDAO dao = new EmodrinfoJDBCDAO();

		// 新增
		EmodrinfoVO emodrinfoVO1 = new EmodrinfoVO();
		emodrinfoVO1.setEmodr_id(1);
		emodrinfoVO1.setProduct_no(999);
		emodrinfoVO1.setQty(100);
		emodrinfoVO1.setPrice(500);
		emodrinfoVO1.setComm("不好看");
		dao.insert(emodrinfoVO1);

		// 修改
		EmodrinfoVO emodrinfoVO2 = new EmodrinfoVO();
		emodrinfoVO2.setEmodr_infoid(4);
		emodrinfoVO2.setEmodr_id(5);
		emodrinfoVO2.setProduct_no(199);
		emodrinfoVO2.setQty(90);
		emodrinfoVO2.setPrice(555);
		emodrinfoVO2.setComm("好看");
		dao.update(emodrinfoVO2);

		// 刪除
		dao.delete(7);

		// 查詢
		EmodrinfoVO emodrinfoVO3 = dao.findByPrimaryKey(1);
		System.out.print(emodrinfoVO3.getEmodr_infoid() + ",");
		System.out.print(emodrinfoVO3.getEmodr_id() + ",");
		System.out.print(emodrinfoVO3.getProduct_no() + ",");
		System.out.print(emodrinfoVO3.getQty() + ",");
		System.out.print(emodrinfoVO3.getPrice() + ",");
		System.out.print(emodrinfoVO3.getComm());
		System.out.println("---------------------");

		// 查詢
		List<EmodrinfoVO> list = dao.getAll();
		for (EmodrinfoVO aEmodrinfo : list) {
			System.out.print(aEmodrinfo.getEmodr_infoid() + ",");
			System.out.print(aEmodrinfo.getEmodr_id() + ",");
			System.out.print(aEmodrinfo.getProduct_no() + ",");
			System.out.print(aEmodrinfo.getQty() + ",");
			System.out.print(aEmodrinfo.getPrice() + ",");
			System.out.print(aEmodrinfo.getComm());
			System.out.println();
		}
	}

}
