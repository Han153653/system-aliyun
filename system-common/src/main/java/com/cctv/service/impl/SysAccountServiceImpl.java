package com.cctv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.cctv.dao.SysAccountDao;
import com.cctv.entity.SysAccountEntity;
import com.cctv.service.SysAccountService;



@Service("sysAccountService")
public class SysAccountServiceImpl implements SysAccountService {
	@Autowired
	private SysAccountDao sysAccountDao;
	
	@Override
	public SysAccountEntity queryObject(String id){
		return sysAccountDao.queryObject(id);
	}
	
	@Override
	public List<SysAccountEntity> queryList(Map<String, Object> map){
		return sysAccountDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysAccountDao.queryTotal(map);
	}
	
	@Override
	public void save(SysAccountEntity sysAccount){
		sysAccountDao.save(sysAccount);
	}
	
	@Override
	public void update(SysAccountEntity sysAccount){
		sysAccountDao.update(sysAccount);
	}
	
	@Override
	public void delete(String id){
		sysAccountDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		sysAccountDao.deleteBatch(ids);
	}

	@Override
	public SysAccountEntity queryByAccount(String account) {
		return sysAccountDao.queryByAccount(account);
	}
	
}
