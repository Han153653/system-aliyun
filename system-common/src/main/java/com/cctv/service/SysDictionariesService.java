package com.cctv.service;

import com.cctv.entity.SysDictionariesEntity;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 */
public interface SysDictionariesService {
	
	SysDictionariesEntity queryObject(Long id);
	
	List<SysDictionariesEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysDictionariesEntity sysDictionaries);
	
	void update(SysDictionariesEntity sysDictionaries);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
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
	 * 根据code条件查询
	 * @param code
	 * @return
	 */
	public String getValueByCode(String code);
	
	/**
	 * 根据父类编码查询子类
	 * @param code
	 * @return
	 */
	public List<SysDictionariesEntity> queryDictionaryByParentCode(String code);
	/**
	 * 根据父类编码查询子类编码和值
	 * @param code
	 * @return
	 */
	public Map<String,Object> getCodeValueByParentCode(String code);
	
	/**
	 * 根据父类编码查询子类
	 * @param code
	 * @return
	 */
	public List<Map<String,Object>> queryDictionaryMapByParentCode(String code);
	/**
	 * 依据code查询信息
	 * @param string
	 * @return
	 */
	SysDictionariesEntity queryDictionaryByCode(String code);
}
