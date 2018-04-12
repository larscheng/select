package com.slxy.www.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.slxy.www.common.Constant;
import org.springframework.stereotype.Service;

import com.slxy.www.dao.ISelectProcessControlMapper;
import com.slxy.www.domain.po.SelectProcessControl;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 * SelectProcessControl 表数据服务层接口实现类
 *
 */
@Service
public class SelectProcessControlService extends  ServiceImpl <ISelectProcessControlMapper, SelectProcessControl>  {
    /***
     * 查询流程列表
     * @param modelAndView
     * @return
     */

    public ModelAndView pcList(ModelAndView modelAndView) {
        List<SelectProcessControl> processControls = selectList(new EntityWrapper<SelectProcessControl>());
        modelAndView.addObject("pcList",processControls);
        return modelAndView;
    }

    /***
     * 修改流程时间
     * @param selectProcessControl
     * @return
     */

    public String updatePc(SelectProcessControl selectProcessControl) {
        SelectProcessControl processControl = this.selectById(selectProcessControl.getId());
        if (ObjectUtils.isEmpty(processControl)){
            return JSONObject.toJSONString(Constant.PARAM_ERROR);
        }
        if (ObjectUtils.isEmpty(selectProcessControl.getProStartTime())||ObjectUtils.isEmpty(selectProcessControl.getProEndTime())){
            return JSONObject.toJSONString(Constant.PARAM_ERROR);
        }
        return this.updateById(selectProcessControl)? JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }
}
