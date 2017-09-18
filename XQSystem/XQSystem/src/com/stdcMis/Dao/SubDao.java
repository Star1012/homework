package com.stdcMis.Dao;

import java.util.List;
import com.dbEntity.Subjects;

public interface SubDao {
	
	/**
	 * 查找一级学科
	 * @return 返回对应的全部Subjects
	 */
	List<Subjects> searchSub(String value);
}
