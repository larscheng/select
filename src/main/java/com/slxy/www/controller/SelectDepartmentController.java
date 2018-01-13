package com.slxy.www.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.mapper.SelectDepartmentMapper;
import com.slxy.www.model.SelectDepartment;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.enums.EnumEnOrDis;
import com.slxy.www.model.vo.SelectDepartmentVo;
import com.slxy.www.service.ISelectDepartmentService;
import com.slxy.www.service.impl.SelectMajorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-07
 */
@Controller
@RequestMapping("/selectDepartment")
public class SelectDepartmentController {

    @Autowired
    private ISelectDepartmentService selectDepartmentService;
    @Autowired
    private SelectMajorServiceImpl selectMajorService;

    @Autowired
    private SelectDepartmentMapper selectDepartmentMapper;

    @RequestMapping("/depList")
    public ModelAndView userList(ModelAndView modelAndView, SelectDepartmentVo vo) {
        modelAndView.setViewName("depmaj/depList");
        Page<SelectDepartment> page = new Page<>(vo.getPage(), vo.getPageSize());
        List<SelectDepartment> list = selectDepartmentMapper.getDepByPage(page, vo);
        page.setRecords(list);
        modelAndView.addObject("depList", list);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @RequestMapping("/depInitAdd")
    public ModelAndView depInitAdd(ModelAndView modelAndView) {
        modelAndView.setViewName("depmaj/depAdd");
        return modelAndView;
    }


    @RequestMapping("/depAdd")
    @ResponseBody
    public String depAdd(SelectDepartment selectDepartment) {
        //校验重复性
        SelectDepartment department = new SelectDepartment();
        department.setDepName(selectDepartment.getDepName());
        List<SelectDepartment> departments = selectDepartmentService.selectList(
                new EntityWrapper<>(department));
        if (departments.size() < 1) {
            selectDepartment.setGmtCreate(new Date());
            if (selectDepartmentService.insert(selectDepartment)) {
                //添加成功
                return Constant.SUCCESS;
            }
        }
        return Constant.DEP_EXIST;


    }

    @RequestMapping("/depDisable")
    @ResponseBody
    public String depDisable(SelectDepartment selectDepartment) {
        if (selectDepartment.getDepStatus() != null && selectDepartment.getDepStatus().equals(0)) {
            //是否有启用中的所属专业
            SelectMajor selectMajor = new SelectMajor();
            selectMajor.setDepId(selectDepartment.getId());
            selectMajor.setMajStatus(1);
            List<SelectMajor> selectMajors = selectMajorService.selectList(new EntityWrapper<SelectMajor>(selectMajor));
            if (!CollectionUtils.isEmpty(selectMajors)) {
                return Constant.DEP_DISABLE_ERROR;
            }
        }
        selectDepartmentService.updateById(selectDepartment);
        return Constant.SUCCESS;
    }


    @RequestMapping("/depInitUpdate")
    public ModelAndView depInitUpdate(Integer id, ModelAndView modelAndView) {
        SelectDepartment selectDepartment = selectDepartmentService.selectById(id);
        modelAndView.setViewName("depmaj/depUpdate");
        modelAndView.addObject("dep", selectDepartment);
        return modelAndView;
    }

    @RequestMapping("/depDelete")
    @ResponseBody
    public String depDelete(SelectDepartment selectDepartment) {
        //是否有启用中的所属专业
        SelectMajor selectMajor = new SelectMajor();
        selectMajor.setDepId(selectDepartment.getId());
        selectMajor.setMajStatus(EnumEnOrDis.ENABLED.getValue());
        List<SelectMajor> selectMajors = selectMajorService.selectList(new EntityWrapper<SelectMajor>(selectMajor));
        if (!CollectionUtils.isEmpty(selectMajors)) {
            return Constant.DEP_DELETE_ERROR;
        }
        selectDepartmentService.deleteById(selectDepartment);
        return Constant.SUCCESS;
    }

    @RequestMapping("/depDeleteAll")
    @ResponseBody
    public String depDeleteAll(Integer[] selectedIDs) {
        //是否有启用中的所属专业
        for (Integer id : selectedIDs) {
            SelectDepartment selectDepartment =selectDepartmentService.selectById(id);
            SelectMajor selectMajor = new SelectMajor();
            selectMajor.setDepId(id);
            selectMajor.setMajStatus(EnumEnOrDis.ENABLED.getValue());
            List<SelectMajor> selectMajors = selectMajorService.selectList(new EntityWrapper<SelectMajor>(selectMajor));
            if (!CollectionUtils.isEmpty(selectMajors)) {
                return Constant.DEP_DELETE_ERROR_NAME+selectDepartment.getDepName();
            }
        }
        List<Integer> ids = Arrays.asList(selectedIDs);
        selectDepartmentService.deleteBatchIds(ids);
        return Constant.SUCCESS;
    }
}

