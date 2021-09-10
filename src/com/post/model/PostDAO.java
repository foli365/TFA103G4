package com.post.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements PostDAO_Interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/gocamping?serverTimezone=Asia/Taipei";
	String userid = "Yves";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO post (author_id, article, created, picture1, picture2, picture3) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE post set article=?, picture1=?, picture2=?, picture3=? where ARTICLE_ID = ?";
	private static final String DELETE = "DELETE FROM post where article_id = ?";
	private static final String GET_ONE_STMT = "SELECT author_id,article_id,article,created,picture1,picture2,picture3 FROM post where author_id= ?";

	@Override
	public void insert(PostVO postVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, postVO.getAuthorId());
			pstmt.setString(2, postVO.getArticle());
			pstmt.setTimestamp(3, timestamp);
			pstmt.setBytes(4, postVO.getPic1());
			pstmt.setBytes(5, postVO.getPic2());
			pstmt.setBytes(6, postVO.getPic3());

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
	public void update(PostVO postVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, postVO.getArticle());
			pstmt.setBytes(2, postVO.getPic1());
			pstmt.setBytes(3, postVO.getPic2());
			pstmt.setBytes(4, postVO.getPic3());
			pstmt.setInt(5, postVO.getArticleId());

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
	public void delete(Integer articleId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, authorId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post = new PostVO();
				post.setArticleId(rs.getInt("article_id"));
				post.setAuthorId(rs.getInt("author_id"));
				post.setArticle(rs.getString("article"));
				post.setCreated(rs.getTimestamp("created"));
				post.setPic1(rs.getBytes("picture1"));
				post.setPic2(rs.getBytes("picture1"));
				post.setPic3(rs.getBytes("picture1"));
				list.add(post);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public static void main(String[] args) {

		PostDAO control = new PostDAO();

		// 新增
//		PostVO ig = new PostVO();
//		ig.setAuthorId(4);
//		ig.setArticle(
//				"Praesent eget enim a odio pretium iaculis. Vestibulum vestibulum est vitae felis malesuada, at mollis ante rhoncus.");
//		control.insert(ig);

		// 修改
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

		// 刪除
//		control.delete(5);
		
		//找自己的文章
		List<PostVO> list = control.findByAuthor(1);
		for (PostVO postVO : list) {
			System.out.println("文章編號: " + postVO.getArticleId());
			System.out.println("作者: " + postVO.getAuthorId());
			System.out.println("內文: " + postVO.getArticle());
			System.out.println("建立時間: " + postVO.getCreated());
			System.out.println("圖片一: " + postVO.getPic1());
			System.out.println("圖片二: " + postVO.getPic2());
			System.out.println("圖片三: " + postVO.getPic3());
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
