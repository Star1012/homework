package com.stdcMis.Dao;

import java.util.List;
import java.util.Map;

import com.dbEntity.DemandCollect;
import com.dbEntity.OperatingOrganization;
import com.dbEntity.SearchResult;

public interface DBDao {

	/**
	 * @参数model指定要获取的数据实体名称(如com.x.y.Entityname)
	 * @参数key指定需要加载的实体类的主键值
	 * @return返回加载的数据实体
	 */
	Object get(String model,Object key);
	
	/**
	 *@参数model指定要查询的数据实体的名称
	 *@return返回查询结果集
	 */
	List<Object> findAllObject(String model);
	
	/**
	 *@参数object指定要保存的实体对象
	 */
	boolean save(Object object);
	
	/**
	 *@参数object指定要删除的实体对象 
	 */
	boolean delete(Object object);
	/**
	 * @return返回将要插入下一条需求表的id
	 */
	String setID();
	/**
	 * 保存DemandCollect实例
	 * @参数Demc指定需要保存的DemandCollect实例
	 */
	boolean save(DemandCollect Demc);
	
	boolean update(DemandCollect Demc);
	/**
	 * 根据条件查找DemandCollect
	 * @参数model为所查询的数据模型即表 ，property[]为查询类型，value[]为所查类型的key值,rigor为精确查询或模糊查询的标识,为true时表示精确查询
	 * @return 返回对应的全部DemandCollect
	 */
	List<DemandCollect> searchByPropertys(String model,String[]propertyName,Object[] value,boolean rigor);

	
	List<SearchResult> searchByConditions(String[] condition,Object[] value,Map<String,Boolean> rigor,String[] symbol);
	/**
	 *
	 * 
	 */
	List<DemandCollect> searchUpdateList(String name);
	
	public List<SearchResult> query(final String sql);
}
