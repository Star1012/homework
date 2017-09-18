package com.clubmanage.dao;

import java.util.List;

import com.clubmanage.model.Comment;

public interface CommentDao
{
	public void saveComment(Comment comment);
	public void deleteCommentById(String activityId, String commentId);
	public void updateComment(Comment comment);
	public List<Comment> getComments();
	public boolean checkCommentExistsWithId(String activityId, String commentId);
	public Comment loadById(String activityId, String commentId);
}
