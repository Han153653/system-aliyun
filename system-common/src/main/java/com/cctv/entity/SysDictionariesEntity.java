package com.cctv.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 字典表
 * 
 */
public class SysDictionariesEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//名称
	private String name;
	//编码
	private String code;
	//父ID
	private Long parentId;
	//父名称
	private String parentName;
	//等级
	private Integer level;
	//值
	private String value;
	//备注
	private String remark;
	//顺序
	private Integer sortNum;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：父ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父ID
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 获取： 父名称
	 * @return
	 */
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/**
	 * 设置：等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：等级
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 获取：备注
	 * @return
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 设置：顺序
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	/**
	 * 获取：顺序
	 */
	public Integer getSortNum() {
		return sortNum;
	}
}
