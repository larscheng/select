package com.slxy.www.controller;


import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.SelectUserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-06
 */
@Controller
@RequestMapping("/selectUserBase")
@SessionAttributes(value = {"user"})
public class SelectUserBaseController {

    @Autowired
    private SelectUserBaseMapper selectUserBaseMapper;
    @RequestMapping("/login")
    public ModelAndView index(SelectUserBase userBase,ModelAndView  modelAndView) {
        SelectUserBase selectUserBase = selectUserBaseMapper.selectOne((userBase));
        if (ObjectUtils.isEmpty(selectUserBase)||!userBase.getUserPassword().equals(selectUserBase.getUserPassword())){
            modelAndView.setViewName("redirect:/login.jsp");
            modelAndView.addObject("msg","请重新登录");
            return modelAndView;
        }
        modelAndView.setViewName("home");
        modelAndView.addObject("user",selectUserBase);
        modelAndView.addObject("userList", selectUserBaseMapper.selectUserBaseList());
        return modelAndView;
    }

    @RequestMapping("/userList")
    public ModelAndView userList(ModelAndView  modelAndView) {
        modelAndView.setViewName("home");
        modelAndView.addObject("userList", selectUserBaseMapper.selectUserBaseList());
        return modelAndView;
    }

}

