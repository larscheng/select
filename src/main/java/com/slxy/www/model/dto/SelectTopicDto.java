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
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
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

//    private Integer subSelectStatus;

    private String subName;

    private String stuName;

    private String teaName;
//
//    private String subSelectStatusName;
//
//    private String teaAuditStatusName;
}

