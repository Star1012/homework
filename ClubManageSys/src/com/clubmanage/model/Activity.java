package com.clubmanage.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Activity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Activity", schema = "dbo", catalog = "db_ClubActivity")
public class Activity implements java.io.Serializable
{

	// Fields

	private String activityId;
	private String zhuti;
	private String gaishu;
	private String guanjianzi;
	private String neirong;
	private String shijian;
	private String didian;
	private String jubanfang;
	private String fuzeren;
	private String lianxifangshi;
	private String zanzhushang;
	private Integer zanzhuzijin;
	private Integer shenpizijin;
	private String tupian;
	private String zhuangtai;
	private Set<Comment> comments = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public Activity()
	{
	}

	/** minimal constructor */
	public Activity(String activityId, String zhuti, String gaishu,
			String neirong, String shijian, String didian, String jubanfang,
			String fuzeren, String lianxifangshi, String zhuangtai)
	{
		this.activityId = activityId;
		this.zhuti = zhuti;
		this.gaishu = gaishu;
		this.neirong = neirong;
		this.shijian = shijian;
		this.didian = didian;
		this.jubanfang = jubanfang;
		this.fuzeren = fuzeren;
		this.lianxifangshi = lianxifangshi;
		this.zhuangtai = zhuangtai;
	}

	/** full constructor */
	public Activity(String activityId, String zhuti, String gaishu,
			String guanjianzi, String neirong, String shijian, String didian,
			String jubanfang, String fuzeren, String lianxifangshi,
			String zanzhushang, Integer zanzhuzijin, Integer shenpizijin,
			String tupian, String zhuangtai, Set<Comment> comments)
	{
		this.activityId = activityId;
		this.zhuti = zhuti;
		this.gaishu = gaishu;
		this.guanjianzi = guanjianzi;
		this.neirong = neirong;
		this.shijian = shijian;
		this.didian = didian;
		this.jubanfang = jubanfang;
		this.fuzeren = fuzeren;
		this.lianxifangshi = lianxifangshi;
		this.zanzhushang = zanzhushang;
		this.zanzhuzijin = zanzhuzijin;
		this.shenpizijin = shenpizijin;
		this.tupian = tupian;
		this.zhuangtai = zhuangtai;
		this.comments = comments;
	}

	// Property accessors
	@Id
	@Column(name = "activityID", unique = true, nullable = false)
	public String getActivityId()
	{
		return this.activityId;
	}

	public void setActivityId(String activityId)
	{
		this.activityId = activityId;
	}

	@Column(name = "zhuti", nullable = false)
	public String getZhuti()
	{
		return this.zhuti;
	}

	public void setZhuti(String zhuti)
	{
		this.zhuti = zhuti;
	}

	@Column(name = "gaishu", nullable = false)
	public String getGaishu()
	{
		return this.gaishu;
	}

	public void setGaishu(String gaishu)
	{
		this.gaishu = gaishu;
	}

	@Column(name = "guanjianzi")
	public String getGuanjianzi()
	{
		return this.guanjianzi;
	}

	public void setGuanjianzi(String guanjianzi)
	{
		this.guanjianzi = guanjianzi;
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

	@Column(name = "shijian", nullable = false)
	public String getShijian()
	{
		return this.shijian;
	}

	public void setShijian(String shijian)
	{
		this.shijian = shijian;
	}

	@Column(name = "didian", nullable = false)
	public String getDidian()
	{
		return this.didian;
	}

	public void setDidian(String didian)
	{
		this.didian = didian;
	}

	@Column(name = "jubanfang", nullable = false)
	public String getJubanfang()
	{
		return this.jubanfang;
	}

	public void setJubanfang(String jubanfang)
	{
		this.jubanfang = jubanfang;
	}

	@Column(name = "fuzeren", nullable = false)
	public String getFuzeren()
	{
		return this.fuzeren;
	}

	public void setFuzeren(String fuzeren)
	{
		this.fuzeren = fuzeren;
	}

	@Column(name = "lianxifangshi", nullable = false)
	public String getLianxifangshi()
	{
		return this.lianxifangshi;
	}

	public void setLianxifangshi(String lianxifangshi)
	{
		this.lianxifangshi = lianxifangshi;
	}

	@Column(name = "zanzhushang")
	public String getZanzhushang()
	{
		return this.zanzhushang;
	}

	public void setZanzhushang(String zanzhushang)
	{
		this.zanzhushang = zanzhushang;
	}

	@Column(name = "zanzhuzijin")
	public Integer getZanzhuzijin()
	{
		return this.zanzhuzijin;
	}

	public void setZanzhuzijin(Integer zanzhuzijin)
	{
		this.zanzhuzijin = zanzhuzijin;
	}

	@Column(name = "shenpizijin")
	public Integer getShenpizijin()
	{
		return this.shenpizijin;
	}

	public void setShenpizijin(Integer shenpizijin)
	{
		this.shenpizijin = shenpizijin;
	}

	@Column(name = "tupian")
	public String getTupian()
	{
		return this.tupian;
	}

	public void setTupian(String tupian)
	{
		this.tupian = tupian;
	}

	@Column(name = "zhuangtai", nullable = false)
	public String getZhuangtai()
	{
		return this.zhuangtai;
	}

	public void setZhuangtai(String zhuangtai)
	{
		this.zhuangtai = zhuangtai;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activity")
	public Set<Comment> getComments()
	{
		return this.comments;
	}

	public void setComments(Set<Comment> comments)
	{
		this.comments = comments;
	}

}