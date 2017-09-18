package com.clubmanage.dao;

import java.util.List;

import com.clubmanage.model.Activity;

public interface ActivityDao
{
	public void saveActivity(Activity activity);
	public void deleteActivityById(String activityId);
	public void updateActivity(Activity activity);
	public List<Activity> getActivitys();
	public List<Activity> getActivitysByWei();
	public List<Activity> getActivitysByTon();
	public List<Activity> getActivitysByBu();
	public boolean checkActivityExistsWithId(String activityId);
	public Activity loadById(String activityId);
}
