package com.slxy.www.mapper;

import com.slxy.www.model.SelectUserBase;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-06
 */
public interface SelectUserBaseMapper extends BaseMapper<SelectUserBase> {

    List<SelectUserBase> selectUserBaseList();
}
