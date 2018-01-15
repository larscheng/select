package com.slxy.www.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.model.SelectMajor;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.model.dto.SelectMajorDTO;
import com.slxy.www.model.vo.SelectMajorVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengql
 * @since 2018-01-07
 */
public interface SelectMajorMapper extends BaseMapper<SelectMajor> {

    List<SelectMajorDTO> getMajByPage(Page<SelectMajorDTO> page, SelectMajorVo vo);

}
