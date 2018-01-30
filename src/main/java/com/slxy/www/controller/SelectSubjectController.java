package com.slxy.www.controller;


import com.slxy.www.model.SelectSubject;
import com.slxy.www.model.enums.EnumSubState;
import com.slxy.www.model.vo.SelectMajorVo;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.service.ISelectSubjectService;
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
 * @author zhengql
 * @since 2018-01-25
 */
@Controller
@RequestMapping("/selectSubject")
public class SelectSubjectController {
    @Autowired
    private ISelectSubjectService selectSubjectService;

    /**
     * 未审核列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/unSubList")
    public ModelAndView unSubList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/unAuditedList");
        vo.setAdmAuditState(EnumSubState.Untreated.getValue());
        return selectSubjectService.subList(modelAndView,vo);
    }

    /**
     * 已审核列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/subList")
    public ModelAndView subList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/auditedList");
        return selectSubjectService.subList(modelAndView,vo);
    }


    @RequestMapping("/unSubListAjax")
    @ResponseBody
    public String unSubListAjax(SelectSubjectVo vo) {
        vo.setAdmAuditState(EnumSubState.Untreated.getValue());
        return selectSubjectService.subListAjax(vo);
    }


    @RequestMapping("/subListAjax")
    @ResponseBody
    public String subListAjax(SelectSubjectVo vo) {
        return selectSubjectService.subListAjax(vo);
    }


    /**
     * 审核
     * @param vo
     * @return
     */
    @RequestMapping("/subAudited")
    @ResponseBody
    public String subAudited(SelectSubjectVo vo) {
        return selectSubjectService.subAudited(vo);
    }

    /***
     * 批量通过
     * @param selectedIDs
     * @return
     */
    @RequestMapping("/subSuccessAll")
    @ResponseBody
    public String subSuccessAll(Integer[] selectedIDs) {
        return selectSubjectService.subSuccessAll(selectedIDs);
    }


    /**
     * 未审核详情
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/subDetail")
    public ModelAndView subDetail(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/subDetails");
        return selectSubjectService.subDetail(modelAndView,vo);
    }

    /***
     * 已审核详情
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/subjectDetail")
    public ModelAndView subjectDetail(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/subjectDetails");
        return selectSubjectService.subDetail(modelAndView,vo);
    }
}

