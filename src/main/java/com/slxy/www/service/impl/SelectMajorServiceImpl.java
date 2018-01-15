package com.slxy.www.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.mapper.SelectDepartmentMapper;
import com.slxy.www.mapper.SelectMajorMapper;
import com.slxy.www.model.SelectDepartment;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.dto.SelectMajorDTO;
import com.slxy.www.model.enums.EnumEnOrDis;
import com.slxy.www.model.enums.EnumYesOrNo;
import com.slxy.www.model.vo.SelectMajorVo;
import com.slxy.www.service.ISelectMajorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
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
public class SelectMajorServiceImpl extends ServiceImpl<SelectMajorMapper, SelectMajor> implements ISelectMajorService {

    @Autowired
    private SelectMajorMapper selectMajorMapper;
    @Autowired
    private SelectDepartmentMapper selectDepartmentMapper;


    /**
     *  @Description    : 专业列表
     *  @Method_Name    : MajList
     *  @param_model	: [modelAndView, vo]
     *  @return         : org.springframework.web.servlet.ModelAndView
     *  @Creation_Date  : 2018/1/14 11:47
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */
    @Override
    public ModelAndView majList(ModelAndView  modelAndView, SelectMajorVo vo) {
        modelAndView.setViewName("depmaj/majList");
        Page<SelectMajorDTO> page = new Page<>(vo.getPage(), vo.getPageSize());
        List<SelectMajorDTO> list = selectMajorMapper.getMajByPage(page, vo);
        page.setRecords(list);
        modelAndView.addObject("page", page);
        modelAndView.addObject("majList", list);
        return modelAndView;
    }


    /**
     *  @Description    : 专业启用禁用、编辑
     *  @Method_Name    : majDisableAndUpdate
     *  @param_model	: [selectMajor]
     *  @return         : java.lang.String
     *  @Creation_Date  : 2018/1/15 14:42
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */
    @Override
    public String majDisableAndUpdate(SelectMajor selectMajor) {

        //判断是否有状态值
        if (selectMajor.getMajStatus()!=null&&selectMajor.getMajStatus().equals(EnumEnOrDis.ENABLED.getValue())){
            //当为启用操作，需判断其所属系别是否被禁用
            SelectMajor major = selectMajorMapper.selectById(selectMajor.getId());
            SelectDepartment selectDepartment = selectDepartmentMapper.selectById(major.getDepId());
            if(selectDepartment.getDepStatus().equals(EnumEnOrDis.DISABLED.getValue())){
                return Constant.MAJ_ABLE_ERROR;
            }
        }
        selectMajorMapper.updateById(selectMajor);
        return Constant.SUCCESS;
    }


    /**
     *  @Description    : 专业添加初始化
     *  @Method_Name    : majInitAdd
     *  @param_model	: [modelAndView]
     *  @return         : org.springframework.web.servlet.ModelAndView
     *  @Creation_Date  : 2018/1/15 15:10
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */
    @Override
    public ModelAndView majInitAdd(ModelAndView modelAndView) {
        modelAndView.setViewName("depmaj/majAdd");
        SelectDepartment selectDepartment = new SelectDepartment();
        selectDepartment.setDepStatus(EnumEnOrDis.ENABLED.getValue());
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<>(selectDepartment));
        modelAndView.addObject("depNameList",depList);
        return modelAndView;
    }



    /**
     * 专业编辑初始化
     * @param modelAndView
     * @param selectMajor
     * @return
     */
    @Override
    public ModelAndView majInitUpdate(ModelAndView modelAndView, SelectMajor selectMajor) {
        selectMajor = selectMajorMapper.selectById(selectMajor.getId());
       modelAndView.setViewName("depmaj/majUpdate");
        if (!ObjectUtils.isEmpty(selectMajor)){
            modelAndView.addObject("major",selectMajor);
       }
        SelectDepartment selectDepartment = new SelectDepartment();
        selectDepartment.setDepStatus(EnumEnOrDis.ENABLED.getValue());
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<>(selectDepartment));
        modelAndView.addObject("depNameList",depList);
       return modelAndView;
    }

    /***
     * 专业添加
     * @param selectMajor
     * @return
     */
    @Override
    public String majAdd(SelectMajor selectMajor) {
        //检查重复性
        SelectMajor major = new SelectMajor();
        major.setMajName(selectMajor.getMajName());
        List<SelectMajor> list = selectMajorMapper.selectList(new EntityWrapper<SelectMajor>(major));
        if (CollectionUtils.isEmpty(list)){
            //无重复
            selectMajor.setGmtCreate(new Date());
            if (this.insert(selectMajor)){
                return Constant.SUCCESS;
            }
        }
        return Constant.MAJ_NAME_EXIST;
    }

    /**
     * 删除
     * @param selectMajor
     * @return
     */
    @Override
    public String majDel(SelectMajor selectMajor) {
        if (this.deleteById(selectMajor)){
            return Constant.SUCCESS;
        }
        return Constant.ERROR;
    }


    /**
     * 批量删除
     * @param selectedIDs
     * @return
     */
    @Override
    public String majDelAll(Integer[] selectedIDs) {
        if(this.deleteBatchIds(Arrays.asList(selectedIDs))){
            return Constant.SUCCESS;
        }
        return Constant.ERROR;
    }


}
