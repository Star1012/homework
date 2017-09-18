package com.stdcMis.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.dbEntity.DemandCollect;
import com.dbEntity.SearchResult;
import com.stdcMis.DaoImpl.DBDaoImpl;
import com.stdcMis.Service.demandImpl.AddAndSaveDemandManagerImpl;
import com.stdcMis.Service.demandImpl.ShowDemandManagerImpl;

public class test {

	@Test
	public void test() {
		AddAndSaveDemandManagerImpl adi = new AddAndSaveDemandManagerImpl();
		String[] str1 = {"aaaa","bbbbb","cccc","",""};
		String[] str2 = {"aaaa","bbbbb","cccc",null,null};
		String[] str3 = {"111313","131414","default"};
		System.out.println(adi.collectMessage(str1));
		System.out.println(adi.collectMessage(str2));
		System.out.println(adi.collectMessage(str3));
	}
	
	@Test
	public void testK() {
		DBDaoImpl dd = new DBDaoImpl();
		Map<String,Boolean> map = new HashMap();
		map.put("o.OName",true);
		String condition[] = {"o.OName"};
		Object value[] ={"石家庄铁道大学"};
		String symbol[] ={"="};
		List list = dd.searchByConditions(condition, value,map,symbol);
		Object[] object = (Object[])list.get(0);
		String Oname = (String) object[0];
		System.out.println(Oname);
	}
	

}
