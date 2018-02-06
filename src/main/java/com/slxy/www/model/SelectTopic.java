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
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
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

}
