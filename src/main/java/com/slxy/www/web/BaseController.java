package com.slxy.www.web;



/**
 *
 * SelectBugLog 控制层
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql123
 * @since 2018-04-03
 */
@Controller
public class BaseController {

    @RequestMapping("/")
    public String index(){return "login";}

}

