package com.slxy.www.domain.po;

import java.io.Serializable;

/**
 * @author zhengql
 * @description 修改密码实体类
 * @className ChangePs
 * @create 2018年04月15日  16:57
 */
public class ChangePs implements Serializable {

    private Integer userId;
    private String userMail;
    private String passWord;
    private String newPassWord;
    private String newPassWord2;
    private String userPhone;
    private String userQq;
    private String sixCode;

    private Integer userType;
    private String userCode;

    public String getSixCode() {
        return sixCode;
    }

    public void setSixCode(String sixCode) {
        this.sixCode = sixCode;
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }

    public String getNewPassWord2() {
        return newPassWord2;
    }

    public void setNewPassWord2(String newPassWord2) {
        this.newPassWord2 = newPassWord2;
    }
}

