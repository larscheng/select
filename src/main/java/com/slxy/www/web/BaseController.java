package com.slxy.www.web;



/**
 *
 * SelectBugLog 控制层
 *
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class BaseController {


    @ApiOperation(value = "登录页跳转", notes = "")
    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String index(){return "login";}

    @ApiOperation(value = "首页跳转", notes = "")
    @RequestMapping(value = "/index" ,method = RequestMethod.GET)
    public String index2(){return "main";}

}

