package com.slxy.www.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.model.SelectSubject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.model.dto.SelectSubjectDto;
import com.slxy.www.model.vo.SelectSubjectVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-25
 */
public interface SelectSubjectMapper extends BaseMapper<SelectSubject> {

    List<SelectSubject> getSubByPage(SelectSubjectVo vo, Page<SelectSubject> page);

    List<SelectSubject> getMySubByPage(SelectSubjectVo vo, Page<SelectSubject> page);

    List<SelectSubjectDto> selectAllSubject();
}
