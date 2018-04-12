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
@TableName("select_subject")
public class SelectSubject implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 题目id */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 题目名称 */
	@TableField(value = "sub_name")
	private String subName;

	/** 发布教师id */
	@TableField(value = "tea_id")
	private Integer teaId;

	/** 题目类型，1应用型，2理论性 */
	@TableField(value = "sub_type")
	private Integer subType;

	/** 题目文件路径 */
	@TableField(value = "sub_file")
	private String subFile;

	/** 题目内容 */
	@TableField(value = "sub_content")
	private String subContent;

	/** 题目选题状态 0未选 1审核中 2已选 */
	@TableField(value = "sub_select_status")
	private Integer subSelectStatus;

	/** 审核状态 0未处理，1审核不通过，2审核通过 */
	@TableField(value = "adm_audit_state")
	private Integer admAuditState;

	/** 审核意见 */
	@TableField(value = "adm_audit_content")
	private String admAuditContent;

	/** 审核人id */
	@TableField(value = "adm_audit_id")
	private Integer admAuditId;

	/** 指导老师评分 */
	@TableField(value = "tutor_score")
	private Double tutorScore;

	/** 评阅老师评分 */
	@TableField(value = "judge_score")
	private Double judgeScore;

	/** 答辩得分 */
	@TableField(value = "defence_score")
	private Double defenceScore;

	/** 最终总得分 */
	@TableField(value = "final_total_score")
	private Double finalTotalScore;

	/** 题目面向系别 */
	@TableField(value = "for_dep_id")
	private Integer forDepId;

	/** 题目年份 */
	@TableField(value = "sub_year")
	private String subYear;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;


	public Integer getId() {
		return this.id;
	}

	public SelectSubject setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getSubName() {
		return this.subName;
	}

	public SelectSubject setSubName(String subName) {
		this.subName = subName;
		return this;
	}

	public Integer getTeaId() {
		return this.teaId;
	}

	public SelectSubject setTeaId(Integer teaId) {
		this.teaId = teaId;
		return this;
	}

	public Integer getSubType() {
		return this.subType;
	}

	public SelectSubject setSubType(Integer subType) {
		this.subType = subType;
		return this;
	}

	public String getSubFile() {
		return this.subFile;
	}

	public SelectSubject setSubFile(String subFile) {
		this.subFile = subFile;
		return this;
	}

	public String getSubContent() {
		return this.subContent;
	}

	public SelectSubject setSubContent(String subContent) {
		this.subContent = subContent;
		return this;
	}

	public Integer getSubSelectStatus() {
		return this.subSelectStatus;
	}

	public SelectSubject setSubSelectStatus(Integer subSelectStatus) {
		this.subSelectStatus = subSelectStatus;
		return this;
	}

	public Integer getAdmAuditState() {
		return this.admAuditState;
	}

	public SelectSubject setAdmAuditState(Integer admAuditState) {
		this.admAuditState = admAuditState;
		return this;
	}

	public String getAdmAuditContent() {
		return this.admAuditContent;
	}

	public SelectSubject setAdmAuditContent(String admAuditContent) {
		this.admAuditContent = admAuditContent;
		return this;
	}

	public Integer getAdmAuditId() {
		return this.admAuditId;
	}

	public SelectSubject setAdmAuditId(Integer admAuditId) {
		this.admAuditId = admAuditId;
		return this;
	}

	public Double getTutorScore() {
		return this.tutorScore;
	}

	public SelectSubject setTutorScore(Double tutorScore) {
		this.tutorScore = tutorScore;
		return this;
	}

	public Double getJudgeScore() {
		return this.judgeScore;
	}

	public SelectSubject setJudgeScore(Double judgeScore) {
		this.judgeScore = judgeScore;
		return this;
	}

	public Double getDefenceScore() {
		return this.defenceScore;
	}

	public SelectSubject setDefenceScore(Double defenceScore) {
		this.defenceScore = defenceScore;
		return this;
	}

	public Double getFinalTotalScore() {
		return this.finalTotalScore;
	}

	public SelectSubject setFinalTotalScore(Double finalTotalScore) {
		this.finalTotalScore = finalTotalScore;
		return this;
	}

	public Integer getForDepId() {
		return this.forDepId;
	}

	public SelectSubject setForDepId(Integer forDepId) {
		this.forDepId = forDepId;
		return this;
	}

	public String getSubYear() {
		return this.subYear;
	}

	public SelectSubject setSubYear(String subYear) {
		this.subYear = subYear;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public SelectSubject setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public SelectSubject setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
