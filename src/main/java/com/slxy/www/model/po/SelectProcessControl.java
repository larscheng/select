package com.slxy.www.model.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengql123
 * @since 2018-03-27
 */
@TableName("select_process_control")
public class SelectProcessControl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流程id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 流程名称
     */
    @TableField("pro_name")
    private String proName;
    /**
     * 流程开始时间
     */
    @TableField("pro_start_time")
    private Date proStartTime;
    /**
     * 流程结束时间
     */
    @TableField("pro_end_time")
    private Date proEndTime;
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

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Date getProStartTime() {
        return proStartTime;
    }

    public void setProStartTime(Date proStartTime) {
        this.proStartTime = proStartTime;
    }

    public Date getProEndTime() {
        return proEndTime;
    }

    public void setProEndTime(Date proEndTime) {
        this.proEndTime = proEndTime;
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
        return "SelectProcessControl{" +
        "id=" + id +
        ", proName=" + proName +
        ", proStartTime=" + proStartTime +
        ", proEndTime=" + proEndTime +
        ", gmtCreate=" + gmtCreate +
        ", gmtModify=" + gmtModify +
        "}";
    }
}
