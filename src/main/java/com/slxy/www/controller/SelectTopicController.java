package com.slxy.www.controller;


import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.model.vo.SelectTopicVo;
import com.slxy.www.service.ISelectTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
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

    /**
     * 选题信息列表 ，根据vo不同权限不同
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/topicList")
    public ModelAndView stuList(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("topicModule/topicList");
        return selectTopicService.topicList(modelAndView,vo);
    }

    /***
     * 异步生成学生选题信息列表
     * @param vo
     * @return
     */
    @RequestMapping("/stuTopicAjaxList")
    @ResponseBody
    public String stuTopicAjaxList(SelectTopicVo vo) {
        return selectTopicService.stuTopicAjaxList(vo);
    }

    /**
     * 教师查看全部选题记录
     * @param modelAndView
     * @param vo
     * @return
     */
//    @RequestMapping("/teaTopicList")
//    public ModelAndView teaTopicList(ModelAndView  modelAndView, SelectTopicVo vo) {
//        modelAndView.setViewName("/topicModule/teaTopicList");
//        return selectTopicService.topicList(modelAndView,vo);
//    }
    /**
     * 待审核选题列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/noTopicList")
    public ModelAndView noTopicList(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("topicModule/noTopicList");
        return selectTopicService.topicList(modelAndView,vo);
    }

    /**
     * 选题详情
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/topicDetails")
    public ModelAndView topicDetails(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("topicModule/topicDetails");
        return selectTopicService.topicDetails(modelAndView,vo);
    }

}

