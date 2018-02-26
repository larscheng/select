package com.slxy.www.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql123
 * @since 2018-02-04
 */

@TableName("select_topic")
public class SelectTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 选题id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 题目id
     */
    @TableField("sub_id")
    private Integer subId;
    /**
     * 教师id
     */
    @TableField("tea_id")
    private Integer teaId;
    /**
     * 学生id
     */
    @TableField("stu_id")
    private Integer stuId;
    /**
     * 学生选择理由
     */
    @TableField("stu_select_reason")
    private String stuSelectReason;
    /**
     * 教师审核状态 0待处理 1审核不通过，2审核通过
     */
    @TableField("tea_audit_state")
    private Integer teaAuditState;
    /**
     * 教师审核理由
     */
    @TableField("tea_audit_content")
    private String teaAuditContent;
    /**
     * 选题年份
     */
    @TableField("topic_year")
    private String topicYear;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_modify")
    private Date gmtModify;

    public SelectTopic() {
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

    public SelectTopic setId(Integer id) {
        this.id = id;
        return this;
    }

    public SelectTopic setSubId(Integer subId) {
        this.subId = subId;
        return this;
    }

    public SelectTopic setTeaId(Integer teaId) {
        this.teaId = teaId;
        return this;
    }

    public SelectTopic setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public SelectTopic setStuSelectReason(String stuSelectReason) {
        this.stuSelectReason = stuSelectReason;
        return this;
    }

    public SelectTopic setTeaAuditState(Integer teaAuditState) {
        this.teaAuditState = teaAuditState;
        return this;
    }

    public SelectTopic setTeaAuditContent(String teaAuditContent) {
        this.teaAuditContent = teaAuditContent;
        return this;
    }

    public SelectTopic setTopicYear(String topicYear) {
        this.topicYear = topicYear;
        return this;
    }

    public SelectTopic setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public SelectTopic setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
        return this;
    }

    public String toString() {
        return "SelectTopic(id=" + this.getId() + ", subId=" + this.getSubId() + ", teaId=" + this.getTeaId() + ", stuId=" + this.getStuId() + ", stuSelectReason=" + this.getStuSelectReason() + ", teaAuditState=" + this.getTeaAuditState() + ", teaAuditContent=" + this.getTeaAuditContent() + ", topicYear=" + this.getTopicYear() + ", gmtCreate=" + this.getGmtCreate() + ", gmtModify=" + this.getGmtModify() + ")";
    }

}
