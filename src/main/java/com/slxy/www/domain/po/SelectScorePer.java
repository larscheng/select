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
@TableName("select_score_per")
public class SelectScorePer implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 成绩模块 */
	@TableField(value = "score_name")
	private String scoreName;

	/** 成绩所占百分比 */
	@TableField(value = "score_per")
	private Integer scorePer;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;


	public Integer getId() {
		return this.id;
	}

	public SelectScorePer setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getScoreName() {
		return this.scoreName;
	}

	public SelectScorePer setScoreName(String scoreName) {
		this.scoreName = scoreName;
		return this;
	}

	public Integer getScorePer() {
		return this.scorePer;
	}

	public SelectScorePer setScorePer(Integer scorePer) {
		this.scorePer = scorePer;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public SelectScorePer setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public SelectScorePer setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
