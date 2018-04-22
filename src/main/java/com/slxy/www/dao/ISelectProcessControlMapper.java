package com.slxy.www.dao;

import com.slxy.www.domain.po.SelectProcessControl;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 *
 * SelectProcessControl 表数据库控制层接口
 *
 */
public interface ISelectProcessControlMapper extends BaseMapper<SelectProcessControl> {


    SelectProcessControl selectPro();
}