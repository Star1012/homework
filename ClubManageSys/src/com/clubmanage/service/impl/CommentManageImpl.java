package com.clubmanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.clubmanage.dao.CommentDao;
import com.clubmanage.model.Comment;
import com.clubmanage.service.CommentManage;

@Component("commentManage")
public class CommentManageImpl implements CommentManage
{

	private CommentDao commentDao;
	
	@Override
	public void addComment(Comment comment)
	{
		commentDao.saveComment(comment);
	}

	@Override
	public void deleteComment(String activityId, String commentId)
	{
		commentDao.deleteCommentById(activityId, commentId);
	}

	@Override
	public void updateComment(Comment comment)
	{
		commentDao.updateComment(comment);
	}

	@Override
	public List<Comment> listComments()
	{
		return commentDao.getComments();
	}

	@Override
	public boolean commentExists(Comment comment)
	{
		return commentDao.checkCommentExistsWithId(comment.getId().getActivityId(), comment.getId().getCommentId());
	}

	@Override
	public Comment loadCommentById(String activityId, String commentId)
	{
		return commentDao.loadById(activityId, commentId);
	}

	public CommentDao getCommentDao()
	{
		return commentDao;
	}

	@Resource
	public void setCommentDao(CommentDao commentDao)
	{
		this.commentDao = commentDao;
	}

}
