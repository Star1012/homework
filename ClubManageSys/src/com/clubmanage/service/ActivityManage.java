package com.clubmanage.service;

import java.util.List;

import com.clubmanage.model.Activity;

public interface ActivityManage
{
	public void addActivity(Activity activity);
	public void deleteActivity(String activityId);
	public void updateActivity(Activity activity);
	public List<Activity> listActivitys();
	public List<Activity> listActivitysByWei();
	public List<Activity> listActivitysByTon();
	public List<Activity> listActivitysByBu();
	public boolean activityExists(Activity activity);
	public Activity loadActivityById(String activityId);
}
