package com.slxy.www.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author zhengql
 * @description 参数空格过滤  请求参数值空格过滤器（去除前后空格）
 * @className ParameterTrimFilter
 * @create 2018年04月02日  14:00
 */
public class ParameterTrimFilter implements Filter {
    public void init(FilterConfig filterconfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HashMap map = new HashMap(request.getParameterMap());
        if (map != null && map.size() > 0) {
            Set<Entry> entrySet = map.entrySet();
            // 遍历，参数值去空格
            for (Entry entry : entrySet) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String[]) {
                    String[] valueArray = (String[]) value;
                    for (int i = 0; i < valueArray.length; ++i) {
                        // 去空格
                        valueArray[i] = valueArray[i].trim();
                    }
                    map.put(key, valueArray);
                }
            }
        }

        // 类型转换
        ParameterRequestWrapper wrapRequest = new ParameterRequestWrapper(req, map);
        request = wrapRequest;
        chain.doFilter(request, response);
    }

}
