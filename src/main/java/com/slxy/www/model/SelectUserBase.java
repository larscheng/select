package com.slxy.www.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.sql.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-06
 */
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
    @TableField("stu_major_name")
    private String stuMajorName;
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
    @TableField("tea_dep_name")
    private String teaDepName;
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
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public String getStuMajorName() {
        return stuMajorName;
    }

    public void setStuMajorName(String stuMajorName) {
        this.stuMajorName = stuMajorName;
    }

    public Integer getStuClass() {
        return stuClass;
    }

    public void setStuClass(Integer stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuYear() {
        return stuYear;
    }

    public void setStuYear(String stuYear) {
        this.stuYear = stuYear;
    }

    public Integer getTeaPosition() {
        return teaPosition;
    }

    public void setTeaPosition(Integer teaPosition) {
        this.teaPosition = teaPosition;
    }

    public String getTeaMajorName() {
        return teaMajorName;
    }

    public void setTeaMajorName(String teaMajorName) {
        this.teaMajorName = teaMajorName;
    }

    public Integer getTeaEducation() {
        return teaEducation;
    }

    public void setTeaEducation(Integer teaEducation) {
        this.teaEducation = teaEducation;
    }

    public String getTeaDirection() {
        return teaDirection;
    }

    public void setTeaDirection(String teaDirection) {
        this.teaDirection = teaDirection;
    }

    public String getTeaDepName() {
        return teaDepName;
    }

    public void setTeaDepName(String teaDepName) {
        this.teaDepName = teaDepName;
    }

    public String getTeaInfo() {
        return teaInfo;
    }

    public void setTeaInfo(String teaInfo) {
        this.teaInfo = teaInfo;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public String toString() {
        return "SelectUserBase{" +
        "id=" + id +
        ", userCode=" + userCode +
        ", userPassword=" + userPassword +
        ", userName=" + userName +
        ", userSex=" + userSex +
        ", userMail=" + userMail +
        ", userPhone=" + userPhone +
        ", userQq=" + userQq +
        ", stuMajorName=" + stuMajorName +
        ", stuClass=" + stuClass +
        ", stuYear=" + stuYear +
        ", teaPosition=" + teaPosition +
        ", teaMajorName=" + teaMajorName +
        ", teaEducation=" + teaEducation +
        ", teaDirection=" + teaDirection +
        ", teaDepName=" + teaDepName +
        ", teaInfo=" + teaInfo +
        ", userStatus=" + userStatus +
        ", userType=" + userType +
        ", operatorId=" + operatorId +
        ", gmtCreate=" + gmtCreate +
        ", gmtModify=" + gmtModify +
        "}";
    }
}
