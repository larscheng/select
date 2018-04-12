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
@TableName("select_bug_log")
public class SelectBugLog implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**  */
	@TableField(value = "user_id")
	private Integer userId;

	/**  */
	@TableField(value = "bug_title")
	private String bugTitle;

	/**  */
	@TableField(value = "bug_content")
	private String bugContent;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;


	public Integer getId() {
		return this.id;
	}

	public SelectBugLog setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public SelectBugLog setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public String getBugTitle() {
		return this.bugTitle;
	}

	public SelectBugLog setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
		return this;
	}

	public String getBugContent() {
		return this.bugContent;
	}

	public SelectBugLog setBugContent(String bugContent) {
		this.bugContent = bugContent;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public SelectBugLog setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

}
