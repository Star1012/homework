package com.clubmanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.clubmanage.dao.ActivityDao;
import com.clubmanage.model.Activity;
import com.clubmanage.service.ActivityManage;

@Component("activityManage")
public class ActivityManageImpl implements ActivityManage
{

	private ActivityDao activityDao;
	
	@Override
	public void addActivity(Activity activity)
	{
		activityDao.saveActivity(activity);
	}

	@Override
	public void deleteActivity(String activityId)
	{
		activityDao.deleteActivityById(activityId);
	}

	@Override
	public void updateActivity(Activity activity)
	{
		activityDao.updateActivity(activity);
	}

	@Override
	public List<Activity> listActivitys()
	{
		return activityDao.getActivitys();
	}
	
	@Override
	public List<Activity> listActivitysByWei()
	{
		return activityDao.getActivitysByWei();
	}
	
	@Override
	public List<Activity> listActivitysByTon()
	{
		return activityDao.getActivitysByTon();
	}
	
	@Override
	public List<Activity> listActivitysByBu()
	{
		return activityDao.getActivitysByBu();
	}

	@Override
	public boolean activityExists(Activity activity)
	{
		return activityDao.checkActivityExistsWithId(activity.getActivityId());
	}

	@Override
	public Activity loadActivityById(String activityId)
	{
		return activityDao.loadById(activityId);
	}

	public ActivityDao getActivityDao()
	{
		return activityDao;
	}

	@Resource
	public void setActivityDao(ActivityDao activityDao)
	{
		this.activityDao = activityDao;
	}
	
}
