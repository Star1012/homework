package com.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import com.dbEntity.SearchResult;
import com.opensymphony.xwork2.ActionSupport;

import com.stdcMis.Service.demand.ShowDemandManager;


public class Comprehensiveindex extends ActionSupport implements RequestAware{

	HttpServletRequest request=ServletActionContext.getRequest();
	private Map<String,Object> map = new HashMap();
	private ShowDemandManager show;
	
	//上一次查询的sql语句
	private static String sql1=null;
	private String submit;
	//机构信息
	private String firstindex01;//索引
	private String firstvalue01;//第一个文本框
	private String firsttype01;//与或非
	private String firstvalue02;//第二个文本框
	private String typefirst01;//精确模糊
	
	//需求表信息
	private String firstindex11;//索引
	private String firstvalue11;//第一个文本框
	private String firsttype11;//与或非
	private String firstvalue12;//第二个文本框
	private String typefirst11;//精确模糊
	
	//年限
	private String begintime1;
	private String begintime2;
	private String finaltime1;
	private String finaltime2;
	
	public String getSubmit() {
		return submit;
	}


	public void setSubmit(String submit) {
		this.submit = submit;
	}


	public String getFirstindex01() {
		return firstindex01;
	}


	public void setFirstindex01(String firstindex01) {
		this.firstindex01 = firstindex01;
	}

	
	public String getFirstvalue01() {
		return firstvalue01;
	}


	public void setFirstvalue01(String firstvalue01) {
		this.firstvalue01 = firstvalue01;
	}


	public String getFirsttype01() {
		return firsttype01;
	}


	public void setFirsttype01(String firsttype01) {
		this.firsttype01 = firsttype01;
	}


	public String getFirstvalue02() {
		return firstvalue02;
	}


	public void setFirstvalue02(String firstvalue02) {
		this.firstvalue02 = firstvalue02;
	}


	public String getTypefirst01() {
		return typefirst01;
	}


	public void setTypefirst01(String typefirst01) {
		this.typefirst01 = typefirst01;
	}


	public String getFirstindex11() {
		return firstindex11;
	}


	public void setFirstindex11(String firstindex11) {
		this.firstindex11 = firstindex11;
	}


	public String getFirstvalue11() {
		return firstvalue11;
	}


	public void setFirstvalue11(String firstvalue11) {
		this.firstvalue11 = firstvalue11;
	}


	public String getFirsttype11() {
		return firsttype11;
	}


	public void setFirsttype11(String firsttype11) {
		this.firsttype11 = firsttype11;
	}


	public String getFirstvalue12() {
		return firstvalue12;
	}


	public void setFirstvalue12(String firstvalue12) {
		this.firstvalue12 = firstvalue12;
	}


	public String getTypefirst11() {
		return typefirst11;
	}


	public void setTypefirst11(String typefirst11) {
		this.typefirst11 = typefirst11;
	}

	public String getBegintime1() {
		return begintime1;
	}


	public void setBegintime1(String begintime1) {
		this.begintime1 = begintime1;
	}


	public String getBegintime2() {
		return begintime2;
	}


	public void setBegintime2(String begintime2) {
		this.begintime2 = begintime2;
	}


	public String getFinaltime1() {
		return finaltime1;
	}


	public void setFinaltime1(String finaltime1) {
		this.finaltime1 = finaltime1;
	}


	public String getFinaltime2() {
		return finaltime2;
	}


	public void setFinaltime2(String finaltime2) {
		this.finaltime2 = finaltime2;
	}

	public String execute() throws Exception
	{
		System.out.println(this.getSubmit());
		if(this.getSubmit().equals("检索"))
		{
			try 
			{
				System.out.println(getSql());
				List<SearchResult>taList=show.query(this.getSql());
				System.out.println(taList);
				map.put("demandList",taList);
				this.setRequest(map);
				return "success";
			} 
			catch (Exception e) 
			{
				System.out.println(e);
				return "error";
			}
		}
		else {
				try 
				{
					request.getSession().removeAttribute("testInfo");
					System.out.println(getSql0());
					List<SearchResult>taList=show.query(this.getSql0());
					System.out.println(taList);
					map.put("demandList",taList);
					this.setRequest(map);
					request.getSession().setAttribute("testInfo", taList);
					return "success";
				} 
				catch (Exception e) 
				{
					System.out.println(e);
					return "error";
				}
		}
		
		
	}
	
	public String getMiddle()
	{
		String sql="";
		if(!this.getFirstvalue01().equals(""))
		{
			if(!this.getFirstvalue02().equals(""))
			{
				if(this.getTypefirst01().equals("equal"))//机构信息
				{
					if(this.getFirsttype01().equals("not"))
					{
						sql=sql+" and (u."+this.getFirstindex01()+"='"+this.getFirstvalue01()+"' and u."+this.getFirstindex01()+"!='"+this.getFirstvalue02()+"')";
					}
					else 
					{
						sql=sql+" and (u."+this.getFirstindex01()+"='"+this.getFirstvalue01()+"' "+this.getFirsttype01()+" u."+this.getFirstindex01()+"='"+this.getFirstvalue02()+"')";
					}
				}
				else 
				{
					if(this.getFirsttype01().equals("not"))
					{
						sql=sql+" and (u."+this.getFirstindex01()+" like '%"+this.getFirstvalue01()+"%' and u."+this.getFirstindex01()+" not like '%"+this.getFirstvalue02()+"%')";
					}
					else 
					{
						sql=sql+" and (u."+this.getFirstindex01()+" like '%"+this.getFirstvalue01()+"%' "+this.getFirsttype01()+" u."+this.getFirstindex01()+" like '%"+this.getFirstvalue02()+"%')";
					}
				}
			}
			//第二个文本框为空
			else
			{
				if(this.getTypefirst01().equals("equal"))
				{
					sql=sql+" and u."+this.getFirstindex01()+" = '"+this.getFirstvalue01()+"'";
				}
				else 
				{
					sql=sql+" and u."+this.getFirstindex01()+" like '%"+this.getFirstvalue01()+"%'";
				}
			}
		}
		//第一个文本框为空，第二个文本框不为空
		else 
		{
			if(!this.getFirstvalue02().equals(""))
			{
				if(this.typefirst01.equals("equal"))
				{
					if(this.getFirsttype01().equals("not"))
					{
						sql=sql+" and u."+this.getFirstindex01()+" != '"+this.getFirstvalue02()+"'";
					}
					else 
					{
						sql=sql+" "+this.getFirsttype01()+" u."+this.getFirstindex01()+"='"+this.getFirstvalue02()+"'";
					}
				}
				else 
				{
					if(this.getFirsttype01().equals("not"))
					{
						sql=sql+" and u."+this.getFirstindex01()+" not like '%"+this.getFirstvalue02()+"%'";
					}
					else 
					{
						sql=sql+" "+this.getFirsttype01()+" u."+this.getFirstindex01()+" like '%"+this.getFirstvalue02()+"%'";
					}
				}
			}
		}
		
		//机构信息
		String []a1=request.getParameterValues("selecttype01");
		String []a2=request.getParameterValues("selectindex01");
		String []a3=request.getParameterValues("selectvalue01");
		String []a4=request.getParameterValues("selecttype02");
		String []a5=request.getParameterValues("selectvalue02");
		String []a6=request.getParameterValues("type01");
		//开始处理机构信息下拉
		if(a1!=null&&a1.length!=0)
		{
			for(int i=0;i<a1.length;i++)
			{
				if(!a3[i].equals(""))
				{
					if(!a5[i].equals(""))
					{
						if(a6[i].equals("like"))
						{
						   if(a1[i].equals("not"))
						   {
								if(a4[i].equals("not"))
								{
									sql=sql+" and (u."+a2[i]+" not like '%"+a3[i]+"%' or u."+a2[i]+" like '%"+a5[i]+"%')";
								}
								else if(a4[i].equals("or"))
								{
									sql=sql+" and (u."+a2[i]+" not like '%"+a3[i]+"%' and u."+a2[i]+" not like '%"+a5[i]+"%')";
								}
								else 
								{
									sql=sql+" and (u."+a2[i]+" not like '%"+a3[i]+"%' or u."+a2[i]+" not like '%"+a5[i]+"%')";
								}
							}
							else 
							{
								if(a4[i].equals("not"))
								{
									sql=sql+" "+a1[i]+" (u."+a2[i]+" like '%"+a3[i]+"%' and u."+a2[i]+" not like '%"+a5[i]+"%')";
								}
								else 
								{
									sql=sql+" "+a1[i]+" (u."+a2[i]+" like '%"+a3[i]+"%' "+a4[i]+" u."+a2[i]+" like '%"+a5[i]+"%')";
								}
							}
						}
						else 
						{
							if(a1[i].equals("not"))
							{
								if(a4[i].equals("not"))
								{
									sql=sql+" and (u."+a2[i]+"!='"+a3[i]+"' or u."+a2[i]+"='"+a5[i]+"')";
								}
								else if(a4[i].equals("or"))
								{
									sql=sql+" and (u."+a2[i]+"!='"+a3[i]+"' and u."+a2[i]+"!='"+a5[i]+"')";
								}
								else 
								{
									sql=sql+" and (u."+a2[i]+"!='"+a3[i]+"' or u."+a2[i]+"!='"+a5[i]+"')";
								}
							}
							else 
							{
								if(a4[i].equals("not"))
								{
									sql=sql+" "+a1[i]+" (u."+a2[i]+"='"+a3[i]+"' and u."+a2[i]+"!='"+a5[i]+"')";
								}
								else 
								{
									sql=sql+" "+a1[i]+" (u."+a2[i]+"='"+a3[i]+"' "+a4[i]+" u."+a2[i]+"='"+a5[i]+"')";
								}
							}
						}
					}
					else 
					{
						if(a1[i].equals("not"))
						{
							if(a6[i].equals("equal"))
							{
								sql=sql+" and u."+a2[i]+" != '"+a3[i]+"'";
							}
							else 
							{
								sql=sql+" and u."+a2[i]+" not like '%"+a3[i]+"%'";
							}
						}
						else 
						{
							if(a6[i].equals("equal"))
							{
								sql=sql+" and u."+a2[i]+" = '"+a3[i]+"'";
							}
							else 
							{
								sql=sql+" and u."+a2[i]+" like '%"+a3[i]+"%'";
							}
						}
					}
				}
				else 
				{
					if(!a5[i].equals(""))
					{
						if(a1[i].equals("not"))
						{
							if(a6[i].equals("equal"))
							{
								if(a4[i].equals("not"))
								{
									sql=sql+" and u."+a2[i]+" = '"+a5[i]+"'";
								}
								else 
								{
									sql=sql+" "+a4[i]+" u."+a2[i]+" != '"+a5[i]+"' ";
								}
							}
							else 
							{
								if(a4[i].equals("not"))
								{
									sql=sql+" and u."+a2[i]+" like '%"+a5[i]+"%'";
								}
								else 
								{
									sql=sql+" "+a4[i]+" u."+a2[i]+" not like '%"+a5[i]+"%' ";
								}
							}
						}
						else 
						{
							if(a6[i].equals("equal"))
							{
								if(a4[i].equals("not"))
								{
									sql=sql+" and u."+a2[i]+" != '"+a5[i]+"'";
								}
								else 
								{
									sql=sql+" "+a4[i]+" u."+a2[i]+" = '"+a5[i]+"' ";
								}
							}
							else 
							{
								if(a4[i].equals("not"))
								{
									sql=sql+" and u."+a2[i]+" not like '%"+a5[i]+"%'";
								}
								else 
								{
									sql=sql+" "+a4[i]+" u."+a2[i]+" like '%"+a5[i]+"%' ";
								}
							}
						}
					}
				}
			}
		}	
		//结束下拉处理
		
		//处理第一行
		if(!this.getFirstvalue11().equals(""))//判断第一个文本框是否为空
		{
			if(!this.getFirstvalue12().equals(""))//判断第二个文本框是否为空
			{
				if(this.getTypefirst11().equals("equal"))//机构信息
				{
					if(this.getFirsttype11().equals("not"))
					{
						sql=sql+" and (t."+this.getFirstindex11()+"='"+this.getFirstvalue11()+"' and t."+this.getFirstindex11()+"!='"+this.getFirstvalue12()+"')";
					}
					else 
					{
						sql=sql+" and (t."+this.getFirstindex11()+"='"+this.getFirstvalue11()+"' "+this.getFirsttype11()+" t."+this.getFirstindex11()+"='"+this.getFirstvalue12()+"')";
					}
				}
				else 
				{
					if(this.getFirsttype11().equals("not"))
					{
						sql=sql+" and (t."+this.getFirstindex11()+" like '%"+this.getFirstvalue11()+"%' and t."+this.getFirstindex11()+" not like '%"+this.getFirstvalue12()+"%')";
					}
					else 
					{
						sql=sql+" and (t."+this.getFirstindex11()+" like '%"+this.getFirstvalue11()+"%' "+this.getFirsttype11()+" t."+this.getFirstindex11()+" like '%"+this.getFirstvalue12()+"%')";
					}
				}
			}
			//第二个文本框为空，第一个不为空
			else
			{
				if(this.getTypefirst11().equals("equal"))
				{
					sql=sql+" and t."+this.getFirstindex11()+" = '"+this.getFirstvalue11()+"'";
				}
				else 
				{
					sql=sql+" and t."+this.getFirstindex11()+" like '%"+this.getFirstvalue11()+"%'";
				}
			}
		}
		//第一个文本框为空，第二个文本框不为空
		else 
		{
			if(!this.getFirstvalue12().equals(""))
			{
				if(this.typefirst01.equals("equal"))
				{
					if(this.getFirsttype11().equals("not"))
					{
						sql=sql+" and t."+this.getFirstindex11()+" != '"+this.getFirstvalue12()+"'";
					}
					else 
					{
						sql=sql+" "+this.getFirsttype11()+" t."+this.getFirstindex11()+"='"+this.getFirstvalue12()+"'";
					}
				}
				else 
				{
					if(this.getFirsttype11().equals("not"))
					{
						sql=sql+" and t."+this.getFirstindex11()+" not like '%"+this.getFirstvalue12()+"%'";
					}
					else 
					{
						sql=sql+" "+this.getFirsttype11()+" t."+this.getFirstindex11()+" like '%"+this.getFirstvalue12()+"%'";
					}
				}
			}
		}
			
		
		//征集表
		String []b1=request.getParameterValues("selecttype11");
		String []b2=request.getParameterValues("selectindex11");
		String []b3=request.getParameterValues("selectvalue11");
		String []b4=request.getParameterValues("selecttype12");
		String []b5=request.getParameterValues("selectvalue12");
		String []b6=request.getParameterValues("type11");
		if(b1!=null&&b1.length!=0)
		{
			for(int i=0;i<b1.length;i++)
			{
				if(!b3[i].equals(""))
				{
					if(!b5[i].equals(""))
					{
						if(b6[i].equals("like"))
						{
							if(b1[i].equals("not"))
							{
								if(b4[i].equals("not"))
								{
									sql=sql+" and (t."+b2[i]+" not like '%"+b3[i]+"%' or t."+b2[i]+" like '%"+b5[i]+"%')";
								}
								else if(b4[i].equals("or"))
								{
									sql=sql+" and (t."+b2[i]+" not like '%"+b3[i]+"%' and t."+b2[i]+" not like '%"+b5[i]+"%')";
								}
								else 
								{
									sql=sql+" and (t."+b2[i]+" not like '%"+b3[i]+"%' or t."+b2[i]+" not like '%"+b5[i]+"%')";
								}
							}
							else 
							{
								if(b4[i].equals("not"))
								{
									sql=sql+" "+b1[i]+" (t."+b2[i]+" like '%"+b3[i]+"%' and t."+b2[i]+" not like '%"+b5[i]+"%')";
								}
								else 
								{
									sql=sql+" "+b1[i]+" (t."+b2[i]+" like '%"+b3[i]+"%' "+b4[i]+" t."+b2[i]+" like '%"+b5[i]+"%')";
								}
							}
						}
						else 
						{
							if(b1[i].equals("not"))
							{
								if(b4[i].equals("not"))
								{
									sql=sql+" and (t."+b2[i]+"!='"+b3[i]+"' or t."+b2[i]+"='"+b5[i]+"')";
								}
								else if(b4[i].equals("or"))
								{
									sql=sql+" and (t."+b2[i]+"!='"+b3[i]+"' and t."+b2[i]+"!='"+b5[i]+"')";
								}
								else 
								{
									sql=sql+" and (t."+b2[i]+"!='"+b3[i]+"' or t."+b2[i]+"!='"+b5[i]+"')";
								}
							}
							else 
							{
								if(b4[i].equals("not"))
								{
									sql=sql+" "+b1[i]+" (t."+b2[i]+"='"+b3[i]+"' and t."+b2[i]+"!='"+b5[i]+"')";
								}
								else 
								{
									sql=sql+" "+b1[i]+" (t."+b2[i]+"='"+b3[i]+"' "+b4[i]+" t."+b2[i]+"='"+b5[i]+"')";
								}
							}
						}
					}
					
					else 
					{
						if(b1[i].equals("not"))
						{
							if(b6[i].equals("equal"))
							{
								sql=sql+" and t."+b2[i]+" != '"+b3[i]+"'";
							}
							else 
							{
								sql=sql+" and t."+b2[i]+" not like '%"+b3[i]+"%'";
							}
						}
						else 
						{
							if(b6[i].equals("equal"))
							{
								sql=sql+" "+b1[i]+" t."+b2[i]+"='"+b3[i]+"'";
							}
							else 
							{
								sql=sql+" "+b1[i]+" t."+b2[i]+" like '%"+b3[i]+"%'";
							}
						}
					}
				}
				else 
				{
					if(!b5[i].equals(""))
					{
						if(b1[i].equals("not"))
						{
							if(b6[i].equals("equal"))
							{
								if(b4[i].equals("not"))
								{
									sql=sql+" and t."+b2[i]+"='"+b5[i]+"'";
								}
								else 
								{
									sql=sql+" "+b4[i]+" t."+b2[i]+"!='"+b5[i]+"' ";
								}
							}
							else 
							{
								if(b4[i].equals("not"))
								{
									sql=sql+" and t."+b2[i]+" like '%"+b5[i]+"%'";
								}
								else 
								{
									sql=sql+" "+b4[i]+" t."+b2[i]+" not like '%"+b5[i]+"%' ";
								}
							}
						}
						else 
						{
							if(b6[i].equals("equal"))
							{
								if(b4[i].equals("not"))
								{
									sql=sql+" and t."+b2[i]+"!='"+b5[i]+"'";
								}
								else 
								{
									sql=sql+" "+b4[i]+" t."+b2[i]+"='"+b5[i]+"' ";
								}
							}
							else 
							{
								if(b4[i].equals("not"))
								{
									sql=sql+" and t."+b2[i]+" not like '%"+b5[i]+"%'";
								}
								else 
								{
									sql=sql+" "+b4[i]+" t."+b2[i]+" like '%"+b5[i]+"%' ";
								}
							}
						}
					}				
				}
			}
		}
		return sql;
	}
	public String getSql()
	{
		//年份处理
		if(this.getBegintime1().equals(""))
		{
			this.setBegintime1("0000");
		}
		if(this.getBegintime2().equals(""))
		{
			this.setBegintime2("9999");
		}
		if(this.getFinaltime1().equals(""))
		{
			this.setFinaltime1("0000");
		}
		if(this.getFinaltime2().equals(""))
		{
			
			this.setFinaltime2("9999");
		}
		String head="select new com.dbEntity.SearchResult(t.demandId,u.OName,t.DName,t.startTime,t.endTime,t.DStatus) from OperatingOrganization u ,DemandCollect t where";
		String sql=" (t.startTime " +
				"between '"+this.getBegintime1()+"' and '"+this.getBegintime2()+"' and t.endTime " +
				"between '"+this.getFinaltime1()+"' and '"+this.getFinaltime2()+"'"+this.getMiddle()+")";
		Comprehensiveindex.sql1=sql;
		sql=head+sql+" and u.OName=t.operatingOrganization";
		return sql;
	}
	
	public String getSql0()
	{
		String head="select new com.dbEntity.SearchResult(t.demandId,u.OName,t.DName,t.startTime,t.endTime,t.DStatus) from OperatingOrganization u ,DemandCollect t where";
		String sql="";
		if(sql1==null)
		{
			sql=this.getSql();
		}
		else
		{
			sql="("+sql1+" "+this.getMiddle()+")";
			Comprehensiveindex.sql1=sql;
			sql=head+sql+" and u.OName=t.operatingOrganization";
		}
		return sql;
		
	}


	public ShowDemandManager getShow() {
		return show;
	}


	@Resource
	public void setShow(ShowDemandManager show) {
		this.show = show;
	}


	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.map = arg0;
	}
}
