package com.clubmanage.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.clubmanage.dao.CommentDao;
import com.clubmanage.model.Comment;

@Component("commentDao")
public class CommentDaoImpl implements CommentDao
{

	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void saveComment(Comment comment)
	{
		hibernateTemplate.save(comment);
	}

	@Override
	public void deleteCommentById(String activityId, String commentId)
	{
		hibernateTemplate.delete(loadById(activityId,commentId));
	}

	@Override
	public void updateComment(Comment comment)
	{
		hibernateTemplate.update(comment);
	}

	@Override
	public List<Comment> getComments()
	{
		List<Comment> comments = hibernateTemplate.find("from Comment");
		return comments;
	}

	@Override
	public boolean checkCommentExistsWithId(String activityId, String commentId)
	{
		List<Comment> comments = hibernateTemplate.find("from Comment c where c.id.activityId='" + activityId + "' and c.id.commentId='" + commentId + "'");
		if(comments.size() > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public Comment loadById(String activityId, String commentId)
	{
		Comment comment = (Comment) hibernateTemplate.find("from Comment c where c.id.activityId='" + activityId + "' and c.id.commentId='" + commentId + "'");
		return comment;
	}

	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
