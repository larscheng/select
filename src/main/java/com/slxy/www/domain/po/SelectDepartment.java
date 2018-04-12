package com.slxy.www.domain.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName("select_department")
public class SelectDepartment implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 系别id */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 系别名称 */
	@TableField(value = "dep_name")
	private String depName;

	/** 系别介绍 */
	@TableField(value = "dep_info")
	private String depInfo;

	/** 系别状态 0禁用，1启用 */
	@TableField(value = "dep_status")
	private Integer depStatus;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;


	public Integer getId() {
		return this.id;
	}

	public SelectDepartment setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getDepName() {
		return this.depName;
	}

	public SelectDepartment setDepName(String depName) {
		this.depName = depName;
		return this;
	}

	public String getDepInfo() {
		return this.depInfo;
	}

	public SelectDepartment setDepInfo(String depInfo) {
		this.depInfo = depInfo;
		return this;
	}

	public Integer getDepStatus() {
		return this.depStatus;
	}

	public SelectDepartment setDepStatus(Integer depStatus) {
		this.depStatus = depStatus;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public SelectDepartment setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public SelectDepartment setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
