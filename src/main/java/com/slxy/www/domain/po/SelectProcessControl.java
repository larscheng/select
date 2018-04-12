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
@TableName("select_process_control")
public class SelectProcessControl implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 流程id */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 流程名称 */
	@TableField(value = "pro_name")
	private String proName;

	/** 流程开始时间 */
	@TableField(value = "pro_start_time")
	private Date proStartTime;

	/** 流程结束时间 */
	@TableField(value = "pro_end_time")
	private Date proEndTime;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;


	public Integer getId() {
		return this.id;
	}

	public SelectProcessControl setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getProName() {
		return this.proName;
	}

	public SelectProcessControl setProName(String proName) {
		this.proName = proName;
		return this;
	}

	public Date getProStartTime() {
		return this.proStartTime;
	}

	public SelectProcessControl setProStartTime(Date proStartTime) {
		this.proStartTime = proStartTime;
		return this;
	}

	public Date getProEndTime() {
		return this.proEndTime;
	}

	public SelectProcessControl setProEndTime(Date proEndTime) {
		this.proEndTime = proEndTime;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public SelectProcessControl setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public SelectProcessControl setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
