package com.slxy.www.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.SelectTopic;
import com.slxy.www.mapper.SelectTopicMapper;
import com.slxy.www.model.dto.SelectMajorDTO;
import com.slxy.www.model.dto.SelectTopicDto;
import com.slxy.www.model.enums.EnumSubSelectStatus;
import com.slxy.www.model.enums.EnumSubState;
import com.slxy.www.model.vo.SelectTopicVo;
import com.slxy.www.service.ISelectTopicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengql123
 * @since 2018-02-04
 */
@Service
public class SelectTopicServiceImpl extends ServiceImpl<SelectTopicMapper, SelectTopic> implements ISelectTopicService {

    @Autowired
    private SelectTopicMapper selectTopicMapper;

    @Override
    public ModelAndView topicList(ModelAndView modelAndView, SelectTopicVo vo) {
        Page<SelectTopicDto> page = new Page<>(vo.getPage(), vo.getPageSize());
        List<SelectTopicDto> list = selectTopicMapper.getTopicByPage(page, vo);
//        if (!CollectionUtils.isEmpty(list)){
//
//            for (SelectTopicDto dto:list){
//                if (!ObjectUtils.isEmpty(dto.getTeaAuditState())){
//                    dto.setTeaAuditStatusName(EnumSubState.toMap().get(dto.getTeaAuditState()));
//                }
//                if (!ObjectUtils.isEmpty(dto.getSubSelectStatus())){
//                    dto.setSubSelectStatusName(EnumSubSelectStatus.toMap().get(dto.getSubSelectStatus()));
//                }
//            }
//        }
        page.setRecords(list);
        modelAndView.addObject("page", page);
        modelAndView.addObject("topicList", list);
        return modelAndView;
    }
}
