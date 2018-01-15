package com.slxy.www.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.slxy.www.common.Constant;
import com.slxy.www.mapper.SelectDepartmentMapper;
import com.slxy.www.model.SelectDepartment;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.enums.EnumEnOrDis;
import com.slxy.www.model.enums.EnumYesOrNo;
import com.slxy.www.model.vo.SelectDepartmentVo;
import com.slxy.www.service.ISelectDepartmentService;
import com.slxy.www.service.ISelectMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengql
 * @since 2018-01-07
 */
@Service
public class SelectDepartmentServiceImpl extends ServiceImpl<SelectDepartmentMapper, SelectDepartment> implements ISelectDepartmentService {

    @Autowired
    private SelectDepartmentMapper selectDepartmentMapper;

    @Autowired
    private ISelectMajorService selectMajorService;


    /**
     *  @Description    : 系别列表
     *  @Method_Name    : depList
     *  @param_model	: [modelAndView, vo]
     *  @return         : org.springframework.web.servlet.ModelAndView
     *  @Creation_Date  : 2018/1/14 11:19
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */
    @Override
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
    @Override
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
                return Constant.SUCCESS;
            }
        }
        return Constant.DEP_EXIST;
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
    @Override
    public String depDisableAndUpdate(SelectDepartment selectDepartment) {
        //判断参数中是否有状态值
        if (selectDepartment.getDepStatus() != null && selectDepartment.getDepStatus().equals(0)) {
            //是否有启用中的所属专业
            SelectMajor selectMajor = new SelectMajor();
            selectMajor.setDepId(selectDepartment.getId());
            selectMajor.setMajStatus(EnumEnOrDis.ENABLED.getValue());
            List<SelectMajor> selectMajors = selectMajorService.selectList(new EntityWrapper<SelectMajor>(selectMajor));
            if (!CollectionUtils.isEmpty(selectMajors)) {
                return Constant.DEP_DISABLE_ERROR;
            }
        }
        selectDepartmentMapper.updateById(selectDepartment);
        return Constant.SUCCESS;
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
    @Override
    public String depDelete(SelectDepartment selectDepartment) {
        //是否有启用中的所属专业
        SelectMajor selectMajor = new SelectMajor();
        selectMajor.setDepId(selectDepartment.getId());
        selectMajor.setMajStatus(EnumEnOrDis.ENABLED.getValue());
        List<SelectMajor> selectMajors = selectMajorService.selectList(new EntityWrapper<SelectMajor>(selectMajor));
        if (!CollectionUtils.isEmpty(selectMajors)) {
            return Constant.DEP_DELETE_ERROR;
        }
        selectDepartmentMapper.deleteById(selectDepartment);
        return Constant.SUCCESS;
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
    @Override
    public String depDeleteAll(Integer[] selectedIDs) {
        //是否有启用中的所属专业
        for (Integer id : selectedIDs) {
            SelectDepartment selectDepartment =selectDepartmentMapper.selectById(id);
            SelectMajor selectMajor = new SelectMajor();
            selectMajor.setDepId(id);
            selectMajor.setMajStatus(EnumEnOrDis.ENABLED.getValue());
            List<SelectMajor> selectMajors = selectMajorService.selectList(new EntityWrapper<SelectMajor>(selectMajor));
            if (!CollectionUtils.isEmpty(selectMajors)) {
                return Constant.DEP_DELETE_ERROR_NAME+selectDepartment.getDepName();
            }
        }
        if (this.deleteBatchIds(Arrays.asList(selectedIDs))){
            return Constant.SUCCESS;
        }
        return Constant.ERROR;
    }
}
