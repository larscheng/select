package com.slxy.www.model.dto;

import com.slxy.www.model.vo.PageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-25
 */

public class SelectSubjectDto extends  PageVo {


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

    /*审核状态**/
    private String subState;

    /*审核人*/
    private String admAuditName;

    /**/

    public SelectSubjectDto() {
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

    public String getSubState() {
        return this.subState;
    }

    public String getAdmAuditName() {
        return this.admAuditName;
    }

    public SelectSubjectDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public SelectSubjectDto setSubName(String subName) {
        this.subName = subName;
        return this;
    }

    public SelectSubjectDto setTeaId(Integer teaId) {
        this.teaId = teaId;
        return this;
    }

    public SelectSubjectDto setSubType(Integer subType) {
        this.subType = subType;
        return this;
    }

    public SelectSubjectDto setSubContent(String subContent) {
        this.subContent = subContent;
        return this;
    }

    public SelectSubjectDto setAdmAuditState(Integer admAuditState) {
        this.admAuditState = admAuditState;
        return this;
    }

    public SelectSubjectDto setAdmAuditContent(String admAuditContent) {
        this.admAuditContent = admAuditContent;
        return this;
    }

    public SelectSubjectDto setAdmAuditId(Integer admAuditId) {
        this.admAuditId = admAuditId;
        return this;
    }

    public SelectSubjectDto setTutorScore(Double tutorScore) {
        this.tutorScore = tutorScore;
        return this;
    }

    public SelectSubjectDto setJudgeScore(Double judgeScore) {
        this.judgeScore = judgeScore;
        return this;
    }

    public SelectSubjectDto setDefenceScore(Double defenceScore) {
        this.defenceScore = defenceScore;
        return this;
    }

    public SelectSubjectDto setFinalTotalScore(Double finalTotalScore) {
        this.finalTotalScore = finalTotalScore;
        return this;
    }

    public SelectSubjectDto setSubYear(String subYear) {
        this.subYear = subYear;
        return this;
    }

    public SelectSubjectDto setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public SelectSubjectDto setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
        return this;
    }

    public SelectSubjectDto setSubTeaName(String subTeaName) {
        this.subTeaName = subTeaName;
        return this;
    }

    public SelectSubjectDto setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public SelectSubjectDto setSubState(String subState) {
        this.subState = subState;
        return this;
    }

    public SelectSubjectDto setAdmAuditName(String admAuditName) {
        this.admAuditName = admAuditName;
        return this;
    }

    public String toString() {
        return "SelectSubjectDto(id=" + this.getId() + ", subName=" + this.getSubName() + ", teaId=" + this.getTeaId() + ", subType=" + this.getSubType() + ", subContent=" + this.getSubContent() + ", admAuditState=" + this.getAdmAuditState() + ", admAuditContent=" + this.getAdmAuditContent() + ", admAuditId=" + this.getAdmAuditId() + ", tutorScore=" + this.getTutorScore() + ", judgeScore=" + this.getJudgeScore() + ", defenceScore=" + this.getDefenceScore() + ", finalTotalScore=" + this.getFinalTotalScore() + ", subYear=" + this.getSubYear() + ", gmtCreate=" + this.getGmtCreate() + ", gmtModify=" + this.getGmtModify() + ", subTeaName=" + this.getSubTeaName() + ", typeName=" + this.getTypeName() + ", subState=" + this.getSubState() + ", admAuditName=" + this.getAdmAuditName() + ")";
    }
}
