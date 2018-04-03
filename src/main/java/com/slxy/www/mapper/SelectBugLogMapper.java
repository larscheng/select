package com.slxy.www.mapper;

import com.slxy.www.model.dto.SelectBugLogDto;
import com.slxy.www.model.po.SelectBugLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengql123
 * @since 2018-04-03
 */
public interface SelectBugLogMapper extends BaseMapper<SelectBugLog> {

    List<SelectBugLogDto> selectTenLog();
}
