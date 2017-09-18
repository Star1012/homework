package com.stdcMis.DaoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import com.dbEntity.Permission;
import com.stdcMis.Dao.PermissionDao;

@Component("perDao")
public class PermissionDaoImpl extends SuperDao implements PermissionDao {

	//定义日志实例
	private static final Logger log = LoggerFactory
			.getLogger(OpogDaoImpl.class);
		
	
	@Override
	public Permission get(String role) {
		// TODO Auto-generated method stub
		return (Permission) getHibernateTemplate().get(Permission.class, role);
	}

	@Override
	public boolean save(Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Permission> searchByPropertys(String model,
			String[] propertyName, Object[] value, boolean rigor) {
		// TODO Auto-generated method stub
		return null;
	}


}
