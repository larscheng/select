package com.slxy.www.controller;


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
@RequestMapping("/selectMarjor")
public class SelectMajorController {

    @Autowired
    private SelectMajorServiceImpl selectMajorService;

    @RequestMapping("/MajList")
    public ModelAndView userList(ModelAndView  modelAndView) {
        modelAndView.setViewName("depmaj/majList");
        modelAndView.addObject("majList", selectMajorService.selectList(null));
        return modelAndView;
    }

}

