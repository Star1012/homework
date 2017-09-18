package com.stdcMis.DaoImpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.dbEntity.DemandCollect;
import com.dbEntity.OperatingOrganization;
import com.dbEntity.SearchResult;
import com.stdcMis.Dao.DBDao;

@Component("demcDao")
public class DBDaoImpl extends SuperDao implements DBDao {

	private static final Logger log = LoggerFactory
			.getLogger(DBDaoImpl.class);
	
	@Override
	public Object get(String model,Object key) {
		// TODO Auto-generated method stub
		return  getHibernateTemplate().get(model, (Serializable) key);
	}

	@Override
	public boolean save(Object object) {
		// TODO Auto-generated method stub
		if(getHibernateTemplate().save(object)==null)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public boolean delete(Object object) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(object);
		return true;		
	}
	
	@Override
	public String setID() {
		// TODO Auto-generated method stub
		String id = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String Time = df.format(new Date());
		//查询当前最后一条数据的id
		String queryString = "from DemandCollect order by demandId desc";
		List<DemandCollect> list = getHibernateTemplate().find(queryString);
		if(list.isEmpty())
		{
			id = Time+"0001";
		}
		else
		{
			DemandCollect dc = (DemandCollect)list.get(0);
			String lastId = dc.getDemandId();
			System.out.print("我的id啊-------："+lastId);
			if(lastId.substring(0, 8).equals(Time))
			{
				int temp=0;
			    temp= Integer.parseInt(lastId.substring(lastId.length()-4));
			    temp+=1;
			    String t = temp+"";
			    lastId=Time;
				for(int i=0;i<4-t.length();i++)
				{
					lastId+="0";
				}
				lastId+=t;
				id = lastId;
			}
			else
			{
				id = Time+"0001";
			}
		}
		return id;
	}

	@Override
	public boolean save(DemandCollect Demc) {
		// TODO Auto-generated method stub
		log.debug("saving DemandCollect instance");
		try {
			getHibernateTemplate().save(Demc);
			log.debug("save successful");
			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public boolean update(DemandCollect Demc) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(Demc);
		return true;
	}
	
	@Override
	public List<DemandCollect> searchByPropertys(String model,
			String[] propertyName, Object[] value, boolean rigor) {
		List<DemandCollect> result = null;
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
					sqlBuffer.append(" where model."+ propertyName[i] + ralation+"'"+value[i]+"'");
					first=false;
				}
				else
				{    
					sqlBuffer.append(" and model."+ propertyName[i] +ralation+ " '"+value[i]+"'");    
					list.add(value[i]);
				}
		    }
		 }
		System.out.print(sqlBuffer.toString());
		log.debug("searching by propertys");
		 try { 
			 result = getHibernateTemplate().find(sqlBuffer.toString());
			 log.debug("search by propertys success,result size:"+result.size());
		 }catch(RuntimeException re)
		 {
			 log.error("find by example failed", re);
			 throw re;
		 }
		
		return result;
	}



	@Override
	public List<DemandCollect> searchUpdateList(String name) {
		// TODO Auto-generated method stub
		String queryString = "from DemandCollect dc where dc.operatingOrganization='"+name+"'and dc.lock=0 and dc.DStatus>=0 and dc.DStatus<=1";
		System.out.print(queryString);
		return getHibernateTemplate().find(queryString);
	}



	@Override
	public List<SearchResult> searchByConditions(String[] condition,
			Object[] value, Map<String,Boolean> rigor, String[] symbol) {
		// TODO Auto-generated method stub
		List<SearchResult> result = null;
		//定义一个字符缓冲区
		StringBuffer sqlBuffer = new StringBuffer();
		
		sqlBuffer.append("select new com.dbEntity.SearchResult(d.demandId,o.OName,d.DName,d.startTime,d.endTime,d.DStatus)" +
				"from DemandCollect d join d.operatingOrganization o");
		int length = condition.length;
		boolean first = true;
		for(int i=0;i<length;i++)
		{
			if(condition[i]==null)
			{
				break;
			}
			String ralation=symbol[i];
			if(!rigor.get(condition[i]))
			{
				ralation = "like";
				value[i] = "%"+value[i]+"%";
			}
			if(first)
			{
				sqlBuffer.append(" where "+ condition[i]+" " + ralation+" '"+value[i]+"'");
				first = false;
			}
			else
			{
				sqlBuffer.append(" and "+ condition[i]+" " + ralation+" '"+value[i]+"'");
			}
		}
		System.out.print(sqlBuffer.toString());
		result = getHibernateTemplate().find(sqlBuffer.toString());
		return result;
	}



	@Override
	public List<Object> findAllObject(String model) {
		// TODO Auto-generated method stub
		String queryString = "from "+model;
		return getHibernateTemplate().find(queryString);
	}

	public List<SearchResult> query(final String sql)
	{
		List<SearchResult> list = null;
		list = (List<SearchResult>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException
			{
				// TODO Auto-generated method stub
				Query query = session.createQuery(sql);
				return query.list();
			}
		});
		return list;
	}
}
