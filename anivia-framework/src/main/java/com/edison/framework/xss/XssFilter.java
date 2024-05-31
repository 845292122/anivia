package com.edison.framework.xss;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * xss过滤器
 * @author edison
 */
@Component
public class XssFilter implements Filter {

    private List<String> excludes = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludesUrls = filterConfig.getInitParameter("excludesUrls");
        String[] excludesArr = excludesUrls.split(",");
        excludes.addAll(Arrays.asList(excludesArr));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        // 检查请求URL是否在排除列表中
        if (!excludes.contains(req.getRequestURI())) {
            // 创建XSS过滤请求包装类
            HttpServletRequestWrapper wrapper = new XssHttpServletRequestWrapper((HttpServletRequest) request);
            // 继续请求链
            chain.doFilter(wrapper, response);
        } else {
            // 如果URL在排除列表中，直接传递请求
            chain.doFilter(request, response);
        }
    }
}
