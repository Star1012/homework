package com.stdcMis.DaoImpl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.dbEntity.NationalEconomy;
import com.stdcMis.Dao.NaeDao;

@Component("naeDao")
public class NaeDaoImpl extends SuperDao implements NaeDao {

	private static final Logger log = LoggerFactory
			.getLogger(NaeDaoImpl.class);
	
	@Override
	public List<NationalEconomy> searchNae(String value) {
		String strSql = "from NationalEconomy as model where model.code like '%"+value+"%'";
		return getHibernateTemplate().find(strSql);
	}

}
