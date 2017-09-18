package com.clubmanage.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Comment", schema = "dbo", catalog = "db_ClubActivity")
public class Comment implements java.io.Serializable
{

	// Fields

	private CommentId id;
	private Activity activity;
	private String pinglunzhe;
	private String jieshoufang;
	private String plneirong;
	private String shijian;
	private String neirong;
	private String zan;

	// Constructors

	/** default constructor */
	public Comment()
	{
	}

	/** full constructor */
	public Comment(CommentId id, Activity activity, String pinglunzhe,
			String jieshoufang, String plneirong, String shijian,
			String neirong, String zan)
	{
		this.id = id;
		this.activity = activity;
		this.pinglunzhe = pinglunzhe;
		this.jieshoufang = jieshoufang;
		this.plneirong = plneirong;
		this.shijian = shijian;
		this.neirong = neirong;
		this.zan = zan;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "commentId", column = @Column(name = "commentID", nullable = false)),
			@AttributeOverride(name = "activityId", column = @Column(name = "activityID", nullable = false)) })
	public CommentId getId()
	{
		return this.id;
	}

	public void setId(CommentId id)
	{
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activityID", nullable = false, insertable = false, updatable = false)
	public Activity getActivity()
	{
		return this.activity;
	}

	public void setActivity(Activity activity)
	{
		this.activity = activity;
	}

	@Column(name = "pinglunzhe", nullable = false)
	public String getPinglunzhe()
	{
		return this.pinglunzhe;
	}

	public void setPinglunzhe(String pinglunzhe)
	{
		this.pinglunzhe = pinglunzhe;
	}

	@Column(name = "jieshoufang", nullable = false)
	public String getJieshoufang()
	{
		return this.jieshoufang;
	}

	public void setJieshoufang(String jieshoufang)
	{
		this.jieshoufang = jieshoufang;
	}

	@Column(name = "plneirong", nullable = false)
	public String getPlneirong()
	{
		return this.plneirong;
	}

	public void setPlneirong(String plneirong)
	{
		this.plneirong = plneirong;
	}

	@Column(name = "shijian", nullable = false)
	public String getShijian()
	{
		return this.shijian;
	}

	public void setShijian(String shijian)
	{
		this.shijian = shijian;
	}

	@Column(name = "neirong", nullable = false)
	public String getNeirong()
	{
		return this.neirong;
	}

	public void setNeirong(String neirong)
	{
		this.neirong = neirong;
	}

	@Column(name = "zan", nullable = false)
	public String getZan()
	{
		return this.zan;
	}

	public void setZan(String zan)
	{
		this.zan = zan;
	}

}