package com.slxy.www.domain.dto;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql
 * @since 2018-01-06
 */

public class SelectUserBaseDto {

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
     * 学生专业id
     */
    private Integer stuMajorId;
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

    private String teaPositionZ;
    /**
     * 教师专业名称
     */
    private String teaMajorName;
    /**
     * 教师学历 1博士，2硕士，3本科
     */
    private Integer teaEducation;

    private String teaEducationZ;

    /**
     * 教师研究方向
     */
    private String teaDirection;
    /**
     * 教师所属系别
     */
    private String teaDepName;
    /**
     * 教师所属id
     */
    private Integer teaDepId;
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
    /**
     * 性别
     */
    private String sex;

    public SelectUserBaseDto() {
    }

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

    public String getStuMajorName() {
        return this.stuMajorName;
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
    public String getTeaPositionZ() {
        return this.teaPositionZ;
    }

    public String getTeaMajorName() {
        return this.teaMajorName;
    }

    public Integer getTeaEducation() {
        return this.teaEducation;
    }
    public String getTeaEducationZ() {
        return this.teaEducationZ;
    }

    public String getTeaDirection() {
        return this.teaDirection;
    }

    public String getTeaDepName() {
        return this.teaDepName;
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


    public String getSex() {
        return this.sex;
    }

    public SelectUserBaseDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public SelectUserBaseDto setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public SelectUserBaseDto setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public SelectUserBaseDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public SelectUserBaseDto setUserSex(Integer userSex) {
        this.userSex = userSex;
        return this;
    }

    public SelectUserBaseDto setUserMail(String userMail) {
        this.userMail = userMail;
        return this;
    }

    public SelectUserBaseDto setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public SelectUserBaseDto setUserQq(String userQq) {
        this.userQq = userQq;
        return this;
    }

    public SelectUserBaseDto setStuMajorName(String stuMajorName) {
        this.stuMajorName = stuMajorName;
        return this;
    }

    public SelectUserBaseDto setStuClass(Integer stuClass) {
        this.stuClass = stuClass;
        return this;
    }

    public SelectUserBaseDto setStuYear(String stuYear) {
        this.stuYear = stuYear;
        return this;
    }

    public SelectUserBaseDto setTeaPosition(Integer teaPosition) {
        this.teaPosition = teaPosition;
        return this;
    }

    public SelectUserBaseDto setTeaPositionZ(String teaPositionZ) {
        this.teaPositionZ = teaPositionZ;
        return this;
    }

    public SelectUserBaseDto setTeaMajorName(String teaMajorName) {
        this.teaMajorName = teaMajorName;
        return this;
    }

    public SelectUserBaseDto setTeaEducation(Integer teaEducation) {
        this.teaEducation = teaEducation;
        return this;
    }

    public SelectUserBaseDto setTeaEducationZ(String teaEducationZ) {
        this.teaEducationZ = teaEducationZ;
        return this;
    }

    public SelectUserBaseDto setTeaDirection(String teaDirection) {
        this.teaDirection = teaDirection;
        return this;
    }

    public SelectUserBaseDto setTeaDepName(String teaDepName) {
        this.teaDepName = teaDepName;
        return this;
    }

    public SelectUserBaseDto setTeaInfo(String teaInfo) {
        this.teaInfo = teaInfo;
        return this;
    }

    public SelectUserBaseDto setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public SelectUserBaseDto setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public SelectUserBaseDto setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public SelectUserBaseDto setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public SelectUserBaseDto setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
        return this;
    }



    public SelectUserBaseDto setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Integer getStuMajorId() {
        return stuMajorId;
    }

    public void setStuMajorId(Integer stuMajorId) {
        this.stuMajorId = stuMajorId;
    }

    public Integer getTeaDepId() {
        return teaDepId;
    }

    public void setTeaDepId(Integer teaDepId) {
        this.teaDepId = teaDepId;
    }
}
