package com.stdcMis.Dao;

import java.util.List;
import com.dbEntity.Permission;

public interface PermissionDao {

	/**
	 * 加载Permission实例
	 * @参数role指定需要加载的Permission实例的主键值
	 * @return返回加载的OperatingOrganization
	 */
	Permission get(String role);
	
	/**
	 * 保存Permission实例
	 * @参数permission指定需要保存的Permission实例
	 */
	boolean save(Permission permission);
	
	/**
	 * 根据条件查找Permission
	 * @参数model为所查询的数据模型即表 ，property[]为查询类型，value[]为所查类型的key值,rigor为精确查询或模糊查询的标识,为true时表示精确查询
	 * @return 返回对应的全部OperatingOrganization
	 */
	List<Permission> searchByPropertys(String model,String[]propertyName,Object[] value,boolean rigor);
}
