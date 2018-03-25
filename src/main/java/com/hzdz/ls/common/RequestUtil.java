package com.hzdz.ls.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
/**
*
*@author 豆豆
*时间:
*/
public class RequestUtil {

    public static HttpServletRequest getRequest(){
        return  ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
    }
}
