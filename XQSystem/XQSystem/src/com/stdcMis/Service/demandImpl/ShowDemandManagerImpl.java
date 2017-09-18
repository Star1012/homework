package com.stdcMis.Service.demandImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.dbEntity.DemandCollect;
import com.dbEntity.OperatingOrganization;
import com.dbEntity.SearchResult;
import com.stdcMis.Dao.DBDao;
import com.stdcMis.Dao.OpogDao;
import com.stdcMis.Service.demand.ShowDemandManager;

@Component
public class ShowDemandManagerImpl implements ShowDemandManager {

	private DBDao demcDao;
	private OpogDao opogDao;
	private String[] condition = new String[18];
	private String[] value = new String[18];
	private Map<String,Boolean> rigor = new HashMap();
	private String[] symbol = new String[18];
	
	@Override
	public List<DemandCollect> listAllDemand(String name) {
		// TODO Auto-generated method stub
		String propertyName[] = {"operatingOrganization"};
		String value[] = {name};
		return demcDao.searchByPropertys("DemandCollect", propertyName, value, true);
	}

	@Override
	public List<DemandCollect> listUpdateList(String name) {
		// TODO Auto-generated method stub
		return demcDao.searchUpdateList(name);
	}
	
	@Override
	public List<DemandCollect> listSubmitDemand() {
		// TODO Auto-generated method stub
		String propertyName[] = {"DStatus"};
		String value[] = {"1"};
		return demcDao.searchByPropertys("DemandCollect", propertyName, value, true);
	}
	

	@Override
	public List<DemandCollect> listFormedDemand() {
		// TODO Auto-generated method stub
		String propertyName[] ={"DStatus"};
		String value[] = {"2"};
		return demcDao.searchByPropertys("DemandCollect", propertyName, value, true);
	}

	
	@Override
	public DemandCollect getDemandCollect(String id) {
		// TODO Auto-generated method stub
		DemandCollect dc = (DemandCollect)demcDao.get("com.dbEntity.DemandCollect",id);
		return dc;
	}

	@Override
	public void initParameter(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int i=0;
		if(map.get("Status").equals("default"))
		{
			condition[i] = "d.DStatus";
			value[i] = "0";
			rigor.put(condition[i],true);
			symbol[i] = ">";
			i++;
		}
		
		else if(map.get("Status").equals("3"))
		{
			condition[i] = "d.DStatus";
			value[i] = (String)map.get("Status");
			rigor.put(condition[i],true);
			symbol[i] = ">=";
			i++;
		}
		else
		{
			condition[i] = "d.DStatus";
			value[i] = (String)map.get("Status");
			rigor.put(condition[i],true);
			symbol[i] = "=";
			i++;
		}
		
		
		if(!map.get("Oname").equals("") && map.get("Oname")!=null)
		{
			condition[i] = "o.OName";
			value[i] = (String) map.get("Oname");
			if(map.get("OnSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Tmd").equals("") && map.get("Tmd")!=null)
		{
			condition[i] = "o.OTmd";
			value[i] = (String) map.get("Tmd");
			if(map.get("TmSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Area").equals("") && map.get("Area")!=null)
		{
			condition[i] = "o.OArea";
			value[i] = (String) map.get("Area");
			if(map.get("ArSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Lperson").equals("") && map.get("Lperson")!=null)
		{
			condition[i] = "o.OLperson";
			value[i] = (String) map.get("Lperson");
			if(map.get("LpSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Contacts").equals("") && map.get("Contacts")!=null)
		{
			condition[i] = "o.OContacts";
			value[i] = (String) map.get("Contacts");
			if(map.get("CoSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Attribute").equals("") && map.get("Attribute")!=null)
		{
			condition[i] = "o.OAttribute";
			value[i] = (String) map.get("Attribute");
			if(map.get("AtSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Dname").equals("") && map.get("Dname")!=null)
		{
			condition[i] = "d.DName";
			value[i] = (String) map.get("Dname");
			if(map.get("DnSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Key").equals("") && map.get("Key")!=null)
		{
			condition[i] = "d.DKey";
			value[i] = (String) map.get("Key");
			if(map.get("KeSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Solution").equals("") && map.get("Solution")!=null)
		{
			condition[i] = "d.DSolution";
			value[i] = (String) map.get("Solution");
			if(map.get("SoSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Type").equals("") && map.get("Type")!=null)
		{
			condition[i] = "d.techType";
			value[i] = (String) map.get("Type");
			if(map.get("TySearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Subject").equals("") && map.get("Subject")!=null)
		{
			condition[i] = "d.subjects";
			value[i] = (String) map.get("Subject");
			if(map.get("SuSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Services").equals("") && map.get("Services")!=null)
		{
			condition[i] = "d.DServics";
			value[i] = (String) map.get("Services");
			if(map.get("SeSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("Industry").equals("") && map.get("Industry")!=null)
		{
			condition[i] = "d.DAppIndustry";
			value[i] = (String) map.get("Industry");
			if(map.get("IndSearchType").equals("accuracy"))
			{
				rigor.put(condition[i],true);
			}
			else
			{
				rigor.put(condition[i],false);
			}
			symbol[i] = "=";
			i++;
		}
		
		if(!map.get("SstartTime").equals("") && map.get("SstartTime")!=null)
		{
			condition[i] = "d.startTime";
			value[i] = (String) map.get("SstartTime");
			rigor.put(condition[i],true);
			symbol[i] = ">=";
			i++;
		}
		
		if(!map.get("EstartTime").equals("") && map.get("EstartTime")!=null)
		{
			condition[i] = "d.etartTime";
			value[i] = (String) map.get("EstartTime");
			rigor.put(condition[i],true);
			symbol[i] = "<=";
			i++;
		}
		
		if(!map.get("SendTime").equals("") && map.get("SendTime")!=null)
		{
			condition[i] = "d.endTime";
			value[i] = (String) map.get("SendTime");
			rigor.put(condition[i],true);
			symbol[i] = ">=";
			i++;
		}
		
		if(!map.get("EendTime").equals("") && map.get("EendTime")!=null)
		{
			condition[i] = "d.endTime";
			value[i] = (String) map.get("EendTime");
			rigor.put(condition[i],true);
			symbol[i] = "<=";
			i++;
		}
	}
	
	@Override
	public List<SearchResult> listDemandByCondition() {
		// TODO Auto-generated method stub
		return demcDao.searchByConditions(condition, value, rigor, symbol);
	}

	
	@Override
	public List<DemandCollect> execute(String type,String name) {
		// TODO Auto-generated method stub
		List<DemandCollect> list = null;
		if(type.equals("0"))
		{
			list = this.listAllDemand(name);
		}
		else if(type.equals("1"))
		{
			list = this.listUpdateList(name);
		}
		else if(type.equals("2"))
		{
			list = this.listSubmitDemand();
		}
		else if(type.equals("3"))
		{
			list = this.listFormedDemand();
		}
		return list;
	}
	
	public List<SearchResult> query(String sql)
	{
		List<SearchResult> list = null;
		list = demcDao.query(sql);
		return list;
	}
	
	public DBDao getDemcDao() {
		return demcDao;
	}

	@Resource
	public void setDemcDao(DBDao demcDao) {
		this.demcDao = demcDao;
	}


	public OpogDao getOpogDao() {
		return opogDao;
	}

	@Resource
	public void setOpogDao(OpogDao opogDao) {
		this.opogDao = opogDao;
	}


}
