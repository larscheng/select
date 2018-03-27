package com.slxy.www.controller;


import com.slxy.www.model.enums.EnumSubState;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.service.ISelectProcessControlService;
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
 * @since 2018-03-27
 */
@Controller
@RequestMapping("/selectProcessControl")
public class SelectProcessControlController {
    @Autowired
    private ISelectProcessControlService selectProcessControlService;




    @RequestMapping("/pcList")
    public ModelAndView pcList(ModelAndView  modelAndView) {
        modelAndView.setViewName("pcModule/pcList");
        return modelAndView;
    }
}

