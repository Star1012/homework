package com.clubmanage.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.clubmanage.model.Activity;
import com.clubmanage.model.Comment;
import com.clubmanage.service.ActivityManage;
import com.clubmanage.service.CommentManage;
import com.clubmanage.vo.ActivityInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component("activityAction")
public class ActivityAction extends ActionSupport implements ModelDriven
{
	
	private static final long serialVersionUID = 1L;
	
	private Activity activity;
	private List<Activity> activitys;
	private ActivityInfo activityInfo;
	private ActivityManage activityManage;
	private List<Comment> comments;
	private CommentManage commentManage;
	
	public String Create()
	{
		Activity a = new Activity();
		a.setActivityId(activityInfo.getActivityId());
		a.setZhuti(activityInfo.getZhuti());
		a.setGaishu(activityInfo.getGaishu());
		a.setGuanjianzi(activityInfo.getGuanjianzi());
		a.setNeirong(activityInfo.getNeirong());
		a.setShijian(activityInfo.getShijian());
		a.setDidian(activityInfo.getDidian());
		a.setJubanfang(activityInfo.getJubanfang());
		a.setFuzeren(activityInfo.getFuzeren());
		a.setLianxifangshi(activityInfo.getLianxifangshi());
		a.setZanzhushang(activityInfo.getZanzhushang());
		a.setZanzhuzijin(Integer.parseInt(activityInfo.getZanzhuzijin()));
		a.setShenpizijin(Integer.parseInt(activityInfo.getShenpizijin()));
		a.setTupian("image/1.jpeg");
		a.setZhuangtai("未审核");
		if(activityManage.activityExists(a))
		{
			return "failed";
		}
		else
		{
			activityManage.addActivity(a);
			return "success";
		}
	}
	
	public String Retrievew()
	{
		activitys = activityManage.listActivitysByWei();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("activitysWeiKey", activitys);
		return "success";
	}
	
	public String Retrievet()
	{
		activitys = activityManage.listActivitysByTon();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("activitysTonKey", activitys);
		return "success";
	}
	
	public String Retrieveb()
	{
		activitys = activityManage.listActivitysByBu();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("activitysBuKey", activitys);
		return "success";
	}
	
	public String ViewTrans()
	{
		activity = activityManage.loadActivityById(activityInfo.getActivityId());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("activitysTransKey", activity);
		return "success";
	}
	
	public String AcTrans()
	{
		activity = activityManage.loadActivityById(activityInfo.getActivityId());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("activitysTranKey", activity);
		comments = commentManage.listComments();
		HttpServletRequest request1 = ServletActionContext.getRequest();
		request1.getSession().setAttribute("commentKey", comments);
		return "success";
	}
	
	public String EndTrans()
	{
		activity = activityManage.loadActivityById(activityInfo.getActivityId());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("activitysEndKey", activity);
		return "success";
	}
	
	public String UnTrans()
	{
		activity = activityManage.loadActivityById(activityInfo.getActivityId());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("activitysUnKey", activity);
		return "success";
	}
	
	public String Update()
	{
		Activity a = new Activity();
		a.setActivityId(activityInfo.getActivityId());
		a.setZhuti(activityInfo.getZhuti());
		a.setGaishu(activityInfo.getGaishu());
		a.setGuanjianzi(activityInfo.getGuanjianzi());
		a.setNeirong(activityInfo.getNeirong());
		a.setShijian(activityInfo.getShijian());
		a.setDidian(activityInfo.getDidian());
		a.setJubanfang(activityInfo.getJubanfang());
		a.setFuzeren(activityInfo.getFuzeren());
		a.setLianxifangshi(activityInfo.getLianxifangshi());
		a.setZanzhushang(activityInfo.getZanzhushang());
		a.setZanzhuzijin(Integer.parseInt(activityInfo.getZanzhuzijin()));
		a.setShenpizijin(Integer.parseInt(activityInfo.getShenpizijin()));
		a.setTupian(activityInfo.getTupian());
		a.setZhuangtai(activityInfo.getZhuangtai());
		activityManage.updateActivity(a);
		return "success";
	}
	
	public String Delete()
	{
		activityManage.deleteActivity(activityInfo.getActivityId());
		return "success";
	}
	
	public Activity getActivity()
	{
		return activity;
	}
	public void setActivity(Activity activity)
	{
		this.activity = activity;
	}
	public List<Activity> getActivitys()
	{
		return activitys;
	}
	public void setActivitys(List<Activity> activitys)
	{
		this.activitys = activitys;
	}
	public ActivityInfo getActivityInfo()
	{
		return activityInfo;
	}
	@Resource
	public void setActivityInfo(ActivityInfo activityInfo)
	{
		this.activityInfo = activityInfo;
	}
	public Object getModel()
	{
		return this.activityInfo;
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

	public CommentManage getCommentManage()
	{
		return commentManage;
	}
	@Resource
	public void setCommentManage(CommentManage commentManage)
	{
		this.commentManage = commentManage;
	}

	public List<Comment> getComments()
	{
		return comments;
	}

	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}
	
}
