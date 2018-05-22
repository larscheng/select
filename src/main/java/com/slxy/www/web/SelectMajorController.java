package com.slxy.www.web;

import com.slxy.www.domain.po.SelectMajor;
import com.slxy.www.domain.vo.SelectMajorVo;
import com.slxy.www.filter.LoginRequired;
import com.slxy.www.service.SelectMajorService;
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
 * SelectMajor 控制层
 *
 */

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql
 * @since 2018-01-07
 */
@Controller
@RequestMapping("/selectMajor")
@Api(tags = "专业管理", description = "专业模块功能")
public class SelectMajorController {

    @Autowired
    private SelectMajorService selectMajorService;

    /**
     * 专业列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "专业列表", notes = "")
    @RequestMapping(value = "/majList",method = RequestMethod.GET)
    public ModelAndView majList(ModelAndView  modelAndView, SelectMajorVo vo) {
        return selectMajorService.majList(modelAndView,vo);
    }

    /**
     * 专业启用禁用、编辑
     * @param selectMajor
     * @return
     */
    @ApiOperation(value = "专业启用禁用、编辑", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value={"/majDisable","/majUpdate"},method = RequestMethod.POST)
    @ResponseBody
    public String majDisableAndUpdate(SelectMajor selectMajor) {
        return selectMajorService.majDisableAndUpdate(selectMajor);
    }

    /**
     * 专业添加初始化
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "专业添加初始化", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/majInitAdd",method = RequestMethod.GET)
    public ModelAndView majInitAdd(ModelAndView modelAndView) {
        return selectMajorService.majInitAdd(modelAndView);
    }

    /**
     * 专业编辑初始化
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "专业编辑初始化", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/majInitUpdate",method = RequestMethod.GET)
    public ModelAndView majInitUpdate(ModelAndView modelAndView, SelectMajor selectMajor) {
        return selectMajorService.majInitUpdate(modelAndView,selectMajor);
    }


    /**
     * 专业查看
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "专业查看", notes = "")
    @RequestMapping(value = "/majFind",method = RequestMethod.GET)
    public ModelAndView majFind(ModelAndView modelAndView,SelectMajor selectMajor) {
        modelAndView = selectMajorService.majInitUpdate(modelAndView,selectMajor);
        modelAndView.setViewName("/depmaj/majFind");
        return modelAndView;
    }

    @ApiOperation(value = "专业添加", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/majAdd",method = RequestMethod.POST)
    @ResponseBody
    public String majAdd(SelectMajor selectMajor) {
        return selectMajorService.majAdd(selectMajor);
    }

    @ApiOperation(value = "专业删除", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/majDelete",method = RequestMethod.POST)
    @ResponseBody
    public String majDelete(SelectMajor selectMajor) {
        return selectMajorService.majDel(selectMajor);
    }

    @ApiOperation(value = "专业批量删除", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/majDeleteAll",method = RequestMethod.POST)
    @ResponseBody
    public String majDeleteAll(Integer[] selectedIDs) {
        return selectMajorService.majDelAll(selectedIDs);
    }
}

