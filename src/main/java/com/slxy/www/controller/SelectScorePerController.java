package com.slxy.www.controller;



import com.slxy.www.model.po.SelectScorePer;
import com.slxy.www.service.ISelectScorePerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql123
 * @since 2018-03-29
 */
@Controller
@RequestMapping("/selectScorePer")
public class SelectScorePerController {

    @Autowired
    private ISelectScorePerService selectScorePerService;


    /***
     * 成绩比例列表
     * @param modelAndView
     * @return
     */
    @RequestMapping("/scoreList")
    public ModelAndView scoreList(ModelAndView  modelAndView) {
        modelAndView.setViewName("adminModule/scoreList");
        return selectScorePerService.scoreList(modelAndView);
    }

    /***
     * 成绩比例模块添加初始化
     * @param modelAndView
     * @return
     */
    @RequestMapping("/addInitScore")
    public ModelAndView addInitScore(ModelAndView  modelAndView) {
        modelAndView.setViewName("adminModule/scoreAdd");
        return modelAndView;
    }

    /***
     * 添加比例
     * @param selectScorePer
     * @return
     */
    @RequestMapping("/addScore")
    @ResponseBody
    public String addScore(SelectScorePer selectScorePer) {
        return selectScorePerService.addScore(selectScorePer);
    }

    /***
     * 成绩比例模块编辑初始化
     * @param modelAndView
     * @return
     */
    @RequestMapping("/updateInitScore")
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
    @RequestMapping("/updateScore")
    @ResponseBody
    public String updateScore(SelectScorePer selectScorePer) {
        return selectScorePerService.updateScore(selectScorePer);
    }

    /***
     * 删除比例
     * @param id
     * @return
     */
    @RequestMapping("/delScore")
    @ResponseBody
    public String delScore(@RequestParam("id")String id) {
        return selectScorePerService.delScore(Integer.parseInt(id));
    }


    /**
     * 比例批量删除
     * @param selectedIDs
     * @return
     */
    @RequestMapping("/delAllScore")
    @ResponseBody
    public String delAllScore(Integer[] selectedIDs) {
        return selectScorePerService.delAllScore(selectedIDs);
    }

}

