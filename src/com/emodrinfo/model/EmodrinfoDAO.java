package com.emodrinfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmodrinfoDAO implements EmodrinfoDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO emodrinfoVO (emodr_id,product_no,qty,price,comm) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT emodr_infoid,emodr_id,product_no,qty,price,comm FROM emodrinfoVO order by emodr_infoid";
	private static final String GET_ONE_STMT = "SELECT emodr_infoid,emodr_id,product_no,qty,price,comm FROM emodrinfoVO where emodr_infoid = ?";
	private static final String DELETE = "DELETE FROM emodrinfoVO where emodr_infoid = ?";
	private static final String UPDATE = "UPDATE emodrinfoVO set emodr_id=?, product_no=?, qty=?, price=?, comm=? where emodr_infoid = ?";

	@Override
	public void insert(EmodrinfoVO emodrinfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, emodrinfoVO.getEmodr_id());
			pstmt.setInt(2, emodrinfoVO.getProduct_no());
			pstmt.setInt(3, emodrinfoVO.getQty());
			pstmt.setInt(4, emodrinfoVO.getPrice());
			pstmt.setString(5, emodrinfoVO.getComm());

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
	public void update(EmodrinfoVO emodrinfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, emodrinfoVO.getEmodr_id());
			pstmt.setInt(2, emodrinfoVO.getProduct_no());
			pstmt.setInt(3, emodrinfoVO.getQty());
			pstmt.setInt(4, emodrinfoVO.getPrice());
			pstmt.setString(5, emodrinfoVO.getComm());
			pstmt.setInt(6, emodrinfoVO.getEmodr_infoid());

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
	public void delete(Integer emodr_infoid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emodr_infoid);

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
	public EmodrinfoVO findByPrimaryKey(Integer emodr_infoid) {

		EmodrinfoVO emodrinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

}
