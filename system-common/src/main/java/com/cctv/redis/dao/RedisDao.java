package com.cctv.redis.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.JedisPubSub;

/** 
 * 说明：redisDAO
 * 创建人：yaoyichen
 * 创建时间：2017-04-21
 * @version
 */
public interface RedisDao {
	
	/**新增(存储Map)
	 * @param key
	 * @param map
	 * @return
	 */
	public String addMap(String key, Map<String, String> map);
	/**新增(存储Map) 带过期时间 秒
	 * @param key
	 * @param map
	 * @return
	 */
	public String addMap(String key, Map<String, String> map,int seconds);
	
	/**获取map
	 * @param key
	 * @return
	 */
	public Map<String,String> getMap(String key);
	
	/**新增(存储List)
	 * @param key
	 * @param list
	 * @return
	 */
	public void addList(String key, List<String> list);
	/**
	 * 设置字符串
	 * @param key
	 * @param value
	 */
	public void setString(String key, String value);
	/**
	 * 设置带有过期时间的字符串
	 * @param key
	 * @param value
	 */
	public void setStringWithExpired(String key, String value, int expired);
	/**
	 * 设置字符串
	 * @param key
	 * @param value
	 */
	public String getString(String key);
	/**
	 * push队列尾部
	 * @param key
	 * @param value
	 */
	public void offer(String key, String value);
	/**push 追加队首
	 * @param key
	 * @param pd
	 * @return
	 */
	public void lpush(String key, String value);
	/**
	 * 获取队首 并移出队列
	 * @param key
	 */
	public String poll(String key);
	/**
	 * 获取队尾 并移出队列
	 * @param key
	 */
	public String rpop(String key);
	/**获取List
	 * @param key
	 * @return
	 */
	public List<String> getList(String key);
	
	/**获取List 分页
	 * @param key
	 * @return
	 */
	public List<String> getList(String key,long start,long end);
	
	/**获取List头一个 不删除
	 * @param key
	 * @return
	 */
	public String getListFirst(String key);
	/**
	 * 监听
	 * @param listener
	 * @param pattern
	 * @return
	 */
	public void psubscribe(JedisPubSub listener,String pattern);
	/**
	 * 发布
	 * @param key
	 * @param value
	 */
	public void publish(String key,String value);
	/**
	 * 获取所有匹配的key
	 * @param key
	 * @return
	 */
	public Set<String> match(String pattern);
	/**新增(存储set)
	 * @param key
	 * @param set
	 */
	public void addSet(String key, Set<String> set);
	
	/**获取Set
	 * @param key
	 * @return
	 */
	public Set<String> getSet(String key);
	
	/**删除
	 * @param key
	 */
	public String del(String key);
	/**
	 * get byte value from redis
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key);
	/**
	 * set byte value
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(byte[] key, byte[] value);
	/**
	 * set byte value with expire time
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	byte[] set(byte[] key, byte[] value, int expire);
	/**
	 * delete value by byte key
	 * @param key
	 */
	public void del(byte[] key);
	/**
	 * clear all keys from this redis db
	 */
	public void flushDB();
	/**
	 * get dbSize
	 * @return
	 */
	public Long dbSize();
	/**
	 * get byte keys by condition
	 * @param pattern
	 * @return
	 */
	Set<byte[]> keys(String pattern);
	/**
	 * 按照指定size添加元素
	 * @param key
	 * @param value
	 * @param size
	 */
	boolean addListSize(String key, String value, int size);
	/**
	 * 按照指定size获取元素
	 * @param key
	 * @param value
	 * @param size
	 */
	List<String> getListSize(String key, int size);
	/**
	 * 修改List列表指定下标的值
	 * @param key
	 * @param index
	 * @param value
	 */
	public void updateList(String key, int index, int value);
	
	
}
