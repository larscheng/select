package com.slxy.www.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class SelectMajorDTO{


    /**
     * 专业id
     */
    private Integer id;
    /**
     * 专业名称
     */
    private String majName;
    /**
     * 所属系别
     */
    private Integer depId;
    /**
     * 专业班级数
     */
    private Integer majClassNum;
    /**
     * 专业状态 0禁用，1启用
     */
    private Integer majStatus;
    /**
     * 专业介绍
     */
    private String majInfo;
    /**
     * 创建时间
     */
    private Date gmtCreate;

    private Date gmtModify;

    private String depName;

}
