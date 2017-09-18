package com.stdcMis.Dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dbEntity.OperatingOrganization;

public interface OpogDao {
 
	/**
	 * @return返回用户是否存在数据库
	 */
	public boolean checkUserExistsWithName(OperatingOrganization opog);
	
	/*
	 * @return 返回用户名密码是否正确
	 */
	public boolean checkUserAndPwd(OperatingOrganization opog);
	/**
	 * 加载OperatingOrganization实例
	 * @参数Oname指定需要加载的OperatingOrganization实例的主键值
	 * @return返回加载的OperatingOrganization
	 */
	public OperatingOrganization get(String Oname);
	
	/**
	 * 保存OperatingOrganization实例
	 * @参数Opog指定需要保存的OperatingOrganization实例
	 */
	public boolean save(OperatingOrganization Opog);
	
	/**
	 * 根据条件查找OperatingOrganization
	 * @参数model为所查询的数据模型即表 ，property[]为查询类型，value[]为所查类型的key值,rigor为精确查询或模糊查询的标识,为true时表示精确查询
	 * @return 返回对应的全部OperatingOrganization
	 */
	public List<OperatingOrganization> searchByPropertys(String model,String[]propertyName,Object[] value,boolean rigor);
	
	/**
	 * @参数 Oname为要查询的用户的用户名
	 * @return	OperatingOrganization的角色
	 */
	public abstract String getRole(String Oname);
}
