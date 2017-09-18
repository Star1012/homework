package com.clubmanage.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.clubmanage.model.Comment;
import com.clubmanage.model.CommentId;
import com.clubmanage.service.CommentManage;
import com.clubmanage.service.ActivityManage;
import com.clubmanage.vo.CommentInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component("commentAction")
public class CommentAction extends ActionSupport implements ModelDriven
{
	
	private static final long serialVersionUID = 1L;
	
	private Comment comment;
	private List<Comment> comments;
	private CommentInfo commentInfo;
	private CommentManage commentManage;
	private ActivityManage activityManage;
	
	public String Create()
	{
		Comment c = new Comment();
		CommentId id = new CommentId(commentInfo.getCommentId(),commentInfo.getActivityId());
		c.setId(id);
		c.setActivity(activityManage.loadActivityById(commentInfo.getActivityId()));
		c.setPinglunzhe(commentInfo.getPinglunzhe());
		c.setJieshoufang(commentInfo.getJieshoufang());
		c.setPlneirong(commentInfo.getPlneirong());
		c.setShijian(commentInfo.getShijian());
		c.setNeirong(commentInfo.getNeirong());
		c.setZan(commentInfo.getZan());
		commentManage.addComment(c);
		comments = commentManage.listComments();
		HttpServletRequest request1 = ServletActionContext.getRequest();
		request1.getSession().setAttribute("commentKey", comments);
		return "success";
	}
	
	public String Retrieve()
	{
		return "success";
	}
	
	public String Update()
	{
		Comment c = new Comment();
		CommentId id = new CommentId(commentInfo.getCommentId(),commentInfo.getActivityId());
		c.setId(id);
		c.setPinglunzhe(commentInfo.getPinglunzhe());
		c.setJieshoufang(commentInfo.getJieshoufang());
		c.setPlneirong(commentInfo.getPlneirong());
		c.setShijian(commentInfo.getShijian());
		c.setNeirong(commentInfo.getNeirong());
		c.setZan(commentInfo.getZan());
		commentManage.updateComment(c);
		comments = commentManage.listComments();
		HttpServletRequest request1 = ServletActionContext.getRequest();
		request1.getSession().setAttribute("commentKey", comments);
		return "success";
	}
	
	public String Delete()
	{
		return "success";
	}
	
	public Comment getComment()
	{
		return comment;
	}
	public void setComment(Comment comment)
	{
		this.comment = comment;
	}
	public List<Comment> getComments()
	{
		return comments;
	}
	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}
	public CommentInfo getCommentInfo()
	{
		return commentInfo;
	}
	@Resource
	public void setCommentInfo(CommentInfo commentInfo)
	{
		this.commentInfo = commentInfo;
	}
	public Object getModel()
	{
		return this.commentInfo;
	}
	public CommentManage getCommentManage()
	{
		return commentManage;
	}
	@Resource
	public void setCommentManage(CommentManage commentManage)
	{
		this.commentManage = commentManage;
	}

	public ActivityManage getActivityManage()
	{
		return activityManage;
	}
	@Resource
	public void setActivityManage(ActivityManage activityManage)
	{
		this.activityManage = activityManage;
	}
}
