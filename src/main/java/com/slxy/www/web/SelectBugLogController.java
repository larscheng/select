package com.slxy.www.web;

import com.slxy.www.common.Constant;
import com.slxy.www.dao.ISelectBugLogMapper;
import com.slxy.www.domain.dto.SelectBugLogDto;
import com.slxy.www.domain.po.SelectBugLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 *
 * SelectBugLog 控制层
 *
 */

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
@Api(tags = "bug管理", description = "bug页面功能")
public class SelectBugLogController {
    @Autowired
    private ISelectBugLogMapper selectBugLogMapper;



    /**
     * 初始化bug列表
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "初始化bug列表", notes = "")
    @RequestMapping(value = "/bugInitList",method = RequestMethod.GET)
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
    @ApiOperation(value = "添加bug", notes = "")
    @RequestMapping(value = "/bugAdd",method = RequestMethod.POST)
    @ResponseBody
    public String bugAdd(@RequestBody SelectBugLog selectBugLog) {
        selectBugLog.setGmtCreate(new Date());
        return selectBugLogMapper.insert(selectBugLog)>0 ? Constant.SUCCESS:Constant.ERROR;
    }
}

