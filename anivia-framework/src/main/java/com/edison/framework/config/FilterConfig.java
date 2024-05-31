package com.edison.framework.config;

import com.edison.framework.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author edison
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<XssFilter> xssFilterRegistration() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        // TODO 添加排除URL
        registration.addInitParameter("excludedUrls", "/api/safe");
        // 设置过滤器顺序，数字越小优先级越高
        registration.setOrder(1);
        return registration;
    }
}
