package com.slxy.www.service;


import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.po.SelectProcessControl;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengql123
 * @since 2018-03-27
 */
public interface ISelectProcessControlService extends IService<SelectProcessControl> {

    ModelAndView pcList(ModelAndView modelAndView);

    String updatePc(SelectProcessControl selectProcessControl);
}
