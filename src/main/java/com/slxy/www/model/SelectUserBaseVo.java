package com.slxy.www.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql
 * @since 2018-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SelectUserBaseVo extends PageVo {

    /**
     * 用户id
     */
    private Integer id;
    /**
     * 账号
     */
    private String userCode;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 性别 1男，2女
     */
    private Integer userSex;
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
    /**
     * 教师职称 1教授，2副教授，3讲师，4助教
     */
    private Integer teaPosition;
    /**
     * 教师专业名称
     */
    private String teaMajorName;
    /**
     * 教师学历 1博士，2硕士，3本科
     */
    private Integer teaEducation;
    /**
     * 教师研究方向
     */
    private String teaDirection;
    /**
     * 教师所属系别
     */
    private String teaDepName;
    /**
     * 教师个人简介
     */
    private String teaInfo;
    /**
     * 用户状态 0禁用，1启用
     */
    private Integer userStatus;
    /**
     * 用户类型 1管理员，2教师，3学生
     */
    private Integer userType;
    /**
     * 操作人员id
     */
    private Integer operatorId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModify;


}
