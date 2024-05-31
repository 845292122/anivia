package com.edison.framework.xss;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.web.util.HtmlUtils;

/**
 * @author edison
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value != null) {
            value = cleanXss(value);
        }
        return value;
    }

    private String cleanXss(String value) {
        // 实现XSS清洗逻辑，例如使用HtmlUtils.htmlEscape(value)
        return HtmlUtils.htmlEscape(value);
    }
}
