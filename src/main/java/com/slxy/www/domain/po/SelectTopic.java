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
@TableName("select_topic")
public class SelectTopic implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 选题id */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 题目id */
	@TableField(value = "sub_id")
	private Integer subId;

	/** 教师id */
	@TableField(value = "tea_id")
	private Integer teaId;

	/** 学生id */
	@TableField(value = "stu_id")
	private Integer stuId;

	/** 学生选择理由 */
	@TableField(value = "stu_select_reason")
	private String stuSelectReason;

	/** 教师审核状态 0待处理 1审核不通过，2审核通过 */
	@TableField(value = "tea_audit_state")
	private Integer teaAuditState;

	/** 教师审核理由 */
	@TableField(value = "tea_audit_content")
	private String teaAuditContent;

	/** 选题年份 */
	@TableField(value = "topic_year")
	private String topicYear;

	/** 任务书 */
	@TableField(value = "task_file")
	private String taskFile;

	/** 开题报告 */
	@TableField(value = "opening_report")
	private String openingReport;

	/** 毕业论文 */
	private String dissertation;

	/** 删除状态 0未删除，1已删除 */
	@TableField(value = "del_state")
	private Integer delState;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;


	public Integer getId() {
		return this.id;
	}

	public SelectTopic setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getSubId() {
		return this.subId;
	}

	public SelectTopic setSubId(Integer subId) {
		this.subId = subId;
		return this;
	}

	public Integer getTeaId() {
		return this.teaId;
	}

	public SelectTopic setTeaId(Integer teaId) {
		this.teaId = teaId;
		return this;
	}

	public Integer getStuId() {
		return this.stuId;
	}

	public SelectTopic setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}

	public String getStuSelectReason() {
		return this.stuSelectReason;
	}

	public SelectTopic setStuSelectReason(String stuSelectReason) {
		this.stuSelectReason = stuSelectReason;
		return this;
	}

	public Integer getTeaAuditState() {
		return this.teaAuditState;
	}

	public SelectTopic setTeaAuditState(Integer teaAuditState) {
		this.teaAuditState = teaAuditState;
		return this;
	}

	public String getTeaAuditContent() {
		return this.teaAuditContent;
	}

	public SelectTopic setTeaAuditContent(String teaAuditContent) {
		this.teaAuditContent = teaAuditContent;
		return this;
	}

	public String getTopicYear() {
		return this.topicYear;
	}

	public SelectTopic setTopicYear(String topicYear) {
		this.topicYear = topicYear;
		return this;
	}

	public String getTaskFile() {
		return this.taskFile;
	}

	public SelectTopic setTaskFile(String taskFile) {
		this.taskFile = taskFile;
		return this;
	}

	public String getOpeningReport() {
		return this.openingReport;
	}

	public SelectTopic setOpeningReport(String openingReport) {
		this.openingReport = openingReport;
		return this;
	}

	public String getDissertation() {
		return this.dissertation;
	}

	public SelectTopic setDissertation(String dissertation) {
		this.dissertation = dissertation;
		return this;
	}

	public Integer getDelState() {
		return this.delState;
	}

	public SelectTopic setDelState(Integer delState) {
		this.delState = delState;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public SelectTopic setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public SelectTopic setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
