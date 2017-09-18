package com.stdcMis.Service.demandImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dbEntity.DemandCollect;
import com.stdcMis.Dao.DBDao;
import com.stdcMis.Service.demand.DemandAuditManager;

@Component("demandAuditManager")
public class DemandAuditManagerImpl implements DemandAuditManager{

	DBDao dd;
	private DemandCollect dc = new DemandCollect();
	
	@Override
	public String execute(String status, String form, String idea,
			String department, String depart, String id) {
		// TODO Auto-generated method stub
		dc = (DemandCollect)dd.get("com.dbEntity.DemandCollect",id);
		System.out.println(status);
		if(status.equals("form"))
		{
			System.out.println("aaaaaaaaaaaaaaa");
			if(form.equals("审核通过"))
			{
				dc.setDStatus(3);
				dc.setManageDepart(department);
			}
			else if(form.equals("审核不通过"))
			{
				dc.setDStatus(2);
				dc.setFormIdea(idea);
			}
			
		}
		else if(status.equals("depart"))
		{
			if(depart.equals("审核通过"))
			{
				dc.setDStatus(5);
			}
			else if(depart.equals("审核不通过"))
			{
				dc.setDStatus(4);
				dc.setDepartIdea(idea);
			}
		}
		if(dd.update(dc))
		{
			return "success";
		}
		return "error";
	}

	public DBDao getDd() {
		return dd;
	}

	@Resource(name="demcDao")
	public void setDd(DBDao dd) {
		this.dd = dd;
	}
	
	
}
