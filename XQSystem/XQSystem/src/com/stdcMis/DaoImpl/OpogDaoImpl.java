package com.stdcMis.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import com.dbEntity.OperatingOrganization;
import com.stdcMis.Dao.OpogDao;

@Component("opogDao")
public class OpogDaoImpl extends SuperDao implements OpogDao {

	private static final Logger log = LoggerFactory
			.getLogger(OpogDaoImpl.class);
	
	@Override
	public boolean checkUserExistsWithName(OperatingOrganization opog) {
		// TODO Auto-generated method stub
		List<OperatingOrganization> list = getHibernateTemplate().find("from OperatingOrganization opog where opog.OName='"+opog.getOName()+"'");
		if(list.isEmpty())
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean checkUserAndPwd(OperatingOrganization opog) {
		// TODO Auto-generated method stub
		List<OperatingOrganization> list = getHibernateTemplate().find("from OperatingOrganization opog where opog.OName='"+opog.getOName()+"' and opog.pwd='"+opog.getPwd()+"'");
		System.out.print("from OperatingOrganization opog where opog.OName='"+opog.getOName()+"' and opog.pwd='"+opog.getPwd()+"'");
		if(list.isEmpty())
		{
			return false;
		}
		return true;
	}

	
	@Override
	public OperatingOrganization get(String Oname) {
		// TODO Auto-generated method stub
		System.out.print(Oname);
		return (OperatingOrganization) getHibernateTemplate().get(OperatingOrganization.class, "石家庄铁道大学");
	}

	@Override
	public boolean save(OperatingOrganization Opog) {
		// TODO Auto-generated method stub
		log.debug("saving OperatingOrganization instance");
		System.out.println(getHibernateTemplate().save(Opog));
		return true;
	}

	@Override
	public List<OperatingOrganization> searchByPropertys(String model,String[] propertyName,
			Object[] value, boolean rigor) 
	{
		List<OperatingOrganization> result = null;
		StringBuffer sqlBuffer = new StringBuffer();
		String ralation=" like ";
		if(rigor){
			ralation=" = ";
		}
		sqlBuffer.append("from "+ model+" as model\n");
		int len=propertyName.length;
		List list=new ArrayList();
		boolean first=true;
		for(int i=0;i<len;i++)
		{
			if(value[i]!=null)
			{
				if(first)
				{    
					sqlBuffer.append(" where model."+ propertyName[i] + ralation+" ?\n");    
					list.add(value[i]);
					first=false;
				}
				else
				{    
					sqlBuffer.append(" and model."+ propertyName[i] +ralation+ " ?\n");    
					list.add(value[i]);
				}
		    }
		 }
		System.out.print(sqlBuffer.toString());
		log.debug("searching by propertys");
		 try { 
			 result = getHibernateTemplate().find(sqlBuffer.toString(),value);
			 log.debug("search by propertys success,result size:"+result.size());
		 }catch(RuntimeException re)
		 {
			 log.error("find by example failed", re);
			 throw re;
		 }
		
		return result;
	}



	@Override
	public String getRole(String Oname) {
		// TODO Auto-generated method stub
		List<OperatingOrganization> list = getHibernateTemplate().find("from OperatingOrganization opog where opog.OName = '"+Oname+"'");
		return list.get(0).getRole();
	}

	
}
