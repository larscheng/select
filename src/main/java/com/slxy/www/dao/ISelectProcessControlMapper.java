package com.slxy.www.dao;

import com.slxy.www.domain.po.SelectProcessControl;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 *
 * SelectProcessControl 表数据库控制层接口
 *
 */
public interface ISelectProcessControlMapper extends BaseMapper<SelectProcessControl> {


    List<SelectProcessControl> selectPro();
}