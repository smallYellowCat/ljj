package com.hzdz.ls.intercepter;

import com.hzdz.ls.common.StringUtil;
import com.hzdz.ls.db.entity.SystemSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIntercepter implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sid = (String) request.getSession().getAttribute("sid");
        if (StringUtil.isNull(sid)){
            request.setAttribute("code", -1);
            request.setAttribute("msg", "请先登录！");
            return false;
        }

        SystemSession session = new SystemSession();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
