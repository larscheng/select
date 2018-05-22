package com.slxy.www.web;

import com.slxy.www.domain.po.SelectDepartment;
import com.slxy.www.domain.vo.SelectDepartmentVo;
import com.slxy.www.filter.LoginRequired;
import com.slxy.www.service.SelectDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
 * SelectDepartment 控制层
 *
 */

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
@Api(tags = "系别管理", description = "系别模块功能")
public class SelectDepartmentController {

    @Autowired
    private SelectDepartmentService selectDepartmentService;



    /**
     * 系别列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "系别列表")
    @RequestMapping(value = "/depList",method = RequestMethod.GET)
    public ModelAndView depList(ModelAndView modelAndView, SelectDepartmentVo vo) {
        return selectDepartmentService.depList(modelAndView,vo);
    }

    /**
     * 跳转添加
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "跳转添加系别页")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/depInitAdd",method = RequestMethod.GET)
    public ModelAndView depInitAdd(ModelAndView modelAndView) {
        modelAndView.setViewName("depmaj/depAdd");
        return modelAndView;
    }

    /**
     * 添加系别
     * @param selectDepartment
     * @return
     */
    @ApiOperation(value = "添加系别")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/depAdd",method = RequestMethod.POST)
    @ResponseBody
    public String depAdd(SelectDepartment selectDepartment) {
        return selectDepartmentService.depAdd(selectDepartment);
    }


    /**
     * 系别启禁用、编辑系别
     * @param selectDepartment
     * @return
     */
    @ApiOperation(value = "系别启禁用、编辑系别")
    @LoginRequired(value = "adm")
    @RequestMapping(value={"/depDisable","/depUpdate"},method =RequestMethod.POST )
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
    @ApiOperation(value = "初始化系别编辑并跳转页面")
    @LoginRequired(value = "adm")
    @RequestMapping( value = "/depInitUpdate", method = RequestMethod.GET)
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
    @ApiOperation(value = "系别查看")
    @RequestMapping(value = "/depFind",method = RequestMethod.GET)
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
    @ApiOperation(value = "系别删除")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/depDelete",method = RequestMethod.POST)
    @ResponseBody
    public String depDelete(SelectDepartment selectDepartment) {
        return selectDepartmentService.depDelete(selectDepartment);
    }


    /**
     * 系别批量删除
     * @param selectedIDs
     * @return
     */
    @ApiOperation(value = "系别批量删除")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/depDeleteAll",method = RequestMethod.POST)
    @ResponseBody
    public String depDeleteAll(Integer[] selectedIDs) {
        return selectDepartmentService.depDeleteAll(selectedIDs);
    }
}

