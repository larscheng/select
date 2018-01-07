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
 * @since 2018-01-07
 */
@TableName("select_major")
public class SelectMajor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专业id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 专业名称
     */
    @TableField("maj_name")
    private String majName;
    /**
     * 所属系别
     */
    @TableField("dep_id")
    private Integer depId;
    /**
     * 专业班级数
     */
    @TableField("maj_class_num")
    private Integer majClassNum;
    /**
     * 专业状态 0禁用，1启用
     */
    @TableField("maj_status")
    private Integer majStatus;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajName() {
        return majName;
    }

    public void setMajName(String majName) {
        this.majName = majName;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getMajClassNum() {
        return majClassNum;
    }

    public void setMajClassNum(Integer majClassNum) {
        this.majClassNum = majClassNum;
    }

    public Integer getMajStatus() {
        return majStatus;
    }

    public void setMajStatus(Integer majStatus) {
        this.majStatus = majStatus;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "SelectMajor{" +
        "id=" + id +
        ", majName=" + majName +
        ", depId=" + depId +
        ", majClassNum=" + majClassNum +
        ", majStatus=" + majStatus +
        ", gmtCreate=" + gmtCreate +
        "}";
    }
}
