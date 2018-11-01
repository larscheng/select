package com.slxy.www.domain.dto;



import com.slxy.www.domain.vo.PageVo;

import java.util.Date;

/**
 * @author zhengql
 * @description 选题信息vo
 * @className SelectTopicVo
 * @create 2018年02月04日  13:16
 */

public class SelectTopicDto extends PageVo {
    /**
     * 选题id
     */
    private Integer id;
    /**
     * 题目id
     */
    private Integer subId;
    /**
     * 教师id
     */
    private Integer teaId;
    /**
     * 学生id
     */
    private Integer stuId;
    /**
     * 学生选择理由
     */
    private String stuSelectReason;
    /**
     * 教师审核状态 0待处理 1审核不通过，2审核通过
     */
    private Integer teaAuditState;
    /**
     * 教师审核理由
     */
    private String teaAuditContent;
    /**
     * 选题年份
     */
    private String topicYear;
    /***
     * 任务书
     */
    private String taskFile;
    /**
     * 开题报告
     */
    private String openingReport;
    /**
     * 论文
     */
    private String dissertation;

    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModify;

    private String subName;

    private String stuName;

    private String teaName;

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

    /***
     * 老师电话
     */
    private String teaPhone;
    /***
     * 学生电话
     */
    private String stuPhone;

    /**
     * 删除状态
     */
    private Integer delState;
    /***
     * 审核状态名
     */
    private String teaAuditStateName;

    /***
     * 系
     */
    private String depName;
    /**
     * 专业
     */
    private String majorName;

    /**
     * 面向系别id
     */
    private String forDepId;

    private Integer subSelectStatus;

    private String subSelectStatusName;

    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public SelectTopicDto setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getSubSelectStatusName() {
        return subSelectStatusName;
    }

    public SelectTopicDto setSubSelectStatusName(String subSelectStatusName) {
        this.subSelectStatusName = subSelectStatusName;
        return this;
    }

    public Integer getSubSelectStatus() {
        return subSelectStatus;
    }

    public SelectTopicDto setSubSelectStatus(Integer subSelectStatus) {
        this.subSelectStatus = subSelectStatus;
        return this;
    }

    public String getForDepId() {
        return forDepId;
    }

    public SelectTopicDto setForDepId(String forDepId) {
        this.forDepId = forDepId;
        return this;
    }

    public SelectTopicDto() {
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getSubId() {
        return this.subId;
    }

    public Integer getTeaId() {
        return this.teaId;
    }

    public Integer getStuId() {
        return this.stuId;
    }

    public String getStuSelectReason() {
        return this.stuSelectReason;
    }

    public Integer getTeaAuditState() {
        return this.teaAuditState;
    }

    public String getTeaAuditContent() {
        return this.teaAuditContent;
    }

    public String getTopicYear() {
        return this.topicYear;
    }

    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public Date getGmtModify() {
        return this.gmtModify;
    }

    public String getSubName() {
        return this.subName;
    }

    public String getStuName() {
        return this.stuName;
    }

    public String getTeaName() {
        return this.teaName;
    }

    public String getTaskFile() {
        return taskFile;
    }

    public Integer getDelState() {
        return delState;
    }

    public SelectTopicDto setDelState(Integer delState) {
        this.delState = delState;
        return this;
    }

    public String getTeaAuditStateName() {
        return teaAuditStateName;
    }

    public SelectTopicDto setTeaAuditStateName(String teaAuditStateName) {
        this.teaAuditStateName = teaAuditStateName;
        return this;
    }

    public String getTeaPhone() {
        return teaPhone;
    }

    public SelectTopicDto setTeaPhone(String teaPhone) {
        this.teaPhone = teaPhone;
        return this;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public SelectTopicDto setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
        return this;
    }

    public Double getTutorScore() {
        return tutorScore;
    }


    public SelectTopicDto setTutorScore(Double tutorScore) {
        this.tutorScore = tutorScore;
        return this;
    }

    public Double getJudgeScore() {
        return judgeScore;
    }

    public String getDepName() {
        return depName;
    }

    public SelectTopicDto setDepName(String depName) {
        this.depName = depName;
        return this;
    }

    public String getMajorName() {
        return majorName;
    }

    public SelectTopicDto setMajorName(String majorName) {
        this.majorName = majorName;
        return this;
    }

    public SelectTopicDto setJudgeScore(Double judgeScore) {
        this.judgeScore = judgeScore;
        return this;

    }

    public Double getDefenceScore() {
        return defenceScore;
    }

    public SelectTopicDto setDefenceScore(Double defenceScore) {
        this.defenceScore = defenceScore;
        return this;
    }

    public Double getFinalTotalScore() {
        return finalTotalScore;
    }

    public SelectTopicDto setFinalTotalScore(Double finalTotalScore) {
        this.finalTotalScore = finalTotalScore;
        return this;
    }

    public SelectTopicDto setTaskFile(String taskFile) {
        this.taskFile = taskFile;
        return this;
    }

    public String getOpeningReport() {
        return openingReport;
    }

    public SelectTopicDto setOpeningReport(String openingReport) {
        this.openingReport = openingReport;
        return this;
    }

    public String getDissertation() {
        return dissertation;
    }

    public SelectTopicDto setDissertation(String dissertation) {
        this.dissertation = dissertation;
        return this;
    }

    public SelectTopicDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public SelectTopicDto setSubId(Integer subId) {
        this.subId = subId;
        return this;
    }

    public SelectTopicDto setTeaId(Integer teaId) {
        this.teaId = teaId;
        return this;
    }

    public SelectTopicDto setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public SelectTopicDto setStuSelectReason(String stuSelectReason) {
        this.stuSelectReason = stuSelectReason;
        return this;
    }

    public SelectTopicDto setTeaAuditState(Integer teaAuditState) {
        this.teaAuditState = teaAuditState;
        return this;
    }

    public SelectTopicDto setTeaAuditContent(String teaAuditContent) {
        this.teaAuditContent = teaAuditContent;
        return this;
    }

    public SelectTopicDto setTopicYear(String topicYear) {
        this.topicYear = topicYear;
        return this;
    }

    public SelectTopicDto setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public SelectTopicDto setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
        return this;
    }

    public SelectTopicDto setSubName(String subName) {
        this.subName = subName;
        return this;
    }

    public SelectTopicDto setStuName(String stuName) {
        this.stuName = stuName;
        return this;
    }

    public SelectTopicDto setTeaName(String teaName) {
        this.teaName = teaName;
        return this;
    }

    public String toString() {
        return "SelectTopicDto(id=" + this.getId() + ", subId=" + this.getSubId() + ", teaId=" + this.getTeaId() + ", stuId=" + this.getStuId() + ", stuSelectReason=" + this.getStuSelectReason() + ", teaAuditState=" + this.getTeaAuditState() + ", teaAuditContent=" + this.getTeaAuditContent() + ", topicYear=" + this.getTopicYear() + ", gmtCreate=" + this.getGmtCreate() + ", gmtModify=" + this.getGmtModify() + ", subName=" + this.getSubName() + ", stuName=" + this.getStuName() + ", teaName=" + this.getTeaName() + ")";
    }
}

