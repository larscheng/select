package com.slxy.www.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.slxy.www.common.Constant;
import com.slxy.www.config.AutoFinishSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slxy.www.dao.ISelectProcessControlMapper;
import com.slxy.www.domain.po.SelectProcessControl;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

/**
 *
 * SelectProcessControl 表数据服务层接口实现类
 *
 */
@Service
public class SelectProcessControlService extends  ServiceImpl <ISelectProcessControlMapper, SelectProcessControl>  {
    @Autowired
    private ISelectProcessControlMapper selectProcessControlMapper;

    @Autowired
    private AutoFinishSelect autoFinishSelect;
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
        if (selectProcessControl.getId().equals(6)){
            autoFinishSelect.update(selectProcessControl.getProEndTime());
        }
        return this.updateById(selectProcessControl)? JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /***
     * 流程检测
     * @param id
     * @return
     */
    public String testPc(Integer id) {
        //取消流程控制
//        return JSONObject.toJSONString(Constant.SUCCESS);
        //流程控制执行逻辑
        List<SelectProcessControl> selectProcessControls = selectProcessControlMapper.selectPro();
        if (CollectionUtils.isEmpty(selectProcessControls)){
            return JSONObject.toJSONString(Constant.NOT_TIME);
        }
        for (SelectProcessControl pc: selectProcessControls
             ) {
            if (pc.getId().equals(id)){
                return JSONObject.toJSONString(Constant.SUCCESS);
            }
        }

        return JSONObject.toJSONString(Constant.NOT_TIME);
    }
}
