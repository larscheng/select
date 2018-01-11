package com.slxy.www.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.model.SelectUserBase;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.model.SelectUserBaseVo;

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

    List<SelectUserBase> getUserByPage(Page<SelectUserBase> page, SelectUserBaseVo vo);
}
