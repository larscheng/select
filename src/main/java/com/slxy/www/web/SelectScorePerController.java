package com.slxy.www.web;

import com.slxy.www.domain.po.SelectScorePer;
import com.slxy.www.service.SelectScorePerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * SelectScorePer 控制层
 *
 */

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql123
 * @since 2018-03-29
 */
@Api(tags = "成绩比例管理", description = "成绩比例模块功能")
@Controller
@RequestMapping("/selectScorePer")
public class SelectScorePerController {

    @Autowired
    private SelectScorePerService selectScorePerService;


    /***
     * 成绩比例列表
     * @param modelAndView
     * @return
     */

    @ApiOperation(value = "成绩比例列表", notes = "")
    @RequestMapping(value = "/scoreList",method = RequestMethod.GET)
    public ModelAndView scoreList(ModelAndView  modelAndView) {
        modelAndView.setViewName("adminModule/scoreList");
        return selectScorePerService.scoreList(modelAndView);
    }

    /***
     * 成绩比例模块添加初始化
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "成绩比例添加初始化", notes = "")
    @RequestMapping(value = "/addInitScore",method = RequestMethod.GET)
    public ModelAndView addInitScore(ModelAndView  modelAndView) {
        modelAndView.setViewName("adminModule/scoreAdd");
        return modelAndView;
    }

    /***
     * 添加比例
     * @param selectScorePer
     * @return
     */

    @ApiOperation(value = "添加比例", notes = "不公开")
    @RequestMapping(value = "/addScore",method = RequestMethod.POST)
    @ResponseBody
    public String addScore(SelectScorePer selectScorePer) {
        return selectScorePerService.addScore(selectScorePer);
    }

    /***
     * 成绩比例模块编辑初始化
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "成绩比例编辑初始化", notes = "")
    @RequestMapping(value = "/updateInitScore",method = RequestMethod.GET)
    public ModelAndView updateInitScore(ModelAndView  modelAndView,SelectScorePer selectScorePer) {
        modelAndView.setViewName("adminModule/scoreUpdate");
        modelAndView.addObject("score",selectScorePerService.selectById(selectScorePer.getId()));
        return modelAndView;
    }

    /***
     * 编辑比例
     * @param selectScorePer
     * @return
     */

    @ApiOperation(value = "编辑成绩比例", notes = "")
    @RequestMapping(value = "/updateScore",method = RequestMethod.POST)
    @ResponseBody
    public String updateScore(SelectScorePer selectScorePer) {
        return selectScorePerService.updateScore(selectScorePer);
    }

    /***
     * 删除比例
     * @param id
     * @return
     */
    @ApiOperation(value = "删除比例", notes = "不公开")
    @RequestMapping(value = "/delScore",method = RequestMethod.POST)
    @ResponseBody
    public String delScore(@RequestParam("id")String id) {
        return selectScorePerService.delScore(Integer.parseInt(id));
    }


    /**
     * 比例批量删除
     * @param selectedIDs
     * @return
     */
    @ApiOperation(value = "比例批量删除", notes = "不用开")
    @RequestMapping(value = "/delAllScore",method = RequestMethod.POST)
    @ResponseBody
    public String delAllScore(Integer[] selectedIDs) {
        return selectScorePerService.delAllScore(selectedIDs);
    }

}

