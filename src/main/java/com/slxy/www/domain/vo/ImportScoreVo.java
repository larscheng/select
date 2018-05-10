package com.slxy.www.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zhengql
 * @description  学生导入类
 * @className ImportStuVo
 * @create 2018年01月30日  10:18
 */

@Data
@EqualsAndHashCode
@Accessors
public class ImportScoreVo {


    /**
     * 题目名称
     */
    private String subName;

    /**
     * 题目id
     */
    private Integer subId;
    /**.
     * 教师名称
     */
    private String teaName;
    /**
     * 学生名称
     */
    private String stuName;
    /**
     * 题目届别
     */
    private String subYear;
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

}

