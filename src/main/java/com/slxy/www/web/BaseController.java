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

import javax.servlet.http.HttpSession;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
@SessionAttributes(value = {"sessionUser","userType","sessionIp"})
public class BaseController {
@Autowired
private ISelectUserBaseMapper selectUserBaseMapper;

    @ApiOperation(value = "登录页跳转", notes = "")
    @RequestMapping(value = {"/","","/login"} ,method = RequestMethod.GET)
    public String index(){return "login";}

    @ApiOperation(value = "首页跳转", notes = "")
    @RequestMapping(value = "/index" ,method = RequestMethod.GET)
    public String index2(){return "main";}



    @ApiOperation(value = "登录", notes = "")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView index(SelectUserBase userBase, ModelAndView  modelAndView) {
        SelectUserBase user = new SelectUserBase().setUserCode(userBase.getUserCode());
        SelectUserBase selectUserBase = selectUserBaseMapper.selectOne((user));
        if (ObjectUtils.isEmpty(selectUserBase)){
            modelAndView.setViewName("/login");
            modelAndView.addObject("msg","该用户不存在，请重新登录");
            return modelAndView;
        }else if (selectUserBase.getUserStatus().equals(EnumEnOrDis.DISABLED.getValue())){
            modelAndView.setViewName("/login");
            modelAndView.addObject("msg","该用户已被禁用，请重新登录");
            return modelAndView;
        }else if (!userBase.getUserPassword().equals(selectUserBase.getUserPassword())){
            modelAndView.setViewName("/login");
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
        SelectUserBase userBase = selectUserBaseMapper.selectById(changePs.getUserId());
        if (ObjectUtils.isEmpty(userBase)){
            return JSONObject.toJSONString("用户不存在");
        }
        SelectUserBase selectUserBase = new SelectUserBase()
                .setId(userBase.getId())
                .setUserPassword(changePs.getNewPassWord());
        //TODO 发邮件通知

        return selectUserBaseMapper.updateById(selectUserBase)>0 ? JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);

    }

    @ApiOperation(value = "校验原始密码", notes = "")
    @RequestMapping(value = "/checkPs" ,method = RequestMethod.POST)
    @ResponseBody
    public String checkPs(ChangePs changePs){
        SelectUserBase userBase = selectUserBaseMapper.selectById(changePs.getUserId());
        Map<String,Boolean> map = new HashMap<>();
        if (!userBase.getUserPassword().equals(changePs.getPassWord())){
            map.put("valid",false);
        }else {
            map.put("valid",true);
        }

        return JSONObject.toJSONString(map);
    }

}

