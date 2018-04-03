package com.slxy.www.filter;

import com.slxy.www.model.SelectUserBase;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengql
 * @description
 * @className AuthorizationInterceptor
 * @create 2018年04月02日  14:37
 */
public class AdminInterceptor implements HandlerInterceptor {
    //不拦截的页面
    private static final String[] IGNORE_URI={"/selectUserBase/login"};   //填写XX.do

    /**
     * 请求之后执行，用于清理资源
     * 在Interceptor的preHandle返回为true时执行
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        System.out.println("方法AuthorizationInterceptor  afterCompletion()");
    }
    /**
     * Controller调用之后执行，可对ModelAndView操作
     * 当Interceptor的preHandle返回为true时执行
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        System.out.println("方法AuthorizationInterceptor  postHandle()");
    }
    /**
     * preHandle拦截使用，在controller执行之前
     * 返回值为true才会向下执行，false的话请求就结束
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        System.out.println("方法AuthorizationInterceptor  preHandle()");
        boolean flag=false;   //用于存储判断登录的结果
        //对请求路径进行判断
        String servletPath=request.getServletPath();
        //判断请求是否需要拦截
        for(String s:IGNORE_URI){
            if(servletPath.contains(s)){
                flag=true;    //如果是不拦截的网站，flag为true,跳出循环，转向下个方法
                break;
            }
        }
        //拦截请求
        if(!flag){   //如果是非公开的页面↓
            SelectUserBase admin=(SelectUserBase)request.getSession().getAttribute("sessionUser");
            if(admin==null){
                System.out.println("AuthorizationInterceptor拦截请求");
                request.setAttribute("message", "请先登录管理员后再访问网站");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }else{
                //用户登陆过，验证通过，放行
                System.out.println("AuthorizationInterceptor放行请求");
                flag=true;
            }
        }

        return flag;
    }

}

