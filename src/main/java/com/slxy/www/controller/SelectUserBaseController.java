package com.slxy.www.controller;


import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.SelectUserBase;
import com.slxy.www.model.vo.SelectUserBaseVo;
import com.slxy.www.model.enums.EnumUserType;
import com.slxy.www.service.ISelectUserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql
 * @since 2018-01-06
 */
@Controller
@RequestMapping("/selectUserBase")
@SessionAttributes(value = {"sessionUser","userType"})
public class SelectUserBaseController {

    @Autowired
    private ISelectUserBaseService selectUserBaseService;
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
        modelAndView.setViewName("main");
        modelAndView.addObject("sessionUser",selectUserBase);
        modelAndView.addObject("userType",selectUserBase.getUserType());
        return modelAndView;
    }


    @RequestMapping("/userList")
    public ModelAndView userList(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("test");
        return selectUserBaseService.userList(modelAndView,userBaseVo);
    }

    /******************************************    学生   *****************************************************/
    /**
     * 学生列表初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuList")
    public ModelAndView stuList(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        userBaseVo.setUserType(EnumUserType.STUDENT.getValue());
        modelAndView.setViewName("/stuModule/stuList");
        return selectUserBaseService.userList(modelAndView,userBaseVo);
    }

    /**
     * 学生参数查询列表 异步生成列表
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuListAjax")
    @ResponseBody
    public String stuListAjax(SelectUserBaseVo userBaseVo) {
        userBaseVo.setUserType(EnumUserType.STUDENT.getValue());
        return selectUserBaseService.stuListAjax(userBaseVo);
    }

    /**
     * 学生启用禁用
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuAble")
    @ResponseBody
    public String stuAble(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.stuAble(userBaseVo);
    }

    /***
     * 学生删除
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuDelete")
    @ResponseBody
    public String stuDelete(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.stuDelete(userBaseVo);
    }


    @RequestMapping("/stuDeleteAll")
    @ResponseBody
    public String stuDeleteAll(Integer[] selectedIDs) {
        return selectUserBaseService.stuDeleteAll(selectedIDs);
    }


    /***
     * 学生修改初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuInitUpdate")
    public ModelAndView stuInitUpdate(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/stuModule/stuUpdate");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /**
     * 学生修改
     * @param userBase
     * @return
     */
    @RequestMapping("/stuUpdate")
    @ResponseBody
    public String stuUpdate(SelectUserBase userBase) {
        return selectUserBaseService.stuUpdate(userBase);
    }

    /**
     * 学生添加初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/stuInitAdd")
    public ModelAndView stuInitAdd(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {

        modelAndView.setViewName("/stuModule/stuAdd");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /**
     * 学生添加
     * @param userBase
     * @return
     */
    @RequestMapping("/stuAdd")
    @ResponseBody
    public String stuAdd(SelectUserBase userBase) {
        return selectUserBaseService.stuAdd(userBase);
    }


    /***
     * 学生导入
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/stuUpload", method = RequestMethod.POST)
    @ResponseBody
    public String stuUpload(HttpServletRequest request) throws IOException {
        return selectUserBaseService.stuUpload(request);
    }

    @RequestMapping(value = "/initClass", method = RequestMethod.POST)
    @ResponseBody
    public String initClass(SelectUserBase userBase) {
        return selectUserBaseService.initClass(userBase);
    }


    /*********************************************************************************************************/
    /******************************************   教师   *****************************************************/
    /*********************************************************************************************************/


    /**
     * 教师列表初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaList")
    public ModelAndView teaList(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        userBaseVo.setUserType(EnumUserType.TEACHER.getValue());
        modelAndView.setViewName("/teaModule/teaList");
        return selectUserBaseService.userList(modelAndView,userBaseVo);
    }

    /**
     * 异步生成教师列表
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaListAjax")
    @ResponseBody
    public String teaListAjax(SelectUserBaseVo userBaseVo) {
        userBaseVo.setUserType(EnumUserType.TEACHER.getValue());
        return selectUserBaseService.teaListAjax(userBaseVo);
    }

    /**
     * 教师启用禁用
     * @param userBase
     * @return
     */
    @RequestMapping("/teaAble")
    @ResponseBody
    public String teaAble(SelectUserBase userBase) {
        return selectUserBaseService.teaAble(userBase);
    }

    /**
     * 教师添加初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaInitAdd")
    public ModelAndView teaInitAdd(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/teaModule/teaAdd");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /**
     * 教师添加
     * @param userBase
     * @return
     */
    @RequestMapping("/teaAdd")
    @ResponseBody
    public String teaAdd(SelectUserBase userBase) {
        return selectUserBaseService.teaAdd(userBase);
    }

    /***
     * 教师编辑初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaInitUpdate")
    public ModelAndView teaInitUpdate(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/teaModule/teaUpdate");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /***
     * 教师编辑
     * @param userBase
     * @return
     */
    @RequestMapping("/teaUpdate")
    @ResponseBody
    public String teaUpdate(SelectUserBase userBase) {
        return selectUserBaseService.teaUpdate(userBase);
    }

    /***
     * 教师删除
     * @param userBase
     * @return
     */
    @RequestMapping("/teaDelete")
    @ResponseBody
    public String teaDelete(SelectUserBase userBase) {
        return selectUserBaseService.teaDelete(userBase);
    }

    /**
     * 教师批量删除
     * @param selectedIDs
     * @return
     */
    @RequestMapping("/teaDeleteAll")
    @ResponseBody
    public String teaDeleteAll(Integer[] selectedIDs) {
        return selectUserBaseService.teaDeleteAll(selectedIDs);
    }

    /**
     * 教师详情
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @RequestMapping("/teaDetails")
    public ModelAndView teaDetails(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("teaModule/teaDetails");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }


    /***
     * 教师导入
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/teaUpload", method = RequestMethod.POST)
    @ResponseBody
    public String teaUpload(HttpServletRequest request) throws IOException {
        return selectUserBaseService.teaUpload(request);
    }



































































}

