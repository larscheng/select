package com.slxy.www.web;

import com.slxy.www.service.SelectJavaMailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author zhengql
 * @description 邮件
 * @className SelectJavaMailController
 * @create 2018年04月16日  9:25
 */
@Controller
@Api(tags = "邮件管理", description = "邮件模块功能")
public class SelectJavaMailController {
    @Autowired
    private SelectJavaMailService  selectJavaMailService;

    @RequestMapping(value = "/sendMail1",method = RequestMethod.GET)
    public void send1(String to,String subject,String content){
        selectJavaMailService.sendSimpleMail(to,subject,content);
    }

    @RequestMapping(value = "/sendHtmlMail1",method = RequestMethod.GET)
    public void send2(String to,String name,String passWord){
        selectJavaMailService.sendHtmlMail(to,name,passWord,"密码重置");
    }

}

