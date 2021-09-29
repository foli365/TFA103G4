package com.post.model;

import java.io.FileInputStream;
import java.io.IOException;
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

public class PostDAO implements PostDAO_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/camping");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO post (author_id, article, created) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE post set article=? where ARTICLE_ID = ?";
	private static final String DELETE = "DELETE FROM post where article_id = ?";
	private static final String GET_ONE_STMT = "SELECT author_id,article_id,article,created,picture1,picture2,picture3 FROM post where author_id= ?";
	private static final String GET_ALL_STMT = "SELECT author_id,article_id,article,picture1,picture2,picture3 FROM post order by created";


	@Override
	public void insert(PostVO postVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, postVO.getAuthorId());
			pstmt.setString(2, postVO.getArticle());
			pstmt.setTimestamp(3, timestamp);

			pstmt.executeUpdate();

		} catch (Exception e) {
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
	public void update(PostVO postVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, postVO.getArticle());
			pstmt.setInt(2, postVO.getPostId());

			pstmt.executeUpdate();
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
	public void delete(Integer articleId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleId);

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
	public List<PostVO> findByAuthor(Integer authorId) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO post = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, authorId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post = new PostVO();
				post.setPostId(rs.getInt("article_id"));
				post.setAuthorId(rs.getInt("author_id"));
				post.setArticle(rs.getString("article"));
				post.setCreated(rs.getTimestamp("created"));
				list.add(post);
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

		// TODO Auto-generated method stub
		return list;
	}
	
	public List<PostVO> getAll(){
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				postVO = new PostVO();
				postVO.setPostId(rs.getInt("article_id"));
				postVO.setAuthorId(rs.getInt("author_id"));
				postVO.setArticle(rs.getString("article"));
				postVO.setCreated(rs.getTimestamp("created"));
				list.add(postVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
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

		PostDAO control = new PostDAO();

		// �s�W
//		PostVO ig = new PostVO();
//		ig.setAuthorId(4);
//		ig.setArticle(
//				"Praesent eget enim a odio pretium iaculis. Vestibulum vestibulum est vitae felis malesuada, at mollis ante rhoncus.");
//		control.insert(ig);

		// �ק�
//		PostVO edit = new PostVO();
//		edit.setArticle("cool");
//		edit.setArticleId(3);
//		byte[] pic1 = null;
//		try {
//			pic1 = getPictureByteArray("img/2.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		edit.setPic1(pic1);
//		control.update(edit);

		// �R��
//		control.delete(5);
		
		//��ۤv���峹
		List<PostVO> list = control.findByAuthor(1);
		for (PostVO postVO : list) {
			System.out.println("�峹�s��: " + postVO.getPostId());
			System.out.println("�@��: " + postVO.getAuthorId());
			System.out.println("����: " + postVO.getArticle());
			System.out.println("�إ߮ɶ�: " + postVO.getCreated());
			System.out.println("==================="); 
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
