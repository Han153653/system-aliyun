package com.cctv.redis.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.cctv.redis.dao.RedisDao;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisException;

//@Repository("redisDao")
public class RedisDaoImpl implements RedisDao{
	
	private static Logger log = Logger.getLogger(RedisDaoImpl.class);
	
   /* @Resource(name = "redisPool") 
    private JedisPool jedisPool;*/
	@Resource(name="jedisCluster")
	private JedisCluster jedisCluster;
	
	/**新增(存储Map)
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public String addMap(String key, Map<String, String> map) {
		//Jedis jedis =  null;
		String result = "";
		try {
			
			//jedis = getJedis();
			result = jedisCluster.hmset(key,map);
			
		} catch (Exception e) {
			log.info(e);
		}/*finally{
			if(jedis!=null){
				jedis.close();
			}
		}*/
		return result;
	}
	/**新增(存储Map) 过期时间 s
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public String addMap(String key, Map<String, String> map,int seconds) {
		//Jedis jedis =  null;
		String result = "";
		try {
			 //jedis = getJedis();
			 result = jedisCluster.hmset(key,map);
			 jedisCluster.expire(key, seconds);
		} catch (Exception e) {
			log.info(e);
		}/*finally{
			if(jedis!=null){
				jedis.close();
			}
		}*/
		return result;
	}

	/**获取map
	 * @param key
	 * @return
	 */
	@Override
	public Map<String,String> getMap(String key){
		log.info("getMap 参数 key值:"+key);
		//Jedis jedis =  null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			/*jedis = getJedis();
			if (jedis == null) {  
				log.info("getMap 获取jedis实例失败");
			}*/
			Iterator<String> iter=jedisCluster.hkeys(key).iterator();
			while (iter.hasNext()){  
		    	 String ikey = iter.next();  
		    	 map.put(ikey, jedisCluster.hmget(key,ikey).get(0));
			}
		} catch (Exception e) {
			log.info(e);
		}/*finally{
			if(jedis!=null){
				jedis.close();
			}
		}*/
		if(map.isEmpty()){
			return null;
		}else{
			return map;
		}
	}
	
	/**新增(存储List)
	 * @param key
	 * @param pd
	 * @return
	 */
	@Override
	public void addList(String key, List<String> list){
		try {
			//jedis = getJedis();
			jedisCluster.del(key); //开始前，先移除所有的内容  
			for(String value:list){
				jedisCluster.rpush(key,value); 
			}
		} catch (Exception e) {
			log.info(e);
		}
	}
	
	/**新增(存储List)
	 * @param key
	 * @param value
	 *  @param size 指定list的大小
	 * @return
	 */
	@Override
	public boolean addListSize(String key, String value , int size){
		try {
			if(jedisCluster.exists(key)){
				jedisCluster.lpop(key); 
				jedisCluster.rpush(key,value);
			}else{
				for (int i = 0; i < size-1; i++) {
					jedisCluster.rpush(key,"0");
				}
				jedisCluster.rpush(key,value);
//				jedisCluster.rpushx(key,value);
			}
			return true;
		} catch (Exception e) {
			log.info(e);
			return false;
		}
	}
	
	@Override
	public void updateList(String key, int index, int value) {
		try {
			jedisCluster.lset(key, index, String.valueOf(value));
		} catch (Exception e) {
			log.info(e);
		}
	}
	
	/**获取list
	 * @param key
	 * @param value
	 *  @param size 指定list的大小
	 * @return
	 */
	@Override
	public List<String> getListSize(String key,int size){
		List<String> lrange = null ;
		try {
			if(jedisCluster.exists(key)){
				return  jedisCluster.lrange(key, 0, -1);
			}else{
				addListSize(key,"0",size);
				return  jedisCluster.lrange(key, 0, -1);
			}
		} catch (Exception e) {
			log.info(e);
			return lrange;
		}
	}
	
	
	/**
	 * 设置字符串
	 */
	@Override
	public void setString(String key, String value){
		//Jedis jedis =  null;
		try {
			//jedis = getJedis();
			jedisCluster.set(key,value); 
		} catch (Exception e) {
			log.info(e);
		}/*finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
	}
	@Override
	public void setStringWithExpired(String key, String value,int expired){
		try {
			jedisCluster.set(key,value); 
			if(expired != 0){
				jedisCluster.expire(key, expired/1000);
		 	}
		} catch (Exception e) {
			log.info(e);
		}
	}
	
	/**
	 * 获取字符串
	 */
	@Override
	public String getString(String key){
		//Jedis jedis =  null;
		String result = "";
		try {
			//jedis = getJedis();
			result = jedisCluster.get(key); 
		} catch (Exception e) {
			log.info(e);
		}/*finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		return result;
	}
	/**push 追加队尾
	 * @param key
	 * @param pd
	 * @return
	 */
	@Override
	public void offer(String key, String value){
		//Jedis jedis =  null;
		try {
			//jedis = getJedis();
			jedisCluster.rpush(key,value); 
		} catch (Exception e) {
			log.info(e);
		}/*finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
	}
	
	/**push 追加队首
	 * @param key
	 * @param pd
	 * @return
	 */
	@Override
	public void lpush(String key, String value){
		//Jedis jedis =  null;
		try {
			//jedis = getJedis();
			jedisCluster.lpush(key,value); 
		} catch (Exception e) {
			log.info(e);
		}/*finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
	}
	/**
	 * 获取队首并移出队列
	 * @param key
	 */
	@Override
	public String poll(String key){
		//Jedis jedis =  null;
		String value = "";
		try {
			//jedis = getJedis();
			value = jedisCluster.lpop(key); 
		} catch (Exception e) {
			log.info(e);
		}/*finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		return value;
	}
	
	/**
	 * 获取队尾并移出队列
	 * @param key
	 */
	@Override
	public String rpop(String key){
		//Jedis jedis =  null;
		String value = "";
		try {
			//jedis = getJedis();
			value = jedisCluster.rpop(key); 
		} catch (Exception e) {
			log.info(e);
		}/*finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		return value;
	}
	/**获取List
	 * @param key
	 * @return
	 */
	public List<String> getList(String key){
		//Jedis jedis =  null;
		List<String> list = null;
		try{
			//jedis = getJedis();
			list = jedisCluster.lrange(key,0,-1);
			
		}catch(Exception e){
			log.info(e);
		}/*finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		
		return list;
	}
	
	/**获取List 0到-1 分页
	 * @param key
	 * @return
	 */
	public List<String> getList(String key,long start,long end){
		
		//Jedis jedis =  null;
		List<String> list = null;
		try {
			//jedis = getJedis();
			list = jedisCluster.lrange(key,start,end);
			
		} catch (Exception e) {
			log.info(e);
		}/*finally{
			if(jedis != null){
				jedis.close();
			}
		}*/
		return list;
	}
	/**获取List头一个 不删除
	 * @param key
	 * @return
	 */
	public String getListFirst(String key){
		//Jedis jedis =  null;
		String tmp = null;
		try {
			//jedis = getJedis();
			List<String> list = jedisCluster.lrange(key,0,1);
			if(list!=null && list.size()>0){
				tmp = list.get(0);
			}
		} catch (Exception e) {
			log.info(e);
		}/*finally{
			if(jedis != null){
				jedis.close();
			}
		}*/
		
		return tmp;
	}
	/**
	 * 配置监听
	 */
	public void psubscribe(JedisPubSub listener,String pattern){
		//Jedis jedis =  null;
		try{
			//jedis = getJedis();
			jedisCluster.psubscribe(listener, pattern);
			
		}catch(Exception e){
			log.info(e);
		}/*finally{
			if(jedis != null){
				jedis.close();
			}
		}*/
	}
	/**
	 * 
	 * 发布消息
	 */
	public void publish(String key,String value){
		//Jedis jedis =  null;
		try{
			//jedis = getJedis();
			jedisCluster.publish(key, value);
		}catch(Exception e){
			e.printStackTrace();
		}/*finally{
			if(jedis != null){
				jedis.close();
			}
		}*/
	}
	/**
	 * 获取所有匹配的key *
	 * @param key
	 * @return
	 */
	public Set<String> match(String pattern){
		Set<String> set = null;
		//Jedis jedis =  null;
		try {
			//jedis = getJedis();
			set = jedisCluster.hkeys(pattern);
			
		} catch (Exception e) {
			log.info(e);
		}/*finally {
			if(jedis!=null){
				jedis.close();
			}
		}*/
		return set; 
	}
	/**新增(存储set)
	 * @param key
	 * @param set
	 */
	public void addSet(String key, Set<String> set){
		//Jedis jedis =  null;
		try {
			//jedis = getJedis();
			jedisCluster.del(key);
			for(String value:set){
				jedisCluster.sadd(key,value); 
			}
		}catch(Exception e) {
			log.info(e);
		}/*finally {
			if(jedis!=null){
				jedis.close();
			}
		}*/
	}
	
	/**获取Set
	 * @param key
	 * @return
	 */
	public Set<String> getSet(String key){
		Set<String> set = null;
		//Jedis jedis =  null;
		try {
			//jedis = getJedis();
			set = jedisCluster.smembers(key);
			
		}catch(Exception e){
			
			log.info(e);
			
		}/* finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		
		return set;
	}
	/**
	 * 删除
	 * @param key
	 */
	public String del(String key){
		//Jedis jedis =  null;
		String result = "";
		try {
			//jedis = getJedis();
			result = String.valueOf(jedisCluster.del(key));
			
		}catch(Exception e){		
			log.info(e);	
		}/* finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		return result;
	}

	/**
	 * get value from redis
	 * @param key
	 * @return
	 */
	@Override
	public byte[] get(byte[] key){
		byte[] result = null;
		//Jedis jedis = null;
		try{
			//jedis = this.getJedis();
			result = jedisCluster.get(key);
		}catch(Exception e){		
			log.info(e);	
		}/* finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		return result;
	}
	
	/**
	 * set byte value
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public byte[] set(byte[] key,byte[] value){
		//Jedis jedis = null;
		try{
			//jedis = this.getJedis();
			jedisCluster.set(key,value);
		}catch(Exception e){		
			log.info(e);	
		}/* finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		return value;
	}
	
	/**
	 * set byte value with expire time
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	@Override
	public byte[] set(byte[] key,byte[] value,int expire){
		//log.info("the expire is :" + expire);
		//Jedis jedis = null;
		try{
			//jedis = this.getJedis();
			jedisCluster.set(key,value);
			if(expire != 0){
				jedisCluster.expire(key, expire/1000);
		 	}
		}catch(Exception e){		
			log.info(e);	
		}/* finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		return value;
	}
	
	/**
	 * del value by byte key
	 * @param key
	 */
	@Override
	public void del(byte[] key){
		//Jedis jedis = null;
		try{
			//jedis = this.getJedis();
			jedisCluster.del(key);
		}catch(Exception e){		
			log.info(e);	
		}/* finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
	}
	
	/**
	 * clear all key from this redis db
	 */
	@Override
	public void flushDB(){
		//Jedis jedis = null;
		try{
			//jedis = this.getJedis();
			jedisCluster.flushDB();
		}catch(Exception e){		
			log.info(e);	
		}/* finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
	}
	
	/**
	 * get dbsize
	 */
	@Override
	public Long dbSize(){
		Long dbSize = 0L;
		//Jedis jedis = null;
		try{
			//jedis = getJedis();
			dbSize = jedisCluster.dbSize();
		}catch(Exception e){		
			log.info(e);	
		}/* finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		return dbSize;
	}

	/**
	 *  get byte keys by condition
	 * @param regex
	 * @return
	 */
	@Override
	public Set<byte[]> keys(String pattern){
		Set<byte[]> keys = null;
		//Jedis jedis = null;
		try{
			//jedis = getJedis();
			keys = jedisCluster.hkeys(pattern.getBytes());
		}catch(Exception e){		
			log.info(e);	
		} /*finally {
			if(jedis != null){
				jedis.close();
			}
		}*/
		return keys;
	}
	
	  
    /**获取Jedis
	 * @return
	 *//*
	private Jedis getJedis() throws JedisException{
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis == null) {  
				log.info("获取jedis实例失败");
			}
		}catch(JedisException e){
			log.info("获取jedis实例失败");
			if (jedis != null) {  
				jedis.close();
			}
			throw e;
		}
	    return jedis;  
		
	}*/

}
