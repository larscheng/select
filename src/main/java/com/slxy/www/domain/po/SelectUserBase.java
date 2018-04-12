package com.slxy.www.domain.po;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 *
 */
@TableName("select_user_base")
public class SelectUserBase implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 用户id */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 账号 */
	@TableField(value = "user_code")
	private String userCode;

	/** 密码 */
	@TableField(value = "user_password")
	private String userPassword;

	/** 姓名 */
	@TableField(value = "user_name")
	private String userName;

	/** 性别 1男，2女 */
	@TableField(value = "user_sex")
	private Integer userSex;

	/** 邮箱 */
	@TableField(value = "user_mail")
	private String userMail;

	/** 电话 */
	@TableField(value = "user_phone")
	private String userPhone;

	/** qq */
	@TableField(value = "user_qq")
	private String userQq;

	/** 学生专业id */
	@TableField(value = "stu_major_id")
	private Integer stuMajorId;

	/** 学生班级 */
	@TableField(value = "stu_class")
	private Integer stuClass;

	/** 学生届别 */
	@TableField(value = "stu_year")
	private String stuYear;

	/** 教师职称 1教授，2副教授，3讲师，4助教 */
	@TableField(value = "tea_position")
	private Integer teaPosition;

	/** 教师专业名称 */
	@TableField(value = "tea_major_name")
	private String teaMajorName;

	/** 教师学历 1博士，2硕士，3本科 */
	@TableField(value = "tea_education")
	private Integer teaEducation;

	/** 教师研究方向 */
	@TableField(value = "tea_direction")
	private String teaDirection;

	/** 教师所属系别id */
	@TableField(value = "tea_dep_id")
	private Integer teaDepId;

	/** 教师个人简介 */
	@TableField(value = "tea_info")
	private String teaInfo;

	/** 用户状态 0禁用，1启用 */
	@TableField(value = "user_status")
	private Integer userStatus;

	/** 用户类型 1管理员，2教师，3学生 */
	@TableField(value = "user_type")
	private Integer userType;

	/** 操作人员id */
	@TableField(value = "operator_id")
	private Integer operatorId;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;


	public Integer getId() {
		return this.id;
	}

	public SelectUserBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public SelectUserBase setUserCode(String userCode) {
		this.userCode = userCode;
		return this;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public SelectUserBase setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}

	public String getUserName() {
		return this.userName;
	}

	public SelectUserBase setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public Integer getUserSex() {
		return this.userSex;
	}

	public SelectUserBase setUserSex(Integer userSex) {
		this.userSex = userSex;
		return this;
	}

	public String getUserMail() {
		return this.userMail;
	}

	public SelectUserBase setUserMail(String userMail) {
		this.userMail = userMail;
		return this;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public SelectUserBase setUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}

	public String getUserQq() {
		return this.userQq;
	}

	public SelectUserBase setUserQq(String userQq) {
		this.userQq = userQq;
		return this;
	}

	public Integer getStuMajorId() {
		return this.stuMajorId;
	}

	public SelectUserBase setStuMajorId(Integer stuMajorId) {
		this.stuMajorId = stuMajorId;
		return this;
	}

	public Integer getStuClass() {
		return this.stuClass;
	}

	public SelectUserBase setStuClass(Integer stuClass) {
		this.stuClass = stuClass;
		return this;
	}

	public String getStuYear() {
		return this.stuYear;
	}

	public SelectUserBase setStuYear(String stuYear) {
		this.stuYear = stuYear;
		return this;
	}

	public Integer getTeaPosition() {
		return this.teaPosition;
	}

	public SelectUserBase setTeaPosition(Integer teaPosition) {
		this.teaPosition = teaPosition;
		return this;
	}

	public String getTeaMajorName() {
		return this.teaMajorName;
	}

	public SelectUserBase setTeaMajorName(String teaMajorName) {
		this.teaMajorName = teaMajorName;
		return this;
	}

	public Integer getTeaEducation() {
		return this.teaEducation;
	}

	public SelectUserBase setTeaEducation(Integer teaEducation) {
		this.teaEducation = teaEducation;
		return this;
	}

	public String getTeaDirection() {
		return this.teaDirection;
	}

	public SelectUserBase setTeaDirection(String teaDirection) {
		this.teaDirection = teaDirection;
		return this;
	}

	public Integer getTeaDepId() {
		return this.teaDepId;
	}

	public SelectUserBase setTeaDepId(Integer teaDepId) {
		this.teaDepId = teaDepId;
		return this;
	}

	public String getTeaInfo() {
		return this.teaInfo;
	}

	public SelectUserBase setTeaInfo(String teaInfo) {
		this.teaInfo = teaInfo;
		return this;
	}

	public Integer getUserStatus() {
		return this.userStatus;
	}

	public SelectUserBase setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
		return this;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public SelectUserBase setUserType(Integer userType) {
		this.userType = userType;
		return this;
	}

	public Integer getOperatorId() {
		return this.operatorId;
	}

	public SelectUserBase setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public SelectUserBase setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public SelectUserBase setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
