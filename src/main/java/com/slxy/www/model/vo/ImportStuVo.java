package com.slxy.www.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhengql
 * @description  学生导入类
 * @className ImportStuVo
 * @create 2018年01月30日  10:18
 */

@Data
@EqualsAndHashCode
@Accessors
public class ImportStuVo {


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
     * 学生专业名称
     */
    private String stuMajorName;
    /**
     * 学生班级
     */
    private Integer stuClass;
    /**
     * 学生届别
     */
    private String stuYear;

}

