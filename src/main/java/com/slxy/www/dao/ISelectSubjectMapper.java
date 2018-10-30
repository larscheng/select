package com.slxy.www.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.domain.dto.SelectSubjectDto;
import com.slxy.www.domain.po.SelectSubject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.domain.vo.SelectSubjectVo;

import java.util.List;

/**
 *
 * SelectSubject 表数据库控制层接口
 *
 */
public interface ISelectSubjectMapper extends BaseMapper<SelectSubject> {

    List<SelectSubject> getSubByPage(SelectSubjectVo vo, Page<SelectSubject> page);

    List<SelectSubject> getMySubByPage(SelectSubjectVo vo, Page<SelectSubject> page);

    List<SelectSubjectDto> selectAllSubject();

    void autoUpdateStatus();

    List<SelectSubject> getSubByPage(SelectSubjectVo vo);

    List<SelectSubject> countSubByPage(SelectSubjectVo vo, Page<SelectSubject> page);

    List<SelectSubject> countSubByPage(SelectSubjectVo vo);
}