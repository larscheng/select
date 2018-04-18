package com.slxy.www.web;



/**
 *
 * SelectBugLog 控制层
 *
 */

import com.alibaba.fastjson.JSONObject;
import com.slxy.www.common.Constant;
import com.slxy.www.dao.ISelectUserBaseMapper;
import com.slxy.www.domain.po.ChangePs;
import com.slxy.www.domain.po.SelectUserBase;
import com.slxy.www.enums.EnumEnOrDis;
import com.slxy.www.service.SelectJavaMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql123
 * @since 2018-04-03
 */
@Controller
@Api(tags = "基础控制器", description = "跳转页面")
//@SessionAttributes(value = {"sessionUser","userType","sessionIp"})
public class BaseController {
    @Autowired
    private ISelectUserBaseMapper selectUserBaseMapper;

    @Autowired
    private SelectJavaMailService selectJavaMailService;

    @ApiOperation(value = "登录页跳转", notes = "")
    @RequestMapping(value = {"/",""} ,method = RequestMethod.GET)
    public String index(HttpSession session){
        SelectUserBase sessionUser = (SelectUserBase)session.getAttribute("sessionUser");
        if (!ObjectUtils.isEmpty(sessionUser)){
            return "main";
        }
        return "login";}

    @ApiOperation(value = "首页跳转", notes = "")
    @RequestMapping(value = "/index" ,method = RequestMethod.GET)
    public String index2(HttpSession session){
        SelectUserBase sessionUser = (SelectUserBase)session.getAttribute("sessionUser");
        if (ObjectUtils.isEmpty(sessionUser)){
            return "login";
        }
        return "main";
    }



    @ApiOperation(value = "登录", notes = "")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(SelectUserBase userBase, HttpSession session){
        SelectUserBase sessionUser = (SelectUserBase)session.getAttribute("sessionUser");
        if (!ObjectUtils.isEmpty(sessionUser)){
//            return JSONObject.toJSONString("请先将已登录的用户注销");
            return JSONObject.toJSONString(Constant.SUCCESS);
        }
        SelectUserBase user = new SelectUserBase().setUserCode(userBase.getUserCode());
        SelectUserBase selectUserBase = selectUserBaseMapper.selectOne((user));
        if (ObjectUtils.isEmpty(selectUserBase)){
            return JSONObject.toJSONString("该用户不存在，请重新登录");
        }else if (selectUserBase.getUserStatus().equals(EnumEnOrDis.DISABLED.getValue())){

            return JSONObject.toJSONString("该用户已被禁用，请重新登录");

        }else if (!userBase.getUserPassword().equals(selectUserBase.getUserPassword())){

            return JSONObject.toJSONString("账号密码错误，请重新登录");
        }

        session.setAttribute("sessionUser",selectUserBase);
        session.setAttribute("sessionIp",this.getIp());
        session.setAttribute("userType",selectUserBase.getUserType());
        if (userBase.getUserPassword().equals(Constant.USER_PASSWORD)){
            return JSONObject.toJSONString("NO");
        }else {
            return JSONObject.toJSONString(Constant.SUCCESS);
        }


    }




//    public ModelAndView index(SelectUserBase userBase, ModelAndView  modelAndView,HttpSession session) {
//        SelectUserBase sessionUser = (SelectUserBase)session.getAttribute("sessionUser");
//        if (!ObjectUtils.isEmpty(sessionUser)){
//            modelAndView.setViewName("/login");
//            modelAndView.addObject("msg","请先将已登录的用户注销");
//            return modelAndView;
//        }
//        SelectUserBase user = new SelectUserBase().setUserCode(userBase.getUserCode());
//        SelectUserBase selectUserBase = selectUserBaseMapper.selectOne((user));
//        if (ObjectUtils.isEmpty(selectUserBase)){
//            modelAndView.setViewName("/login");
//            modelAndView.addObject("msg","该用户不存在，请重新登录");
//            return modelAndView;
//        }else if (selectUserBase.getUserStatus().equals(EnumEnOrDis.DISABLED.getValue())){
//            modelAndView.setViewName("/login");
//            modelAndView.addObject("msg","该用户已被禁用，请重新登录");
//            return modelAndView;
//        }else if (!userBase.getUserPassword().equals(selectUserBase.getUserPassword())){
//            modelAndView.setViewName("/login");
//            modelAndView.addObject("msg","账号密码错误，请重新登录");
//            return modelAndView;
//        }
//        if (userBase.getUserPassword().equals(Constant.USER_PASSWORD)){
//            modelAndView.setViewName("changePs");
//        }else {
//            modelAndView.setViewName("main");
//        }
////        modelAndView.addObject("sessionUser",selectUserBase);
////        modelAndView.addObject("sessionIp",this.getIp());
////        modelAndView.addObject("userType",selectUserBase.getUserType());
//        session.setAttribute("sessionUser",selectUserBase);
//        session.setAttribute("sessionIp",this.getIp());
//        session.setAttribute("userType",selectUserBase.getUserType());
//        return modelAndView;
//    }


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
        modelAndView.setViewName("/login");

        return modelAndView;
    }





    @ApiOperation(value = "修改密码跳转", notes = "")
    @RequestMapping(value = "/initChangePs" ,method = RequestMethod.GET)
    public String initChangePs(){return "common/changePass";}


    @ApiOperation(value = "修改密码", notes = "")
    @RequestMapping(value = "/changePs" ,method = RequestMethod.POST)
    @ResponseBody
    public String changePs(ChangePs changePs){
        SelectUserBase userBase  = new SelectUserBase();
        if (ObjectUtils.isEmpty(changePs.getUserCode())){
            userBase = selectUserBaseMapper.selectById(changePs.getUserId());
        }else {
            userBase = selectUserBaseMapper.selectOne(new SelectUserBase().setUserCode(changePs.getUserCode()));
        }
        if (ObjectUtils.isEmpty(userBase)){
            return JSONObject.toJSONString("用户不存在");
        }
        SelectUserBase selectUserBase = new SelectUserBase()
                .setId(userBase.getId())
                .setUserPassword(changePs.getNewPassWord())
                .setUserMail(changePs.getUserMail())
                .setUserPhone(ObjectUtils.isEmpty(changePs.getUserPhone())?userBase.getUserPhone():changePs.getUserPhone())
                .setUserQq(ObjectUtils.isEmpty(changePs.getUserQq())?userBase.getUserQq():changePs.getUserQq())
                .setSixCode("");
        //发邮件通知
        selectJavaMailService.sendHtmlMail(changePs.getUserMail(),userBase.getUserName(),changePs.getNewPassWord(),"密码重置");

        return selectUserBaseMapper.updateById(selectUserBase)>0 ? JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);

    }

    @ApiOperation(value = "校验原始密码", notes = "")
    @RequestMapping(value = "/checkPs" ,method = RequestMethod.POST)
    @ResponseBody
    public String checkPs(ChangePs changePs){
        SelectUserBase userBase  = new SelectUserBase();
        if (ObjectUtils.isEmpty(changePs.getUserCode())){
            userBase = selectUserBaseMapper.selectById(changePs.getUserId());
        }else {
            userBase = selectUserBaseMapper.selectOne(new SelectUserBase().setUserCode(changePs.getUserCode()));
        }
        Map<String,Boolean> map = new HashMap<>();
        if (!userBase.getUserPassword().equals(changePs.getPassWord())){
            map.put("valid",false);
        }else {
            map.put("valid",true);
        }

        return JSONObject.toJSONString(map);
    }

    @ApiOperation(value = "忘记密码跳转", notes = "")
    @RequestMapping(value = "/initForgetPs" ,method = RequestMethod.GET)
    public String initForgetPs(){return "forgetPs";}

    /**
     * 获取6位验证码
     * @return
     */
    private  String getSix() {
        Random rad = new Random();

        String result = rad.nextInt(1000000) + "";

        if (result.length() != 6) {
            return getSix();
        }
        return result;
    }

    @ApiOperation(value = "校验用户邮箱", notes = "")
    @RequestMapping(value = "/checkMail" ,method = RequestMethod.POST)
    @ResponseBody
    public String checkMail(ChangePs changePs){
        SelectUserBase userBase = selectUserBaseMapper.selectOne(new SelectUserBase()
                .setUserCode(changePs.getUserCode())
                .setUserMail(changePs.getUserMail()));
        Map<String,Boolean> map = new HashMap<>();
        if (ObjectUtils.isEmpty(userBase)){
            map.put("valid",false);//显示信息
        }else {
            map.put("valid",true);
        }

        return JSONObject.toJSONString(map);
    }

    @ApiOperation(value = "发送验证码", notes = "")
    @RequestMapping(value = "/sendSixCode" ,method = RequestMethod.POST)
    @ResponseBody
    public String sendSixCode(ChangePs changePs){
        SelectUserBase userBase = selectUserBaseMapper.selectOne(new SelectUserBase()
                .setUserCode(changePs.getUserCode()));
        String sixCode = getSix();
        SelectUserBase selectUserBase = new SelectUserBase()
                .setId(userBase.getId())
                .setSixCode(sixCode);
        //验证码邮件
        selectJavaMailService.sendHtmlMail(changePs.getUserMail(),userBase.getUserName(),sixCode,"修改密码的验证码");

        return selectUserBaseMapper.updateById(selectUserBase)>0 ? JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    @ApiOperation(value = "校验验证码", notes = "")
    @RequestMapping(value = "/checkSixCode" ,method = RequestMethod.POST)
    @ResponseBody
    public String checkSixCode(ChangePs changePs){
        SelectUserBase userBase = selectUserBaseMapper.selectOne(new SelectUserBase()
                .setUserCode(changePs.getUserCode())
                .setSixCode(changePs.getSixCode()));
        Map<String,Boolean> map = new HashMap<>();
        if (ObjectUtils.isEmpty(userBase)){
            map.put("valid",false);//显示信息
        }else {
            map.put("valid",true);
        }
        return JSONObject.toJSONString(map);
    }

    /***
     * 自动结题，根据流程表中的时间，对本届已选的题目和选题记录修改状态为“已结题”
     * @param
     * @return
     */
    public String overSelect(){
       return "";
    }


}

