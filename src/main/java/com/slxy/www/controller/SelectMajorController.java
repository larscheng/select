package com.slxy.www.controller;


import com.slxy.www.model.SelectDepartment;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.vo.SelectMajorVo;
import com.slxy.www.service.ISelectMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class SelectMajorController {

    @Autowired
    private ISelectMajorService selectMajorService;

    /**
     * 专业列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/majList")
    public ModelAndView majList(ModelAndView  modelAndView, SelectMajorVo vo) {
        return selectMajorService.majList(modelAndView,vo);
    }

    /**
     * 专业启用禁用、编辑
     * @param selectMajor
     * @return
     */
    @RequestMapping(value={"/majDisable","/majUpdate"})
    @ResponseBody
    public String majDisableAndUpdate(SelectMajor selectMajor) {
        return selectMajorService.majDisableAndUpdate(selectMajor);
    }

    /**
     * 专业添加初始化
     * @param modelAndView
     * @return
     */
    @RequestMapping("/majInitAdd")
    public ModelAndView majInitAdd(ModelAndView modelAndView) {
        return selectMajorService.majInitAdd(modelAndView);
    }

    /**
     * 专业编辑初始化
     * @param modelAndView
     * @return
     */
    @RequestMapping("/majInitUpdate")
    public ModelAndView majInitUpdate(ModelAndView modelAndView,SelectMajor selectMajor) {
        return selectMajorService.majInitUpdate(modelAndView,selectMajor);
    }

    @RequestMapping("/majAdd")
    @ResponseBody
    public String majAdd(SelectMajor selectMajor) {
        return selectMajorService.majAdd(selectMajor);
    }

    @RequestMapping("/majDelete")
    @ResponseBody
    public String majDelete(SelectMajor selectMajor) {
        return selectMajorService.majDel(selectMajor);
    }

    @RequestMapping("/majDeleteAll")
    @ResponseBody
    public String majDeleteAll(Integer[] selectedIDs) {
        return selectMajorService.majDelAll(selectedIDs);
    }
}

