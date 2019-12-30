package com.cctv.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author ${author}
 * @email ${email}
 * @date 2017-10-27 09:17:48
 */
public class SysAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//系统账号
	private String account;
	//密码
	private String password;
	//系统名称
	private String name;
	//状态：-1:禁用 2:正常
	private String status;
	//过期时间
	private Date expiredTime;
	//创建人
	private String creater;
	//创建时间
	private Date createTime;
	//最后一次登录时间
	private Date lastLogin;

	/**
	 * 设置：id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：系统账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：系统账号
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：系统名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：系统名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：状态：-1:禁用 2:正常
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态：-1:禁用 2:正常
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：过期时间
	 */
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	/**
	 * 获取：过期时间
	 */
	public Date getExpiredTime() {
		return expiredTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreater() {
		return creater;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：最后一次登录时间
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * 获取：最后一次登录时间
	 */
	public Date getLastLogin() {
		return lastLogin;
	}
}
