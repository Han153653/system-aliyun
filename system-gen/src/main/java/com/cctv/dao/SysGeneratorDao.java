package com.cctv.dao;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 */
public interface SysGeneratorDao {
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	/**
	 * 查询表的基本信息
	 * @param tableName
	 * @return
	 */
	List<Map<String, String>> queryColumnsDetail(String tableName);
}
