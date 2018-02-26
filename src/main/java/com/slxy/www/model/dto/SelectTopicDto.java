package com.slxy.www.model.dto;

import com.slxy.www.model.vo.PageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

