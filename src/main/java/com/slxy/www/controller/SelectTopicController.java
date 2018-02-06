package com.slxy.www.controller;


import com.slxy.www.model.SelectTopic;
import com.slxy.www.model.enums.EnumUserType;
import com.slxy.www.model.vo.SelectTopicVo;
import com.slxy.www.model.vo.SelectUserBaseVo;
import com.slxy.www.service.ISelectTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql123
 * @since 2018-02-04
 */
@Controller
@RequestMapping("/selectTopic")
public class SelectTopicController {
    @Autowired
    private ISelectTopicService selectTopicService;


    @RequestMapping("/stuTopicList")
    public ModelAndView stuList(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("/topicModule/stuTopicList");
        return selectTopicService.topicList(modelAndView,vo);
    }
}

