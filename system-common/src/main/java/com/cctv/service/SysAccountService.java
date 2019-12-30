package com.cctv.service;

import com.cctv.entity.SysAccountEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 */
public interface SysAccountService {
	
	SysAccountEntity queryObject(String id);
	
	List<SysAccountEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysAccountEntity sysAccount);
	
	void update(SysAccountEntity sysAccount);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	/**
	 * 根据账号名称查询
	 * @param account
	 * @return
	 */
	public SysAccountEntity queryByAccount(String account);
}
