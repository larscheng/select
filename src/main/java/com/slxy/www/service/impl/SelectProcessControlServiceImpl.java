package com.slxy.www.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.slxy.www.common.Constant;
import com.slxy.www.mapper.SelectProcessControlMapper;
import com.slxy.www.model.po.SelectProcessControl;
import com.slxy.www.service.ISelectProcessControlService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengql123
 * @since 2018-03-27
 */
@Service
public class SelectProcessControlServiceImpl extends ServiceImpl<SelectProcessControlMapper, SelectProcessControl> implements ISelectProcessControlService {
    /***
     * 查询流程列表
     * @param modelAndView
     * @return
     */
    @Override
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
    @Override
    public String updatePc(SelectProcessControl selectProcessControl) {
        SelectProcessControl processControl = this.selectById(selectProcessControl.getId());
        if (ObjectUtils.isEmpty(processControl)){
            return Constant.PARAM_ERROR;
        }
        if (ObjectUtils.isEmpty(selectProcessControl.getProStartTime())||ObjectUtils.isEmpty(selectProcessControl.getProEndTime())){
            return Constant.PARAM_ERROR;
        }
        return this.updateById(selectProcessControl)?Constant.SUCCESS:Constant.ERROR;
    }
}
