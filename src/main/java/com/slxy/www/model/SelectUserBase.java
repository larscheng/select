package com.slxy.www.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sun.jdi.IntegerType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@TableName("select_user_base")
public class SelectUserBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号
     */
    @TableField("user_code")
    private String userCode;
    /**
     * 密码
     */
    @TableField("user_password")
    private String userPassword;
    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 性别 1男，2女
     */
    @TableField("user_sex")
    private Integer userSex;
    /**
     * 邮箱
     */
    @TableField("user_mail")
    private String userMail;
    /**
     * 电话
     */
    @TableField("user_phone")
    private String userPhone;
    /**
     * qq
     */
    @TableField("user_qq")
    private String userQq;
    /**
     * 学生专业名称
     */
    @TableField("stu_major_id")
    private Integer stuMajorId;
    /**
     * 学生班级
     */
    @TableField("stu_class")
    private Integer stuClass;
    /**
     * 学生届别
     */
    @TableField("stu_year")
    private String stuYear;
    /**
     * 教师职称 1教授，2副教授，3讲师，4助教
     */
    @TableField("tea_position")
    private Integer teaPosition;
    /**
     * 教师专业名称
     */
    @TableField("tea_major_name")
    private String teaMajorName;
    /**
     * 教师学历 1博士，2硕士，3本科
     */
    @TableField("tea_education")
    private Integer teaEducation;
    /**
     * 教师研究方向
     */
    @TableField("tea_direction")
    private String teaDirection;
    /**
     * 教师所属系别
     */
    @TableField("tea_dep_id")
    private Integer teaDepId;
    /**
     * 教师个人简介
     */
    @TableField("tea_info")
    private String teaInfo;
    /**
     * 用户状态 0禁用，1启用
     */
    @TableField("user_status")
    private Integer userStatus;
    /**
     * 用户类型 1管理员，2教师，3学生
     */
    @TableField("user_type")
    private Integer userType;
    /**
     * 操作人员id
     */
    @TableField("operator_id")
    private Integer operatorId;
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

    public Integer getId() {
        return this.id;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public String getUserName() {
        return this.userName;
    }

    public Integer getUserSex() {
        return this.userSex;
    }

    public String getUserMail() {
        return this.userMail;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public String getUserQq() {
        return this.userQq;
    }

    public Integer getStuMajorId() {
        return this.stuMajorId;
    }

    public Integer getStuClass() {
        return this.stuClass;
    }

    public String getStuYear() {
        return this.stuYear;
    }

    public Integer getTeaPosition() {
        return this.teaPosition;
    }

    public String getTeaMajorName() {
        return this.teaMajorName;
    }

    public Integer getTeaEducation() {
        return this.teaEducation;
    }

    public String getTeaDirection() {
        return this.teaDirection;
    }

    public Integer getTeaDepId() {
        return this.teaDepId;
    }

    public String getTeaInfo() {
        return this.teaInfo;
    }

    public Integer getUserStatus() {
        return this.userStatus;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public Integer getOperatorId() {
        return this.operatorId;
    }

    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public Date getGmtModify() {
        return this.gmtModify;
    }

    public SelectUserBase setId(Integer id) {
        this.id = id;
        return this;
    }

    public SelectUserBase setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public SelectUserBase setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public SelectUserBase setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public SelectUserBase setUserSex(Integer userSex) {
        this.userSex = userSex;
        return this;
    }

    public SelectUserBase setUserMail(String userMail) {
        this.userMail = userMail;
        return this;
    }

    public SelectUserBase setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public SelectUserBase setUserQq(String userQq) {
        this.userQq = userQq;
        return this;
    }

    public SelectUserBase setStuMajorId(Integer stuMajorId) {
        this.stuMajorId = stuMajorId;
        return this;
    }

    public SelectUserBase setStuClass(Integer stuClass) {
        this.stuClass = stuClass;
        return this;
    }

    public SelectUserBase setStuYear(String stuYear) {
        this.stuYear = stuYear;
        return this;
    }

    public SelectUserBase setTeaPosition(Integer teaPosition) {
        this.teaPosition = teaPosition;
        return this;
    }

    public SelectUserBase setTeaMajorName(String teaMajorName) {
        this.teaMajorName = teaMajorName;
        return this;
    }

    public SelectUserBase setTeaEducation(Integer teaEducation) {
        this.teaEducation = teaEducation;
        return this;
    }

    public SelectUserBase setTeaDirection(String teaDirection) {
        this.teaDirection = teaDirection;
        return this;
    }

    public SelectUserBase setTeaDepId(Integer teaDepId) {
        this.teaDepId = teaDepId;
        return this;
    }

    public SelectUserBase setTeaInfo(String teaInfo) {
        this.teaInfo = teaInfo;
        return this;
    }

    public SelectUserBase setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public SelectUserBase setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public SelectUserBase setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public SelectUserBase setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public SelectUserBase setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
        return this;
    }



}
