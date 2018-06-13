package com.slxy.www.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.slxy.www.common.Constant;
import com.slxy.www.dao.ISelectUserBaseMapper;
import com.slxy.www.domain.po.ChangePs;
import com.slxy.www.domain.po.SelectUserBase;
import com.slxy.www.domain.vo.SelectUserBaseVo;
import com.slxy.www.enums.EnumEnOrDis;
import com.slxy.www.enums.EnumUserType;
import com.slxy.www.filter.LoginRequired;
import com.slxy.www.service.SelectUserBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql
 * @since 2018-01-06
 */
@Controller
@Api(tags = "用户管理", description = "用户模块功能")
@RequestMapping("/selectUserBase")
@SessionAttributes(value = {"sessionUser","userType","sessionIp"})
public class SelectUserBaseController {

    @Autowired
    private SelectUserBaseService selectUserBaseService;
    @Autowired
    private ISelectUserBaseMapper selectUserBaseMapper;



    @ApiOperation(value = "登录", notes = "")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView index(SelectUserBase userBase, ModelAndView  modelAndView) {
        SelectUserBase user = new SelectUserBase().setUserCode(userBase.getUserCode());
        SelectUserBase selectUserBase = selectUserBaseMapper.selectOne((user));
        if (ObjectUtils.isEmpty(selectUserBase)){
            modelAndView.setViewName("../../login");
            modelAndView.addObject("msg","该用户不存在，请重新登录");
            return modelAndView;
        }else if (selectUserBase.getUserStatus().equals(EnumEnOrDis.DISABLED.getValue())){
            modelAndView.setViewName("../../login");
            modelAndView.addObject("msg","该用户已被禁用，请重新登录");
            return modelAndView;
        }else if (!userBase.getUserPassword().equals(selectUserBase.getUserPassword())){
            modelAndView.setViewName("../../login");
            modelAndView.addObject("msg","账号密码错误，请重新登录");
            return modelAndView;
        }
        if (userBase.getUserPassword().equals(Constant.USER_PASSWORD)){
            modelAndView.setViewName("changePs");
        }else {
            modelAndView.setViewName("main");
        }
        modelAndView.addObject("sessionUser",selectUserBase);
        modelAndView.addObject("sessionIp",this.getIp());
        modelAndView.addObject("userType",selectUserBase.getUserType());
        return modelAndView;
    }


    /**
     * 获取IP地址
     * @return
     */
    private   String getIp(){
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (java.net.SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements())
        {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
                    .nextElement();
//            System.out.println(netInterface.getName());
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements())
            {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address)
                {
                    if(ip.getHostAddress().equals("127.0.0.1")){
                        continue;
                    }
//                    System.out.println("/u672c/u673a/u7684IP = " + ip.getHostAddress());
                    return ip.getHostAddress();
                }
            }
        }
        return "127.0.0.1";
    }

    @ApiOperation(value = "注销", notes = "")
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session, ModelAndView  modelAndView) {
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();

//        modelAndView.setViewName("main");
        modelAndView.setViewName("../../login");

        return modelAndView;
    }


    @ApiOperation(value = "校验账号", notes = "")
    @RequestMapping(value = "/checkCode" ,method = RequestMethod.POST)
    @ResponseBody
    public String checkCode(ChangePs changePs){
        return selectUserBaseService.checkCode(changePs);
    }

    /**
     * 管理员列表
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "获取管理员列表", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/admList",method = RequestMethod.GET)
    public ModelAndView admList(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("adminModule/admList");
        userBaseVo.setUserType(EnumUserType.ADMIN.getValue());
        return selectUserBaseService.userList(modelAndView,userBaseVo);
    }

    /**
     * 初始化添加
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "初始化管理员添加", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/initAddAdmin",method = RequestMethod.GET)
    public ModelAndView initAddAdmin(ModelAndView  modelAndView) {
        modelAndView.setViewName("adminModule/admAdd");
        return modelAndView;
    }


    /**
     * 管理员添加
     * @param userBase
     * @return
     */
    @ApiOperation(value = "管理员添加", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/admAdd",method = RequestMethod.POST)
    @ResponseBody
    public String admAdd(SelectUserBase userBase) {

        return selectUserBaseService.admAdd(userBase);
    }



    /***
     * admin删除
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "管理员删除", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/admDelete",method = RequestMethod.POST)
    @ResponseBody
    public String admDelete(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.admDelete(userBaseVo);
    }

    /***
     * 管理员批量删除
     * @param selectedIDs
     * @return
     */
    @ApiOperation(value = "管理员批量删除", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/admDeleteAll",method = RequestMethod.POST)
    @ResponseBody
    public String admDeleteAll(Integer[] selectedIDs) {
        return selectUserBaseService.admDeleteAll(selectedIDs);
    }

    /**
     * adm启用禁用
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "管理员启用禁用", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/admAble",method = RequestMethod.POST)
    @ResponseBody
    public String admAble(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.admAble(userBaseVo);
    }


    /***
     * 管理员个人信息变更
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "管理员信息修改（本人）", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/admSelfInfo",method = RequestMethod.GET)
    public ModelAndView admSelfInfo(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/adminModule/admSelfInfo");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }


    /**
     * adm修改
     * @param userBase
     * @return
     */
    @ApiOperation(value = "管理员修改", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/admUpdate",method = RequestMethod.POST)
    @ResponseBody
    public String admUpdate(SelectUserBase userBase) {
        return selectUserBaseService.stuUpdate(userBase);
    }


    /******************************************    学生   *****************************************************/
    /**
     * 学生列表初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "学生列表初始化", notes = "")
    @RequestMapping(value = "/stuList",method = RequestMethod.GET)
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

    @ApiOperation(value = "异步生成学生列表", notes = "")
    @RequestMapping(value = "/stuListAjax",method = RequestMethod.POST)
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
    @ApiOperation(value = "学生启用禁用", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/stuAble",method = RequestMethod.POST)
    @ResponseBody
    public String stuAble(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.stuAble(userBaseVo);
    }

    /***
     * 学生删除
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "学生删除", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/stuDelete",method = RequestMethod.POST)
    @ResponseBody
    public String stuDelete(SelectUserBaseVo userBaseVo) {
        return selectUserBaseService.stuDelete(userBaseVo);
    }

    /***
     * 学生批量删除
     * @param selectedIDs
     * @return
     */
    @ApiOperation(value = "教师批量删除", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/stuDeleteAll",method = RequestMethod.POST)
    @ResponseBody
    public String stuDeleteAll(Integer[] selectedIDs) {
        return selectUserBaseService.stuDeleteAll(selectedIDs);
    }



    /***
     * 学生个人信息变更
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "学生个人信息变更", notes = "")
    @LoginRequired(value = "stu")
    @RequestMapping(value = "/stuSelfInfo",method = RequestMethod.GET)
    public ModelAndView stuSelfInfo(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!ObjectUtils.isEmpty(session)){
            SelectUserBase userBase = (SelectUserBase) session.getAttribute("sessionUser");
            if (!ObjectUtils.isEmpty(userBase)){
                userBaseVo.setId(userBase.getId());
            }
        }
        modelAndView.setViewName("/stuModule/stuSelfInfo");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }



    /***
     * 学生修改初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "学生修改初始化", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/stuInitUpdate",method = RequestMethod.GET)
    public ModelAndView stuInitUpdate(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/stuModule/stuUpdate");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /**
     * 学生修改
     * @param userBase
     * @return
     */
    @ApiOperation(value = "学生修改", notes = "")
//    @LoginRequired(value = "adm")
    @RequestMapping(value = "/stuUpdate",method = RequestMethod.POST)
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
    @ApiOperation(value = "学生添加初始化", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/stuInitAdd",method = RequestMethod.GET)
    public ModelAndView stuInitAdd(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {

        modelAndView.setViewName("/stuModule/stuAdd");
        return selectUserBaseService.stuInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /**
     * 学生添加
     * @param userBase
     * @return
     */
    @ApiOperation(value = "学生添加", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/stuAdd",method = RequestMethod.POST)
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
    @ApiOperation(value = "学生导入", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/stuUpload", method = RequestMethod.POST)
    @ResponseBody
    public String stuUpload(HttpServletRequest request) throws IOException {
        return selectUserBaseService.stuUpload(request);
    }

    /**
     * 根据专业查询班级数
     * @param userBase
     * @return
     */
    @ApiOperation(value = "根据专业查询班级数", notes = "")
    @RequestMapping(value = "/initClass", method = RequestMethod.POST)
    @ResponseBody
    public String initClass(SelectUserBase userBase) {
        return selectUserBaseService.initClass(userBase);
    }


    /**
     * 学生模板下载
     * @param request
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "学生模板下载", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/stuFileDown",method = RequestMethod.GET)
    public void stuFileDown(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String fileName= "select_students.xls";
        selectUserBaseService.down(request,response,fileName);
    }

    /*********************************************************************************************************/
    /******************************************   教师   *****************************************************/
    /*********************************************************************************************************/

    /**
     * 教师模板下载
     * @param request
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "教师模板下载", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/teaFileDown",method = RequestMethod.GET)
    public void teaFileDown(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String fileName= "select_teachers.xls";
        selectUserBaseService.down(request,response,fileName);
    }
    /**
     * 教师列表初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "教师列表初始化", notes = "")
    @RequestMapping(value = "/teaList",method = RequestMethod.GET)
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
    @ApiOperation(value = "异步生成教师列表", notes = "")
//    @LoginRequired(value = "adm")
    @RequestMapping(value = "/teaListAjax",method = RequestMethod.POST)
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
    @ApiOperation(value = "教师启用禁用", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/teaAble",method = RequestMethod.POST)
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
    @ApiOperation(value = "教师添加初始化", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/teaInitAdd",method = RequestMethod.GET)
    public ModelAndView teaInitAdd(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/teaModule/teaAdd");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /**
     * 教师添加
     * @param userBase
     * @return
     */
    @ApiOperation(value = "教师添加", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/teaAdd",method = RequestMethod.POST)
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
    @ApiOperation(value = "教师个人信息编辑（本人）", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/teaSelfInfo",method = RequestMethod.GET)
    public ModelAndView teaSelfInfo(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!ObjectUtils.isEmpty(session)){
            SelectUserBase userBase = (SelectUserBase) session.getAttribute("sessionUser");
            if (!ObjectUtils.isEmpty(userBase)){
                userBaseVo.setId(userBase.getId());
            }
        }
        modelAndView.setViewName("/teaModule/teaSelfInfo");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }


    /***
     * 教师编辑初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @ApiOperation(value = "教师编辑初始化", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/teaInitUpdate",method = RequestMethod.GET)
    public ModelAndView teaInitUpdate(ModelAndView  modelAndView,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("/teaModule/teaUpdate");
        return selectUserBaseService.teaInitAddAndUpdate(modelAndView,userBaseVo);
    }

    /***
     * 教师编辑
     * @param userBase
     * @return
     */
    @ApiOperation(value = "教师修改", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/teaUpdate",method = RequestMethod.POST)
    @ResponseBody
    public String teaUpdate(SelectUserBase userBase) {
        return selectUserBaseService.teaUpdate(userBase);
    }

    /***
     * 教师删除
     * @param userBase
     * @return
     */
    @ApiOperation(value = "教师删除", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/teaDelete",method = RequestMethod.POST)
    @ResponseBody
    public String teaDelete(SelectUserBase userBase) {
        return selectUserBaseService.teaDelete(userBase);
    }

    /**
     * 教师批量删除
     * @param selectedIDs
     * @return
     */
    @ApiOperation(value = "教师批量删除", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/teaDeleteAll",method = RequestMethod.POST)
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
    @ApiOperation(value = "教师详情", notes = "")
    @RequestMapping(value = "/teaDetails",method = RequestMethod.GET)
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
    @ApiOperation(value = "教师导入", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/teaUpload", method = RequestMethod.POST)
    @ResponseBody
    public String teaUpload(HttpServletRequest request) throws IOException {
        return selectUserBaseService.teaUpload(request);
    }



































































}

