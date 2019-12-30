package com.cctv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cctv.dao.SysDictionariesDao;
import com.cctv.entity.SysDictionariesEntity;
import com.cctv.service.SysDictionariesService;



@Service("sysDictionariesService")
public class SysDictionariesServiceImpl implements SysDictionariesService {
	@Autowired
	private SysDictionariesDao sysDictionariesDao;
	
	@Override
	public SysDictionariesEntity queryObject(Long id){
		return sysDictionariesDao.queryObject(id);
	}
	
	@Override
	public List<SysDictionariesEntity> queryList(Map<String, Object> map){
		return sysDictionariesDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysDictionariesDao.queryTotal(map);
	}
	
	@Override
	public void save(SysDictionariesEntity sysDictionaries){
		sysDictionariesDao.save(sysDictionaries);
	}
	
	@Override
	public void update(SysDictionariesEntity sysDictionaries){
		sysDictionariesDao.update(sysDictionaries);
	}
	
	@Override
	public void delete(Long id){
		sysDictionariesDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysDictionariesDao.deleteBatch(ids);
	}
	
	@Override
	public List<SysDictionariesEntity> queryListForDictTree() {
		return sysDictionariesDao.queryListForDictTree();
	}

	@Override
	public List<SysDictionariesEntity> queryDictionaryByParentId(Long parentId) {
		return sysDictionariesDao.queryDictionaryByParentId(parentId);
	}

	@Override
	public List<SysDictionariesEntity> queryDictionaryByCondition(Map<String, Object> params) {
		return sysDictionariesDao.queryDictionaryByCondition(params);
	}
	
	@Override
	public String getValueByCode(String code) {
		
		String result = "";
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		List<SysDictionariesEntity> dicList = 
				sysDictionariesDao.queryDictionaryByCondition(params);
		if(dicList != null && dicList.size() > 0){
			SysDictionariesEntity dictionariesEntity = dicList.get(0);
			if(dictionariesEntity != null){
				result = dictionariesEntity.getValue();
			}
		}
		return result;
	}

	@Override
	public List<SysDictionariesEntity> queryDictionaryByParentCode(String code) {
		return sysDictionariesDao.queryDictionaryByParentCode(code);
	}
	@Override
	public Map<String,Object> getCodeValueByParentCode(String code) {
		Map<String,Object> res = new HashMap<String,Object>();
		List<SysDictionariesEntity> list = this.queryDictionaryByParentCode(code);
		if(list!=null&&list.size()>0){
			for(SysDictionariesEntity d:list){
				res.put(d.getCode(), d.getValue());
			}
			return res;
		}else{
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> queryDictionaryMapByParentCode(String code) {
		return sysDictionariesDao.queryDictionaryMapByParentCode(code);
	}

	@Override
	public List<SysDictionariesEntity> queryDicByParentIds(Map<String, Object> params) {
		return this.sysDictionariesDao.queryDicByParentIds(params);
	}

	@Override
	public SysDictionariesEntity queryDictionaryByCode(String code) {
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		List<SysDictionariesEntity> dicList = 
				sysDictionariesDao.queryDictionaryByCondition(params);
		if(dicList != null && dicList.size() > 0){
			return dicList.get(0);
		}
		return null;
	}

}
