package com.slxy.www.controller;


import com.slxy.www.service.impl.SelectDepartmentServiceImpl;
import com.slxy.www.service.impl.SelectMajorServiceImpl;
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
 * @since 2018-01-07
 */
@Controller
@RequestMapping("/selectDepartment")
public class SelectDepartmentController {

    @Autowired
    private SelectDepartmentServiceImpl selectDepartmentService;
    @Autowired
    private SelectMajorServiceImpl selectMarjorService;

    @RequestMapping("/depList")
    public ModelAndView userList(ModelAndView  modelAndView) {
        modelAndView.setViewName("depList");
        modelAndView.addObject("depList", selectDepartmentService.selectList(null));
        return modelAndView;
    }
}

