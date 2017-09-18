package com.clubmanage.vo;

import org.springframework.stereotype.Component;

@Component("commentInfo")
public class CommentInfo
{
	private String activityId;
	private String commentId;
	private String pinglunzhe;
	private String jieshoufang;
	private String plneirong;
	private String shijian;
	private String neirong;
	private String zan;
	
	public String getActivityId()
	{
		return activityId;
	}
	public void setActivityId(String activityId)
	{
		this.activityId = activityId;
	}
	public String getCommentId()
	{
		return commentId;
	}
	public void setCommentId(String commentId)
	{
		this.commentId = commentId;
	}
	public String getPinglunzhe()
	{
		return pinglunzhe;
	}
	public void setPinglunzhe(String pinglunzhe)
	{
		this.pinglunzhe = pinglunzhe;
	}
	public String getJieshoufang()
	{
		return jieshoufang;
	}
	public void setJieshoufang(String jieshoufang)
	{
		this.jieshoufang = jieshoufang;
	}
	public String getShijian()
	{
		return shijian;
	}
	public void setShijian(String shijian)
	{
		this.shijian = shijian;
	}
	public String getNeirong()
	{
		return neirong;
	}
	public void setNeirong(String neirong)
	{
		this.neirong = neirong;
	}
	public String getPlneirong()
	{
		return plneirong;
	}
	public void setPlneirong(String plneirong)
	{
		this.plneirong = plneirong;
	}
	public String getZan()
	{
		return zan;
	}
	public void setZan(String zan)
	{
		this.zan = zan;
	}
	
	
}
