package com.stdcMis.Service.demandImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.dbEntity.NationalEconomy;
import com.dbEntity.Subjects;
import com.stdcMis.Dao.NaeDao;
import com.stdcMis.Dao.SubDao;
import com.stdcMis.Service.demand.GetSubandNaeManager;

@Component("getSubandNaeManager")
public class GetSubandNaeManagerImpl implements GetSubandNaeManager {

	private HttpServletRequest request;
	private SubDao subDao;
	private NaeDao naeDao;
	
	@Override
	public List getFirstSubject() {
		// TODO Auto-generated method stub
		return subDao.searchSub("__00000");
	}

	@Override
	public List getFirstIndustry() {
		// TODO Auto-generated method stub
		return naeDao.searchNae("_0000");
	}
	
	public String execute()
	{
		request = ServletActionContext.getRequest();
		request.getSession().setAttribute("firstSubject",this.getFirstSubject());
		request.getSession().setAttribute("firstNationalEconomy",this.getFirstIndustry());
		return "success";
	}

	public SubDao getSubDao() {
		return subDao;
	}

	@Resource
	public void setSubDao(SubDao subDao) {
		this.subDao = subDao;
	}

	public NaeDao getNaeDao() {
		return naeDao;
	}

	@Resource
	public void setNaeDao(NaeDao naeDao) {
		this.naeDao = naeDao;
	}

}
