package com.slxy.www.controller;


import com.slxy.www.model.enums.EnumSubState;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.service.ISelectSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /***
     * 历届论文列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/subAllList")
    public ModelAndView subAllList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("CountModule/subAllList");
        return selectSubjectService.subList(modelAndView,vo);
    }

    /***
     * 异步生成未审核论文列表
     * @param vo
     * @return
     */
    @RequestMapping("/unSubListAjax")
    @ResponseBody
    public String unSubListAjax(SelectSubjectVo vo) {
        vo.setAdmAuditState(EnumSubState.Untreated.getValue());
        return selectSubjectService.subListAjax(vo);
    }

    /***
     * 异步生成论文列表
     * @param vo
     * @return
     */
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



    //****************************************************教师**********************************************************//
    //****************************************************教师**********************************************************//
    //****************************************************教师**********************************************************//

    /**
     * 教师个人题目列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/mySubList")
    public ModelAndView mySubList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/mySubList");
        return selectSubjectService.mySubList(modelAndView,vo);
    }

    /**
     * 异步生成我的题目列表
     * @param vo
     * @return
     */
    @RequestMapping("/mySubListAjax")
    @ResponseBody
    public String mySubListAjax(SelectSubjectVo vo) {
        return selectSubjectService.mySubListAjax(vo);
    }

    /***
     * 题目添加初始化
     * @param modelAndView
     * @return
     */
    @RequestMapping("/initSubAdd")
    public ModelAndView initSubAdd(ModelAndView  modelAndView) {
        modelAndView.setViewName("subjectModule/subAdd");
        return selectSubjectService.initSubAdd(modelAndView);
    }

    /**
     * 题目添加
     * @param vo
     * @return
     */
    @RequestMapping("/subAdd")
    @ResponseBody
    public String subAdd(@RequestParam("subFile") MultipartFile file, SelectSubjectVo vo, HttpServletRequest request) {
        return selectSubjectService.subAdd(file,vo,request);
    }


    /***
     * 所有通过审核的题目列表
     * @param modelAndView
     * @return
     */
    @RequestMapping("/optionalList")
    public ModelAndView optionalList(ModelAndView  modelAndView,SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/optionalList");
        vo.setAdmAuditState(EnumSubState.SUCCESS.getValue());
        return selectSubjectService.mySubList(modelAndView,vo);
    }


    /**
     * 异步生成 所有通过审核的题目列表
     * @param vo
     * @return
     */
    @RequestMapping("/optionalListAjax")
    @ResponseBody
    public String optionalListAjax(SelectSubjectVo vo) {
        vo.setAdmAuditState(EnumSubState.SUCCESS.getValue());
        return selectSubjectService.mySubListAjax(vo);
    }


    /**
     * 学生模板下载
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/subFileDown")
    public void stuFileDown(HttpServletRequest request,HttpServletResponse response,String fileName) throws Exception {

        selectSubjectService.downSubFile(request,response,fileName);
    }


    //****************************************************stu**********************************************************//
    //****************************************************stu**********************************************************//
    //****************************************************stu**********************************************************//


    /**
     * 学生可见的题目列表 本系、本届
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/stuSubList")
    public ModelAndView stuSubList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/stuSubList");
        return selectSubjectService.stuSubList(modelAndView,vo);
    }

    /**
     * 异步生成 所有通过审核的题目列表
     * @param vo
     * @return
     */
    @RequestMapping("/stuSubListAjax")
    @ResponseBody
    public String stuSubListAjax(SelectSubjectVo vo) {
        vo.setAdmAuditState(EnumSubState.SUCCESS.getValue());
        return selectSubjectService.stuSubListAjax(vo);
    }

    /**
     * 学生选题：添加选题记录，题目选题状态变【审核中】
     * @param vo
     * @return
     */
    @RequestMapping("/stuSelect")
    @ResponseBody
    public String stuSelect(SelectSubjectVo vo) {
        return selectSubjectService.stuSelect(vo);
    }

}

