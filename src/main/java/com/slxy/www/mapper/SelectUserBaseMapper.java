package com.slxy.www.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.model.SelectUserBase;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.model.vo.SelectUserBaseVo;
import com.slxy.www.model.dto.SelectUserBaseDto;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengql
 * @since 2018-01-06
 */
public interface SelectUserBaseMapper extends BaseMapper<SelectUserBase> {

    List<SelectUserBase> getUserByPage(Page<SelectUserBaseDto> page, SelectUserBaseVo vo);

    List<SelectUserBase> selectStuYear();

    List<SelectUserBase> selectStuMajor();

    List<SelectUserBase> selectStuClass();

    List<SelectUserBase> selectTeaDep();
}
