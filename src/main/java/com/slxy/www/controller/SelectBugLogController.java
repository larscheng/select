package com.slxy.www.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.slxy.www.common.Constant;
import com.slxy.www.mapper.SelectBugLogMapper;
import com.slxy.www.model.SelectDepartment;
import com.slxy.www.model.dto.SelectBugLogDto;
import com.slxy.www.model.po.SelectBugLog;
import com.slxy.www.model.vo.SelectDepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql123
 * @since 2018-04-03
 */
@Controller
@RequestMapping("/selectBugLog")
public class SelectBugLogController {
    @Autowired
    private SelectBugLogMapper selectBugLogMapper;



    /**
     * 初始化bug列表
     * @param modelAndView
     * @return
     */
    @RequestMapping("/bugInitList")
    public ModelAndView bugList(ModelAndView modelAndView) {
        List<SelectBugLogDto> bugLog = selectBugLogMapper.selectTenLog();
        modelAndView.addObject("bugList",bugLog);
        modelAndView.setViewName("bugModule/bugList");
        return modelAndView;
    }

    /**
     * 添加bug
     * @param selectBugLog
     * @return
     */
    @RequestMapping("/bugAdd")
    @ResponseBody
    public String bugAdd(SelectBugLog selectBugLog) {
        selectBugLog.setGmtCreate(new Date());
        return selectBugLogMapper.insert(selectBugLog)>0 ? Constant.SUCCESS:Constant.ERROR;
    }
}

