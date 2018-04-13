package com.slxy.www.web;

import com.slxy.www.domain.po.SelectProcessControl;
import com.slxy.www.service.SelectProcessControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(tags = "流程控制管理", description = "流程控制模块功能")
public class SelectProcessControlController {
    @Autowired
    private SelectProcessControlService selectProcessControlService;


    /***
     *流程控制列表
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "流程控制列表", notes = "")
    @RequestMapping(value = "/pcList",method = RequestMethod.GET)
    public ModelAndView pcList(ModelAndView  modelAndView) {
        modelAndView.setViewName("pcModule/pcList");
        return selectProcessControlService.pcList(modelAndView);
    }


    /***
     *更新流程控制
     * @param selectProcessControl
     * @return
     */
    @ApiOperation(value = "更新流程", notes = "")
    @RequestMapping(value = "/updatePc",method = RequestMethod.POST)
    @ResponseBody
    public String updatePc(SelectProcessControl selectProcessControl) {
        return selectProcessControlService.updatePc(selectProcessControl);
    }
}

