package com.slxy.www.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.model.SelectTopic;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.model.dto.SelectTopicDto;
import com.slxy.www.model.vo.SelectTopicVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengql123
 * @since 2018-02-04
 */
public interface SelectTopicMapper extends BaseMapper<SelectTopic> {

    List<SelectTopicDto> getTopicByPage(Page<SelectTopicDto> page, SelectTopicVo vo);

    List<SelectTopicDto> selectAllTopic();
}
