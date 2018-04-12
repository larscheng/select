package com.slxy.www.dao;

import com.slxy.www.domain.dto.SelectBugLogDto;
import com.slxy.www.domain.po.SelectBugLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 *
 * SelectBugLog 表数据库控制层接口
 *
 */
public interface ISelectBugLogMapper extends BaseMapper<SelectBugLog> {

    List<SelectBugLogDto> selectTenLog();
}