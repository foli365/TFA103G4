package com.postComment.model;

public interface postCommentDAO_interface {
	public void insert (PostCommentVO commentVO);
	public void update (PostCommentVO commentVO);
	public void delete (Integer commentId);
}
