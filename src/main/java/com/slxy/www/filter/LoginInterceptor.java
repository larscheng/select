package com.slxy.www.filter;

/**
 * @author zhengql
 * @description
 * @className LoginInterceptor
 * @create 2018年04月02日  15:02
 */


import com.slxy.www.domain.po.SelectUserBase;
import com.slxy.www.enums.EnumUserType;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 登录认证的拦截器
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {


    private static final String[] IGNORE_URI={"/selectUserBase/login","/","","/login","/initForgetPs"};   //填写XX.do


    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        SelectUserBase admin=(SelectUserBase)request.getSession().getAttribute("sessionUser");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String name = request.getServletPath();
        String ip  = request.getRemoteAddr();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        boolean contains = Arrays.asList(IGNORE_URI).contains(name);
        if (!contains){
            if (ObjectUtils.isEmpty(admin)){
                response.getWriter().write("<script>window.open('/','_top')</script>");
                return false;
            }
        }


        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

        if (methodAnnotation != null) {
            String param = methodAnnotation.value();
            if (!ObjectUtils.isEmpty(admin)) {
                try {
                    boolean boo = false;
                    if (param.equals("stu")){
                        boo =  Objects.equals(admin.getUserType(), EnumUserType.STUDENT.getValue());
                    }else if (param.equals("tea")){
                        boo =  Objects.equals(admin.getUserType(), EnumUserType.TEACHER.getValue());
                    }else if (param.equals("adm")){
                        boo =  Objects.equals(admin.getUserType(), EnumUserType.ADMIN0.getValue())||
                                Objects.equals(admin.getUserType(), EnumUserType.ADMIN.getValue());
                    }else if (param.equals("admTea")){
                        boo =  Objects.equals(admin.getUserType(), EnumUserType.ADMIN0.getValue())||
                                Objects.equals(admin.getUserType(), EnumUserType.ADMIN.getValue())||
                                Objects.equals(admin.getUserType(), EnumUserType.TEACHER.getValue());
                    }
                    if (!boo){
                        System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，权限不足！ip:"+ip);
                        request.getRequestDispatcher("/auth.html").forward(request, response);
//                        response.getWriter().write("<script>window.open('/auth.html','_top')</script>");
                    }else {
                        System.out.println("========"+name+"===>LoginInterceptor preHandle 拥有权限，通过！ip:"+ip);
                    }
                    return boo;
                } catch (Exception e) {
                    response.setCharacterEncoding("UTF-8");
                    System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，登录已过期，请重新登录！ip:"+ip);
                    response.getWriter().write("<script>window.open('/','_top')</script>");
                    return false;
                }
            } else {
                response.setCharacterEncoding("UTF-8");
                System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，尚未登录！ip:"+ip);
//                request.getRequestDispatcher("/login.jsp").forward(request, response);
                response.getWriter().write("<script>window.open('/','_top')</script>");
                return false;
            }
        }
        System.out.println("========"+name+"===>LoginInterceptor preHandle 没加验证注解放行  ip:"+ip);
        return true;
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
//        String name = request.getServletPath();
//        System.out.println("========"+name+"===>LoginInterceptor postHandle");
    }

    /**
     * Handler执行完成之后调用这个方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {
//        String name = request.getServletPath();
//        System.out.println("========"+name+"===>LoginInterceptor afterCompletion");
    }
}
