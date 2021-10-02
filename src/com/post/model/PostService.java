package com.post.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class PostService {
	private PostDAO_Interface dao;

	public PostService() {
		dao = new PostDAO();
	}

	public PostVO addPost(Integer authorId, String title, String article) {
		PostVO postVO = new PostVO();
		postVO.setAuthorId(authorId);
		postVO.setTitle(title);
		postVO.setArticle(article);
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		postVO.setCreated(timestamp);
		dao.insert(postVO);
		return postVO;
	}
	
	public PostVO updatePost(Integer postId, Integer authorId, String article) {
		PostVO postVO = new PostVO();
		postVO.setAuthorId(authorId);
		postVO.setArticle(article);
		postVO.setPostId(postId);
		dao.update(postVO);
		return postVO;
	}
	
	public void delete(Integer articleId) {
		dao.delete(articleId);
	}
	
    public List<PostVO> findByAuthor(Integer authorId){
    	return dao.findByAuthor(authorId);
    }
    
    public List<PostVO> getAll(){
    	return dao.getAll();
    }
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
