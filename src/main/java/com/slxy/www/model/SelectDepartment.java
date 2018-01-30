package com.slxy.www.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
 * @since 2018-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
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

    /**
     * 系别状态 0禁用，1启用
     */
    @TableField("dep_info")
    private String depInfo;

    @TableField("gmt_create")
    private Date gmtCreate;

    @TableField("gmt_modify")
    private Date gmtModify;

}
