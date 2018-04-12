package com.slxy.www.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.dao.ISelectDepartmentMapper;
import com.slxy.www.dao.ISelectUserBaseMapper;
import com.slxy.www.domain.dto.SelectMajorDTO;
import com.slxy.www.domain.po.SelectDepartment;
import com.slxy.www.domain.po.SelectUserBase;
import com.slxy.www.domain.vo.SelectMajorVo;
import com.slxy.www.enums.EnumEnOrDis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slxy.www.dao.ISelectMajorMapper;
import com.slxy.www.domain.po.SelectMajor;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * SelectMajor 表数据服务层接口实现类
 *
 */
@Service
public class SelectMajorService extends  ServiceImpl <ISelectMajorMapper, SelectMajor>  {

    @Autowired
    private ISelectMajorMapper selectMajorMapper;
    @Autowired
    private ISelectDepartmentMapper selectDepartmentMapper;
    @Autowired
    private ISelectUserBaseMapper selectUserBaseMapper;

    /**
     *  @Description    : 专业列表
     *  @Method_Name    : MajList
     *  @param_model	: [modelAndView, vo]
     *  @return         : org.springframework.web.servlet.ModelAndView
     *  @Creation_Date  : 2018/1/14 11:47
     *  @version        : v1.00
     *  @Author         : zhengql@senthink.com
     */

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

    public String majDisableAndUpdate(SelectMajor selectMajor) {

        //判断是否有状态值
        if (selectMajor.getMajStatus()!=null&&selectMajor.getMajStatus().equals(EnumEnOrDis.ENABLED.getValue())){
            //当为启用操作，需判断其所属系别是否被禁用
            SelectMajor major = selectMajorMapper.selectById(selectMajor.getId());
            SelectDepartment selectDepartment = selectDepartmentMapper.selectById(major.getDepId());
            if(selectDepartment.getDepStatus().equals(EnumEnOrDis.DISABLED.getValue())){
                return JSONObject.toJSONString(Constant.MAJ_ABLE_ERROR);
            }
        }
        if (selectMajor.getMajStatus()!=null&&selectMajor.getMajStatus().equals(EnumEnOrDis.DISABLED.getValue())){
            //当为禁用操作，判断是否有学生
            List<SelectUserBase> userBaseList = selectUserBaseMapper.selectList(new EntityWrapper<>(
                    new SelectUserBase().setStuMajorId(selectMajor.getId())
            ));
            if(!CollectionUtils.isEmpty(userBaseList)){
                return JSONObject.toJSONString(Constant.MAJ_DISABLE_ERROR);
            }
        }
        selectMajorMapper.updateById(selectMajor);
        return JSONObject.toJSONString(Constant.SUCCESS);
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

    public String majAdd(SelectMajor selectMajor) {
        //检查重复性
        SelectMajor major = new SelectMajor();
        major.setMajName(selectMajor.getMajName());
        List<SelectMajor> list = selectMajorMapper.selectList(new EntityWrapper<SelectMajor>(major));
        if (CollectionUtils.isEmpty(list)){
            //无重复
            selectMajor.setGmtCreate(new Date());
            if (this.insert(selectMajor)){
                return JSONObject.toJSONString(Constant.SUCCESS);
            }
        }
        return JSONObject.toJSONString(Constant.MAJ_NAME_EXIST);
    }

    /**
     * 删除
     * @param selectMajor
     * @return
     */

    public String majDel(SelectMajor selectMajor) {
        List<SelectUserBase> userBaseList = selectUserBaseMapper.selectList(new EntityWrapper<>(
                new SelectUserBase().setStuMajorId(selectMajor.getId())
        ));
        if(!CollectionUtils.isEmpty(userBaseList)){
            return JSONObject.toJSONString(Constant.MAJ_DELETE_ERROR);
        }
        if (this.deleteById(selectMajor)){
            return JSONObject.toJSONString(Constant.SUCCESS);
        }
        return JSONObject.toJSONString(Constant.ERROR);
    }


    /**
     * 批量删除
     * @param selectedIDs
     * @return
     */

    public String majDelAll(Integer[] selectedIDs) {
        for (Integer id : selectedIDs){
            SelectMajor major = selectMajorMapper.selectById(id);
            List<SelectUserBase> userBaseList = selectUserBaseMapper.selectList(new EntityWrapper<>(
                    new SelectUserBase().setStuMajorId(id)
            ));
            if(!CollectionUtils.isEmpty(userBaseList)){
                return JSONObject.toJSONString(Constant.MAJ_DELETE_ERROR_NAME+major.getMajName());
            }
        }


        if(this.deleteBatchIds(Arrays.asList(selectedIDs))){
            return JSONObject.toJSONString(Constant.SUCCESS);
        }
        return JSONObject.toJSONString(Constant.ERROR);
    }


}
