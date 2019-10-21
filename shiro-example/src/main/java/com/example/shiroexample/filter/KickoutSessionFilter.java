package com.example.shiroexample.filter;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class KickoutSessionFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(
            ServletRequest servletRequest,
            ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(
            ServletRequest servletRequest,
            ServletResponse servletResponse) throws Exception {
        return false;
    }
}
