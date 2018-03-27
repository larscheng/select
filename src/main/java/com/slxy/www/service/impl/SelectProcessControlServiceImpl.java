package com.slxy.www.service.impl;


import com.slxy.www.mapper.SelectProcessControlMapper;
import com.slxy.www.model.po.SelectProcessControl;
import com.slxy.www.service.ISelectProcessControlService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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

    @Override
    public ModelAndView pcList(ModelAndView modelAndView) {
        return null;
    }
}
