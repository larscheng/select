package com.slxy.www.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.domain.po.SelectMajor;
import com.slxy.www.domain.vo.SelectDepartmentVo;
import com.slxy.www.enums.EnumEnOrDis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slxy.www.dao.ISelectDepartmentMapper;
import com.slxy.www.domain.po.SelectDepartment;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * SelectDepartment 表数据服务层接口实现类
 *
 */
@Service
public class SelectDepartmentService extends  ServiceImpl <ISelectDepartmentMapper, SelectDepartment>  {

    @Autowired
    private ISelectDepartmentMapper selectDepartmentMapper;

    @Autowired
    private SelectMajorService selectMajorService;


    /**
     *  @Description    : 系别列表
     *  @Method_Name    : depList
     *  @param_model	: [modelAndView, vo]
     *  @return         : org.springframework.web.servlet.ModelAndView
     *  @Creation_Date  : 2018/1/14 11:19
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */

    public ModelAndView depList(ModelAndView modelAndView, SelectDepartmentVo vo) {
        modelAndView.setViewName("depmaj/depList");
        Page<SelectDepartment> page = new Page<>(vo.getPage(), vo.getPageSize());
        List<SelectDepartment> list = selectDepartmentMapper.getDepByPage(page, vo);
        page.setRecords(list);
        modelAndView.addObject("depList", list);
        modelAndView.addObject("page", page);
        return modelAndView;
    }



    /**
     *  @Description    : 添加系别
     *  @Method_Name    : depAdd
     *  @param_model	: [selectDepartment]
     *  @return         : java.lang.String
     *  @Creation_Date  : 2018/1/14 11:20
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */

    public String depAdd(SelectDepartment selectDepartment) {
        //校验重复性
        SelectDepartment department = new SelectDepartment();
        department.setDepName(selectDepartment.getDepName());
        List<SelectDepartment> departments = selectDepartmentMapper.selectList(
                new EntityWrapper<>(department));
        if (CollectionUtils.isEmpty(departments)) {
            selectDepartment.setGmtCreate(new Date());
            if (selectDepartmentMapper.insert(selectDepartment)> EnumEnOrDis.DISABLED.getValue()) {
                //添加成功
                return JSONObject.toJSONString(Constant.SUCCESS);
            }
        }
        return JSONObject.toJSONString(Constant.DEP_EXIST);
    }

    /**
     *  @Description    : 系别启用禁用、系别编辑
     *  @Method_Name    : depDisableAndUpdate
     *  @param_model	: [selectDepartment]
     *  @return         : java.lang.String
     *  @Creation_Date  : 2018/1/14 11:32
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */

    public String depDisableAndUpdate(SelectDepartment selectDepartment) {
        //判断参数中是否有状态值
        if (selectDepartment.getDepStatus() != null && selectDepartment.getDepStatus().equals(0)) {
            //是否有启用中的所属专业
            SelectMajor selectMajor = new SelectMajor();
            selectMajor.setDepId(selectDepartment.getId());
            selectMajor.setMajStatus(EnumEnOrDis.ENABLED.getValue());
            List<SelectMajor> selectMajors = selectMajorService.selectList(new EntityWrapper<SelectMajor>(selectMajor));
            if (!CollectionUtils.isEmpty(selectMajors)) {
                return JSONObject.toJSONString(Constant.DEP_DISABLE_ERROR);
            }
        }
        selectDepartmentMapper.updateById(selectDepartment);
        return JSONObject.toJSONString(Constant.SUCCESS);
    }


    /**
     *  @Description    : 删除系别
     *  @Method_Name    : depDelete
     *  @param_model	: [selectDepartment]
     *  @return         : java.lang.String
     *  @Creation_Date  : 2018/1/14 11:35
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */

    public String depDelete(SelectDepartment selectDepartment) {
        //是否有启用中的所属专业
        SelectMajor selectMajor = new SelectMajor();
        selectMajor.setDepId(selectDepartment.getId());
        selectMajor.setMajStatus(EnumEnOrDis.ENABLED.getValue());
        List<SelectMajor> selectMajors = selectMajorService.selectList(new EntityWrapper<SelectMajor>(selectMajor));
        if (!CollectionUtils.isEmpty(selectMajors)) {
            return JSONObject.toJSONString(Constant.DEP_DELETE_ERROR);
        }
        selectDepartmentMapper.deleteById(selectDepartment);
        return JSONObject.toJSONString(Constant.SUCCESS);
    }



    /**
     *  @Description    : 系别批量删除
     *  @Method_Name    : depDeleteAll
     *  @param_model	: [selectedIDs]
     *  @return         : java.lang.String
     *  @Creation_Date  : 2018/1/14 11:37
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */

    public String depDeleteAll(Integer[] selectedIDs) {
        //是否有启用中的所属专业
        for (Integer id : selectedIDs) {
            SelectDepartment selectDepartment =selectDepartmentMapper.selectById(id);
            SelectMajor selectMajor = new SelectMajor();
            selectMajor.setDepId(id);
            selectMajor.setMajStatus(EnumEnOrDis.ENABLED.getValue());
            List<SelectMajor> selectMajors = selectMajorService.selectList(new EntityWrapper<SelectMajor>(selectMajor));
            if (!CollectionUtils.isEmpty(selectMajors)) {
                return JSONObject.toJSONString(Constant.DEP_DELETE_ERROR_NAME+selectDepartment.getDepName());
            }
        }
        if (this.deleteBatchIds(Arrays.asList(selectedIDs))){
            return JSONObject.toJSONString(Constant.SUCCESS);
        }
        return JSONObject.toJSONString(Constant.ERROR);
    }
}
