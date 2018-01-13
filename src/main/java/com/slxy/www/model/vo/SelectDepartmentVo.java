package com.slxy.www.model.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
 * @author zhengql123
 * @since 2018-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SelectDepartmentVo extends  PageVo{


    /**
     * 系别id
     */
    private Integer id;
    /**
     * 系别名称
     */
    private String depName;
    /**
     * 系别状态 0禁用，1启用
     */
    private Integer depStatus;

    /**
     * 系别状态 0禁用，1启用
     */
    private String depInfo;

    private Date gmtCreate;


}
