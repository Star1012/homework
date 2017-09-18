package com.clubmanage.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.clubmanage.dao.ActivityDao;
import com.clubmanage.model.Activity;

@Component("activityDao")
public class ActivityDaoImpl implements ActivityDao
{

	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void saveActivity(Activity activity)
	{
		hibernateTemplate.save(activity);
	}

	@Override
	public void deleteActivityById(String activityId)
	{
		hibernateTemplate.delete(loadById(activityId));
	}

	@Override
	public void updateActivity(Activity activity)
	{
		hibernateTemplate.update(activity);
	}

	@Override
	public List<Activity> getActivitys()
	{
		List<Activity> activitys = hibernateTemplate.find("from Activity");
		return activitys;
	}
	
	@Override
	public List<Activity> getActivitysByWei()
	{
		List<Activity> activitys = hibernateTemplate.find("from Activity a where a.zhuangtai='未审核'");
		return activitys;
	}
	
	@Override
	public List<Activity> getActivitysByTon()
	{
		List<Activity> activitys = hibernateTemplate.find("from Activity a where a.zhuangtai='通过'");
		return activitys;
	}
	
	@Override
	public List<Activity> getActivitysByBu()
	{
		List<Activity> activitys = hibernateTemplate.find("from Activity a where a.zhuangtai='不通过'");
		return activitys;
	}

	@Override
	public boolean checkActivityExistsWithId(String activityId)
	{
		List<Activity> activitys = hibernateTemplate.find("from Activity a where a.activityId='" + activityId + "'");
		if(activitys.size() > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public Activity loadById(String activityId)
	{
		Activity activity = (Activity) hibernateTemplate.load(Activity.class, activityId);
		return activity;
	}

	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
