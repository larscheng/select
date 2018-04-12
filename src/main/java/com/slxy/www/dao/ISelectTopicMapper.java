package com.slxy.www.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.domain.dto.SelectTopicDto;
import com.slxy.www.domain.po.SelectTopic;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.domain.vo.SelectTopicVo;

import java.util.List;

/**
 *
 * SelectTopic 表数据库控制层接口
 *
 */
public interface ISelectTopicMapper extends BaseMapper<SelectTopic> {


    List<SelectTopicDto> getTopicByPage(Page<SelectTopicDto> page, SelectTopicVo vo);

    List<SelectTopicDto> selectAllTopic();
}