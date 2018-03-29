package com.slxy.www.controller;


import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.enums.EnumSubState;
import com.slxy.www.model.po.SelectProcessControl;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.service.ISelectProcessControlService;
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
 * @since 2018-03-27
 */
@Controller
@RequestMapping("/selectProcessControl")
public class SelectProcessControlController {
    @Autowired
    private ISelectProcessControlService selectProcessControlService;


    /***
     *流程控制列表
     * @param modelAndView
     * @return
     */
    @RequestMapping("/pcList")
    public ModelAndView pcList(ModelAndView  modelAndView) {
        modelAndView.setViewName("pcModule/pcList");
        return selectProcessControlService.pcList(modelAndView);
    }


    /***
     *更新流程控制
     * @param selectProcessControl
     * @return
     */
    @RequestMapping("/updatePc")
    @ResponseBody
    public String updatePc(SelectProcessControl selectProcessControl) {
        return selectProcessControlService.updatePc(selectProcessControl);
    }
}

