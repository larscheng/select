package com.slxy.www.domain.vo;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-25
 */

public class SelectSubjectVo extends  PageVo {


    /**
     * 题目id
     */
    private Integer id;
    /**
     * 题目名称
     */
    private String subName;
    /**
     * 发布教师id
     */
    private Integer teaId;
    /**
     * 题目类型，1应用型，2理论性
     */
    private Integer subType;
    /**
     * 题目内容
     */
    private String subContent;
    /**
     * 审核状态 0未处理，1审核不通过，2审核通过
     */
    private Integer admAuditState;
    /**
     * 审核意见
     */
    private String admAuditContent;
    /**
     * 审核人id
     */
    private Integer admAuditId;
    /**
     * 指导老师评分
     */
    private Double tutorScore;
    /**
     * 评阅老师评分
     */
    private Double judgeScore;
    /**
     * 答辩得分
     */
    private Double defenceScore;
    /**
     * 最终总得分
     */
    private Double finalTotalScore;
    /**
     * 题目年份
     */
    private String subYear;
    /**
     * 题目面向系别id
     */
    private Integer forDepId;
    /**
     * 题目选题状态 0未选 1审核中 2已选
     */
    private Integer subSelectStatus;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModify;


    /*教师名*/
    private String subTeaName;

    /*论文类型名*/
    private String typeName;

    /***
     * 选题人id
     */
    private Integer selectId;
    /***
     * 选择原因
     */
    private  String selectReason;

    public SelectSubjectVo() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getSubName() {
        return this.subName;
    }

    public Integer getTeaId() {
        return this.teaId;
    }

    public Integer getSubType() {
        return this.subType;
    }

    public String getSubContent() {
        return this.subContent;
    }

    public Integer getAdmAuditState() {
        return this.admAuditState;
    }

    public String getAdmAuditContent() {
        return this.admAuditContent;
    }

    public Integer getAdmAuditId() {
        return this.admAuditId;
    }

    public Double getTutorScore() {
        return this.tutorScore;
    }

    public Double getJudgeScore() {
        return this.judgeScore;
    }

    public Double getDefenceScore() {
        return this.defenceScore;
    }

    public Double getFinalTotalScore() {
        return this.finalTotalScore;
    }

    public String getSubYear() {
        return this.subYear;
    }

    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public Date getGmtModify() {
        return this.gmtModify;
    }

    public String getSubTeaName() {
        return this.subTeaName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public SelectSubjectVo setId(Integer id) {
        this.id = id;
        return this;
    }

    public SelectSubjectVo setSubName(String subName) {
        this.subName = subName;
        return this;
    }

    public SelectSubjectVo setTeaId(Integer teaId) {
        this.teaId = teaId;
        return this;
    }

    public SelectSubjectVo setSubType(Integer subType) {
        this.subType = subType;
        return this;
    }

    public SelectSubjectVo setSubContent(String subContent) {
        this.subContent = subContent;
        return this;
    }

    public SelectSubjectVo setAdmAuditState(Integer admAuditState) {
        this.admAuditState = admAuditState;
        return this;
    }

    public SelectSubjectVo setAdmAuditContent(String admAuditContent) {
        this.admAuditContent = admAuditContent;
        return this;
    }

    public SelectSubjectVo setAdmAuditId(Integer admAuditId) {
        this.admAuditId = admAuditId;
        return this;
    }

    public SelectSubjectVo setTutorScore(Double tutorScore) {
        this.tutorScore = tutorScore;
        return this;
    }

    public SelectSubjectVo setJudgeScore(Double judgeScore) {
        this.judgeScore = judgeScore;
        return this;
    }

    public SelectSubjectVo setDefenceScore(Double defenceScore) {
        this.defenceScore = defenceScore;
        return this;
    }

    public SelectSubjectVo setFinalTotalScore(Double finalTotalScore) {
        this.finalTotalScore = finalTotalScore;
        return this;
    }

    public SelectSubjectVo setSubYear(String subYear) {
        this.subYear = subYear;
        return this;
    }

    public SelectSubjectVo setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public SelectSubjectVo setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
        return this;
    }

    public SelectSubjectVo setSubTeaName(String subTeaName) {
        this.subTeaName = subTeaName;
        return this;
    }

    public SelectSubjectVo setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public Integer getForDepId() {
        return forDepId;
    }

    public SelectSubjectVo setForDepId(Integer forDepId) {
        this.forDepId = forDepId;
        return this;
    }

    public Integer getSubSelectStatus() {
        return subSelectStatus;
    }

    public SelectSubjectVo setSubSelectStatus(Integer subSelectStatus) {
        this.subSelectStatus = subSelectStatus;
        return this;
    }

    public Integer getSelectId() {
        return selectId;
    }

    public SelectSubjectVo setSelectId(Integer selectId) {
        this.selectId = selectId;
        return this;
    }

    public String getSelectReason() {
        return selectReason;
    }

    public SelectSubjectVo setSelectReason(String selectReason) {
        this.selectReason = selectReason;
        return this;
    }
}
