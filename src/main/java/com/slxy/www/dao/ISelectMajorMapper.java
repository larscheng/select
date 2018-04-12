package com.slxy.www.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.domain.dto.SelectMajorDTO;
import com.slxy.www.domain.po.SelectMajor;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.domain.vo.SelectMajorVo;

import java.util.List;

/**
 *
 * SelectMajor 表数据库控制层接口
 *
 */
public interface ISelectMajorMapper extends BaseMapper<SelectMajor> {
    List<SelectMajorDTO> getMajByPage(Page<SelectMajorDTO> page, SelectMajorVo vo);

}