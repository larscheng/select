package com.slxy.www.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.domain.po.SelectDepartment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.domain.vo.SelectDepartmentVo;

import java.util.List;

/**
 *
 * SelectDepartment 表数据库控制层接口
 *
 */
public interface ISelectDepartmentMapper extends BaseMapper<SelectDepartment> {
    List<SelectDepartment> getDepByPage(Page<SelectDepartment> page, SelectDepartmentVo vo);

    List<SelectDepartment> selectTeaDep();

}