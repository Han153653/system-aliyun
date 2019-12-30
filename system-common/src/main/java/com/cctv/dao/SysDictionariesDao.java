package com.cctv.dao;

import java.util.List;
import java.util.Map;

import com.cctv.entity.SysDictionariesEntity;

/**
 * 字典表
 * 
 */
public interface SysDictionariesDao extends BaseDao<SysDictionariesEntity> {
	/**
	 * 查询字典树
	 * @return
	 */
	public List<SysDictionariesEntity> queryListForDictTree();
	/**
	 * 根据父级id查询
	 * @param parentId
	 * @return
	 */
	public List<SysDictionariesEntity> queryDictionaryByParentId(Long parentId);
	/**
	 * 根据父级id查询子类
	 * @param params
	 * @return
	 */
	public List<SysDictionariesEntity> queryDicByParentIds(Map<String,Object> params);
	/**
	 * 根据条件查询
	 * @param params
	 * @return
	 */
	public List<SysDictionariesEntity> queryDictionaryByCondition(Map<String,Object> params);
	/**
	 * 根据父类编码查询子类
	 * @param code
	 * @return
	 */
	public List<SysDictionariesEntity> queryDictionaryByParentCode(String code);
	/**
	 * 根据父类编码查询子类
	 * @param code
	 * @return
	 */
	public List<Map<String,Object>> queryDictionaryMapByParentCode(String code);
}
