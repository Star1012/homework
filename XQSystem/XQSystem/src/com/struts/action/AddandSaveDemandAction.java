package com.struts.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.dbEntity.DemandCollect;
import com.dbEntity.OperatingOrganization;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.stdcMis.Dao.DBDao;
import com.stdcMis.Dao.OpogDao;
import com.stdcMis.Service.demand.AddAndSaveDemandManager;
import com.stdcMis.Vo.DemandInfo;


public class AddandSaveDemandAction extends ActionSupport implements ModelDriven{
	
	private DemandInfo info = new DemandInfo();
	private DemandCollect deco = new DemandCollect();
	private String oname;
	private HttpServletRequest request;
	private AddAndSaveDemandManager as;
	private String[] key = new String[5];
	private String[] subject = new String[3];
	private String[] industry = new String[3];
	private String message = ERROR;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		//获取填报需求表单的用户
		request = ServletActionContext.getRequest();
		oname = (String) request.getSession().getAttribute("Oname");
		//获取key值
		System.out.println(info.getKey1());
		key[0] = info.getKey1();
		key[1] = info.getKey2();
		key[2] = info.getKey3();
		key[3] = info.getKey4();
		key[4] = info.getKey5();
		//获取学科分类
		subject[0] = info.getFirstSubjects();
		subject[1] = info.getSecondSubjects();
		subject[2] = info.getThirdSubjects();
		//获取技术需求应用行业
		industry[0] = info.getFirstIndustry();
		industry[1] = info.getSecondIndustry();
		industry[2] = info.getThirdIndustry();
		
		/**
		 * 测试输出
		 */
		String id = as.setID();
		System.out.print("'"+id+"',");
		System.out.print("'',");
		System.out.print("'',");
		System.out.print("'',");
		System.out.print(1+",");
		System.out.print(0+",");
		System.out.print("'"+oname+"',");
		System.out.print("'"+info.getDname()+"',");
		System.out.print("'"+info.getStartTime()+"',");
		System.out.print("'"+info.getEndTime()+"',");
		System.out.print("'"+info.getTheme()+"',");
		System.out.print("'"+info.getTechkey()+"',");
		System.out.print("'"+info.getTarget()+"',");
		System.out.print("'"+as.collectMessage(key)+"',");
		System.out.print("'"+info.getInvestment()+"',");
		System.out.print("'"+info.getModel()+"',");
		System.out.print("'"+info.getIntention()+"',");
		System.out.print("'"+info.getType()+"',");
		System.out.print("'"+as.collectMessage(subject)+"',");
		System.out.print("'"+info.getServics()+"',");
		System.out.print("'"+as.collectMessage(industry)+"'");
		
		
		deco.setDemandId(as.setID());
		System.out.println("add or save:"+info.getAdd());
		System.out.println("add or save:"+info.getSave());
		as.initStatus(deco,info.getAdd(),info.getSave());     //将需求表的状态标注为1已提交
		deco.setLock(0);					  			     //需求表加锁状态为未加锁
		deco.setOperatingOrganization(as.getOpogByName(oname));//设置关联关系
		deco.setFormIdea("");
		deco.setDepartIdea("");
		deco.setManageDepart("");
		deco.setDName(info.getDname());		  			    //保存需求表单名称
		deco.setStartTime(info.getStartTime());			    //保存需求开始日期
		deco.setEndTime(info.getEndTime());					//保存需求表结束时间
		deco.setTheme(info.getTheme());					    //保存需求表主要问题
		deco.setTechKey(info.getTechkey());				    //保存需求表技术关键
		deco.setExpectTarget(info.getTarget());			    //保存需求表预期目标
		deco.setDKey(as.collectMessage(key));				//保存需求表关键字
		deco.setPlanInvestment(info.getInvestment());	    //保存需求表拟投入资金
		deco.setDSolution(info.getModel());				    //保存技术需求合作模式
		deco.setCooIntention(info.getIntention());		    //保存合作意向单位
		deco.setTechType(info.getType());				    //保存科技活动类型
		deco.setSubjects(as.collectMessage(subject));		//保存学科分类码
		deco.setDServics(info.getServics());			    //保存技术需求所属领域
		deco.setDAppIndustry(as.collectMessage(industry));	//保存技术需求应用行业
		message = as.execute(deco);
		return message;
	}

	public DemandInfo getInfo() {
		return info;
	}

	public void setInfo(DemandInfo info) {
		this.info = info;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return info;
	}

	public AddAndSaveDemandManager getAs() {
		return as;
	}

	@Resource(name="addAndSaveDemandManager")
	public void setAs(AddAndSaveDemandManager as) {
		this.as = as;
	}
	
}
