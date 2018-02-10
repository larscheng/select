package com.slxy.www.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.SelectTopic;
import com.slxy.www.mapper.SelectTopicMapper;
import com.slxy.www.model.SelectUserBase;
import com.slxy.www.model.dto.SelectMajorDTO;
import com.slxy.www.model.dto.SelectTopicDto;
import com.slxy.www.model.enums.EnumEnOrDis;
import com.slxy.www.model.enums.EnumSubSelectStatus;
import com.slxy.www.model.enums.EnumSubState;
import com.slxy.www.model.enums.EnumUserType;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.model.vo.SelectTopicVo;
import com.slxy.www.service.ISelectTopicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private SelectUserBaseMapper selectUserBaseMapper;


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
        List<SelectUserBase> teaList = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
                .and("user_type = {0}", EnumUserType.TEACHER.getValue()).and("user_status = {0}", EnumEnOrDis.ENABLED.getValue()));
        List<SelectUserBase> stuList = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
                .and("user_type = {0}", EnumUserType.STUDENT.getValue()).and("user_status = {0}", EnumEnOrDis.ENABLED.getValue()));
        modelAndView.addObject("page", page);
        modelAndView.addObject("topicList", list);
        modelAndView.addObject("teaList", teaList);
        modelAndView.addObject("stuList", stuList);
        return modelAndView;
    }

    /**
     * 异步生成学生选题列表
     * @param vo
     * @return
     */
    @Override
    public String stuTopicAjaxList(SelectTopicVo vo) {
        Page<SelectTopicDto> page = new Page<>(vo.getPage(), vo.getPageSize());
        List<SelectTopicDto> list = selectTopicMapper.getTopicByPage(page, vo);
//        page.setRecords(list);
        Map<String,Object> map = new HashMap<>();
        map.put("topicList",list);
        map.put("page",page);
        String object = JSONObject.toJSONString(map);
        return object;
    }




}
