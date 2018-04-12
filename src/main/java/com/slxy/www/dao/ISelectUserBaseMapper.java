package com.slxy.www.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.domain.dto.SelectUserBaseDto;
import com.slxy.www.domain.po.SelectUserBase;
import com.slxy.www.domain.vo.SelectUserBaseVo;

import java.util.List;

/**
 *
 * SelectUserBase 表数据库控制层接口
 *
 */
public interface ISelectUserBaseMapper extends BaseMapper<SelectUserBase> {

    List<SelectUserBase> getUserByPage(Page<SelectUserBaseDto> page, SelectUserBaseVo vo);

    List<SelectUserBase> selectStuYear();

    List<SelectUserBase> selectStuMajor();

    List<SelectUserBase> selectStuClass();

    List<SelectUserBase> selectTeaDep();

}