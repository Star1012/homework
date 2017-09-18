package com.stdcMis.DaoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.dbEntity.Subjects;
import com.stdcMis.Dao.SubDao;

@Component("subDao")
public class SubDaoImpl extends SuperDao implements SubDao {

	@Override
	public List<Subjects> searchSub(String value) {
		// TODO Auto-generated method stub
		String strSql = "from Subjects as model where model.code like '%"+value+"%'";
		return getHibernateTemplate().find(strSql);
	}

}
