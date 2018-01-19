package com.slxy.www.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.mapper.SelectDepartmentMapper;
import com.slxy.www.model.SelectDepartment;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.enums.EnumEnOrDis;
import com.slxy.www.model.vo.SelectDepartmentVo;
import com.slxy.www.service.ISelectDepartmentService;
import com.slxy.www.service.impl.SelectMajorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhengql
 * @since 2018-01-07
 */
@Controller
@RequestMapping("/selectDepartment")
public class SelectDepartmentController {

    @Autowired
    private ISelectDepartmentService selectDepartmentService;



    /**
     * 系别列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/depList")
    public ModelAndView depList(ModelAndView modelAndView, SelectDepartmentVo vo) {
        return selectDepartmentService.depList(modelAndView,vo);
    }

    /**
     * 跳转添加
     * @param modelAndView
     * @return
     */
    @RequestMapping("/depInitAdd")
    public ModelAndView depInitAdd(ModelAndView modelAndView) {
        modelAndView.setViewName("depmaj/depAdd");
        return modelAndView;
    }

    /**
     * 添加系别
     * @param selectDepartment
     * @return
     */
    @RequestMapping("/depAdd")
    @ResponseBody
    public String depAdd(SelectDepartment selectDepartment) {
        return selectDepartmentService.depAdd(selectDepartment);
    }


    /**
     * 系别启禁用、编辑系别
     * @param selectDepartment
     * @return
     */
    @RequestMapping(value={"/depDisable","/depUpdate"})
    @ResponseBody
    public String depDisableAndUpdate(SelectDepartment selectDepartment) {
         return selectDepartmentService.depDisableAndUpdate(selectDepartment);
    }

    /**
     * 初始化系别编辑
     * @param id
     * @param modelAndView
     * @return
     */
    @RequestMapping("/depInitUpdate")
    public ModelAndView depInitUpdate(Integer id, ModelAndView modelAndView) {
        SelectDepartment selectDepartment = selectDepartmentService.selectById(id);
        modelAndView.setViewName("depmaj/depUpdate");
        modelAndView.addObject("dep", selectDepartment);
        return modelAndView;
    }


    /**
     * 系别查看
     * @param id
     * @param modelAndView
     * @return
     */
    @RequestMapping("/depFind")
    public ModelAndView depFind(Integer id, ModelAndView modelAndView) {
        SelectDepartment selectDepartment = selectDepartmentService.selectById(id);
        modelAndView.setViewName("depmaj/depFind");
        modelAndView.addObject("dep", selectDepartment);
        return modelAndView;
    }


    /***
     * 系别删除
     * @param selectDepartment
     * @return
     */
    @RequestMapping("/depDelete")
    @ResponseBody
    public String depDelete(SelectDepartment selectDepartment) {
        return selectDepartmentService.depDelete(selectDepartment);
    }


    /**
     * 系别批量删除
     * @param selectedIDs
     * @return
     */
    @RequestMapping("/depDeleteAll")
    @ResponseBody
    public String depDeleteAll(Integer[] selectedIDs) {
        return selectDepartmentService.depDeleteAll(selectedIDs);
    }
}

