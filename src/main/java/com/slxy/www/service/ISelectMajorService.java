package com.slxy.www.service;

import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.vo.SelectMajorVo;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengql
 * @since 2018-01-07
 */
public interface ISelectMajorService extends IService<SelectMajor> {


    ModelAndView majList(ModelAndView  modelAndView, SelectMajorVo vo);

    String majDisableAndUpdate(SelectMajor selectMajor);

    ModelAndView majInitAdd(ModelAndView modelAndView);

    ModelAndView majInitUpdate(ModelAndView modelAndView, SelectMajor selectMajor);

    String majAdd(SelectMajor selectMajor);

    String majDel(SelectMajor selectMajor);

    String majDelAll(Integer[] selectedIDs);
}
