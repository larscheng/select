package com.slxy.www.filter;

/**
 * @author zhengql
 * @description
 * @className LoginInterceptor
 * @create 2018年04月02日  15:02
 */

import com.alibaba.fastjson.JSON;
import com.slxy.www.common.LoginRequired;
import com.slxy.www.common.result.JsonResult;
import com.slxy.www.model.SelectUserBase;
import com.slxy.www.model.enums.EnumUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 登录认证的拦截器
 */
@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {


    private static final String[] IGNORE_URI={"/selectUserBase/login"};   //填写XX.do

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

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

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
                        System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，权限不足！");
                        request.getRequestDispatcher("/auth.html").forward(request, response);
                    }
                    return boo;
                } catch (Exception e) {
                    response.setCharacterEncoding("UTF-8");
                    System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，登录已过期，请重新登录！");
                    return false;
                }
            } else {
                response.setCharacterEncoding("UTF-8");
                System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，尚未登录！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return false;
            }
        }
        System.out.println("========"+name+"===>LoginInterceptor preHandle 没加验证注解放行");
        return true;
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        String name = request.getServletPath();
        System.out.println("========"+name+"===>LoginInterceptor postHandle");
    }

    /**
     * Handler执行完成之后调用这个方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {
        String name = request.getServletPath();
        System.out.println("========"+name+"===>LoginInterceptor afterCompletion");
    }
}
