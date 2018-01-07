package com.slxy.www.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.sql.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-07
 */
@TableName("select_department")
public class SelectDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系别id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 系别名称
     */
    @TableField("dep_name")
    private String depName;
    /**
     * 系别状态 0禁用，1启用
     */
    @TableField("dep_status")
    private Integer depStatus;
    @TableField("gmt_create")
    private Date gmtCreate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getDepStatus() {
        return depStatus;
    }

    public void setDepStatus(Integer depStatus) {
        this.depStatus = depStatus;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "SelectDepartment{" +
                "id=" + id +
                ", depName=" + depName +
                ", depStatus=" + depStatus +
                ", gmtCreate=" + gmtCreate +
                "}";
    }
}
