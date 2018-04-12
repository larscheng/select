package com.slxy.www.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zhengql
 * @description 教师导入类
 * @className ImportTeaVo
 * @create 2018年01月30日  14:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ImportTeaVo {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 账号
     */
    private String userCode;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 性别 1男，2女
     */
    private String userSex;
    /**
     * 邮箱
     */
    private String userMail;
    /**
     * 电话
     */
    private String userPhone;
    /**
     * qq
     */
    private String userQq;
    /**
     * 教师职称 1教授，2副教授，3讲师，4助教
     */
    private String teaPosition;
    /**
     * 教师专业名称
     */
    private String teaMajorName;
    /**
     * 教师学历 1博士，2硕士，3本科
     */
    private String teaEducation;
    /**
     * 教师所属系别
     */
    private String teaDepName;
}

