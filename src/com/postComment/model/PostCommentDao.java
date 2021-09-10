package com.postComment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostCommentDao implements postCommentDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/gocamping?serverTimezone=Asia/Taipei";
	String userid = "Yves";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO post_comment (ARTICLE_ID, MEMBER_ID, CONTENT, TIMESTAMP) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE post_comment set CONTENT=? where FOURM_COMMENT_ID = ?";
	private static final String DELETE = "DELETE FROM post_comment where FOURM_COMMENT_ID = ?";

	@Override
	public void insert(PostCommentVO commentVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
			
			pstmt.setInt(1, commentVO.getArticleId());
			pstmt.setInt(2, commentVO.getMemberId());
			pstmt.setString(3, commentVO.getContent());
			pstmt.setTimestamp(4, timestamp);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(PostCommentVO commentVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, commentVO.getContent());
			pstmt.setInt(2, commentVO.getCommentId());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(Integer commentId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, commentId);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static void main(String[] args) {
		PostCommentDao control = new PostCommentDao();
		
		PostCommentVO good = new PostCommentVO();
		good.setArticleId(2);
		good.setMemberId(3);
		good.setContent("Æg");
		
		control.insert(good);
		
//		PostCommentVO change = new PostCommentVO();
//		change.setCommentId(1);
//		change.setContent("Äê");
//		
//		control.update(change);
		
//		control.delete(1);
	}

}
