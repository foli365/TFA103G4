package com.postComment.model;

public class PostCommentService {
	private postCommentDAO_interface dao;
	
	public PostCommentService() {
		// TODO Auto-generated constructor stub
		dao = new PostCommentDao();
	}
	
	public PostCommentVO addPostComment(Integer articleId, Integer memberId,String content) {
		PostCommentVO postCommentVO = new PostCommentVO();
		postCommentVO.setArticleId(articleId);
		postCommentVO.setMemberId(memberId);
		postCommentVO.setContent(content);
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		postCommentVO.setCreated(timestamp);
		dao.insert(postCommentVO);
		return postCommentVO;
	}
	
	public PostCommentVO updatePostComment(Integer commentId, String content) {
		PostCommentVO postCommentVO = new PostCommentVO();
		postCommentVO.setCommentId(commentId);
		postCommentVO.setContent(content);
		dao.update(postCommentVO);
		return postCommentVO;
	}
	
	public void delete(Integer commentId) {
		dao.delete(commentId);
	}
}
