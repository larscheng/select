package com.slxy.www.model.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SelectTopicVo extends PageVo {
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

    /**
     * 删除状态
     */
    private Integer delState;

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
}

