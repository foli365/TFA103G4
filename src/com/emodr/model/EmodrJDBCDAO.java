package com.emodr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmodrJDBCDAO implements EmodrDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/gocamping?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO emodr (member_id,emodr_date,receipient,addr,mobile,totalprice,emodr_status) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT emodr_id,member_id,emodr_date,receipient,addr,mobile,totalprice,emodr_status FROM emodr order by emodr_id";
	private static final String GET_ONE_STMT = "SELECT emodr_id,member_id,emodr_date,receipient,addr,mobile,totalprice,emodr_status FROM emodr where emodr_id = ?";
	private static final String DELETE = "DELETE FROM emodr where emodr_id = ?";
	private static final String UPDATE = "UPDATE emodr set member_id=?, emodr_date=?, receipient=?, addr=?, mobile=?, totalprice=?,emodr_status=? where emodr_id = ?";

	@Override
	public void insert(EmodrVO emodrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, emodrVO.getMember_id());
			pstmt.setDate(2, emodrVO.getEmodr_date());
			pstmt.setString(3, emodrVO.getReceipient());
			pstmt.setString(4, emodrVO.getAddr());
			pstmt.setString(5, emodrVO.getMobile());
			pstmt.setDouble(6, emodrVO.getTotalprice());
			pstmt.setBoolean(7, emodrVO.getEmodr_status());

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
	public void update(EmodrVO emodrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, emodrVO.getMember_id());
			pstmt.setDate(2, emodrVO.getEmodr_date());
			pstmt.setString(3, emodrVO.getReceipient());
			pstmt.setString(4, emodrVO.getAddr());
			pstmt.setString(5, emodrVO.getMobile());
			pstmt.setDouble(6, emodrVO.getTotalprice());
			pstmt.setBoolean(7, emodrVO.getEmodr_status());
			pstmt.setInt(8, emodrVO.getEmodr_id());

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
	public void delete(Integer emodrid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emodrid);

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
	public EmodrVO findByPrimaryKey(Integer emodrid) {
		EmodrVO emodrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emodrid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// emodrVO �]�٬� Domain objects
				emodrVO = new EmodrVO();
				emodrVO.setEmodr_id(rs.getInt("emodr_id"));
				emodrVO.setMember_id(rs.getInt("member_id"));
				emodrVO.setEmodr_date(rs.getDate("emodr_date"));
				emodrVO.setReceipient(rs.getString("receipient"));
				emodrVO.setAddr(rs.getString("addr"));
				emodrVO.setMobile(rs.getString("mobile"));
				emodrVO.setTotalprice(rs.getDouble("totalprice"));
				emodrVO.setEmodr_status(rs.getBoolean("emodr_status"));
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

		return emodrVO;
	}

	@Override
	public List<EmodrVO> getAll() {
		List<EmodrVO> list = new ArrayList<EmodrVO>();
		EmodrVO emodrVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// emodrVO �]�٬� Domain objects
				emodrVO = new EmodrVO();
				emodrVO.setEmodr_id(rs.getInt("emodr_id"));
				emodrVO.setMember_id(rs.getInt("member_id"));
				emodrVO.setEmodr_date(rs.getDate("emodr_date"));
				emodrVO.setReceipient(rs.getString("receipient"));
				emodrVO.setAddr(rs.getString("addr"));
				emodrVO.setMobile(rs.getString("mobile"));
				emodrVO.setTotalprice(rs.getDouble("totalprice"));
				emodrVO.setEmodr_status(rs.getBoolean("emodr_status"));
				list.add(emodrVO); // Store the row in the list
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

	@Override
	public List<EmodrVO> notDisplay(Integer emodrid) {
		// TODO Auto-generated method stub
		return null;
	}



}
