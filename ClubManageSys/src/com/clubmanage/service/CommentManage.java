package com.clubmanage.service;

import java.util.List;

import com.clubmanage.model.Comment;

public interface CommentManage
{
	public void addComment(Comment comment);
	public void deleteComment(String activityId, String commentId);
	public void updateComment(Comment comment);
	public List<Comment> listComments();
	public boolean commentExists(Comment comment);
	public Comment loadCommentById(String activityId, String commentId);
}
