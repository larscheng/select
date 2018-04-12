package com.slxy.www.web;

import com.slxy.www.domain.po.SelectProcessControl;
import com.slxy.www.service.SelectProcessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * SelectProcessControl 控制层
 *
 */

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
    private SelectProcessControlService selectProcessControlService;


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

