package com.slxy.www.service;

import com.slxy.www.model.SelectUserBase;
import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.SelectUserBaseVo;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengql
 * @since 2018-01-06
 */
public interface ISelectUserBaseService extends IService<SelectUserBase> {

    ModelAndView userList(ModelAndView modelAndView, SelectUserBaseVo userBaseVo);

    String stuListAjax(SelectUserBaseVo userBaseVo);

    String stuAble(SelectUserBaseVo userBaseVo);

    String stuDelete(SelectUserBaseVo userBaseVo);

    ModelAndView stuInitAddAndUpdate(ModelAndView modelAndView, SelectUserBaseVo userBaseVo);

    String stuUpdate(SelectUserBase userBase);

    String stuAdd(SelectUserBase userBase);

    String stuDeleteAll(Integer[] selectedIDs);
}
