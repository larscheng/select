package com.slxy.www.model.vo;

import lombok.Data;

/**
 * @author zhengql
 * @description 通用分页查询类
 * @className PageVo
 * @create 2017年12月12日  14:44
 */
@Data
public class PageVo {

    /**
     * 当前页码
     */
    private Integer page = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 10;

    /**
     * 搜索关键词
     */
    private String search;

    /**
     * 时间开始区间
     */
    private String searchBgnTime;

    /**
     * 时间结束区间
     */
    private String searchEndTime;

    /**
     * 按某字段排序
     */


}


