package com.stdcMis.Dao;

import java.util.List;

import com.dbEntity.NationalEconomy;

public interface NaeDao {

	/**
	 * 查找一级学科
	 * @return 返回对应的全部Subjects
	 */
	List<NationalEconomy> searchNae(String value);
}
