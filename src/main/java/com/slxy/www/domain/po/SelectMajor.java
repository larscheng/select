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
@TableName("select_major")
public class SelectMajor implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 专业id */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 专业名称 */
	@TableField(value = "maj_name")
	private String majName;

	/** 所属系别 */
	@TableField(value = "dep_id")
	private Integer depId;

	/** 专业班级数 */
	@TableField(value = "maj_class_num")
	private Integer majClassNum;

	/** 专业状态 0禁用，1启用 */
	@TableField(value = "maj_status")
	private Integer majStatus;

	/** 专业介绍 */
	@TableField(value = "maj_info")
	private String majInfo;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;


	public Integer getId() {
		return this.id;
	}

	public SelectMajor setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getMajName() {
		return this.majName;
	}

	public SelectMajor setMajName(String majName) {
		this.majName = majName;
		return this;
	}

	public Integer getDepId() {
		return this.depId;
	}

	public SelectMajor setDepId(Integer depId) {
		this.depId = depId;
		return this;
	}

	public Integer getMajClassNum() {
		return this.majClassNum;
	}

	public SelectMajor setMajClassNum(Integer majClassNum) {
		this.majClassNum = majClassNum;
		return this;
	}

	public Integer getMajStatus() {
		return this.majStatus;
	}

	public SelectMajor setMajStatus(Integer majStatus) {
		this.majStatus = majStatus;
		return this;
	}

	public String getMajInfo() {
		return this.majInfo;
	}

	public SelectMajor setMajInfo(String majInfo) {
		this.majInfo = majInfo;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public SelectMajor setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public SelectMajor setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
