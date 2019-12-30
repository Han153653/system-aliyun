package com.cctv.dao;

import com.cctv.entity.SysAccountEntity;

/**
 * 
 * 
 */
public interface SysAccountDao extends BaseDao<SysAccountEntity> {
	/**
	 * 根据账号名称查询
	 * @param account
	 * @return
	 */
	public SysAccountEntity queryByAccount(String account);
}
