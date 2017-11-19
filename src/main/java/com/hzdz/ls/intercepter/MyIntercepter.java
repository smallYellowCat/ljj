package com.hzdz.ls.intercepter;

import com.hzdz.ls.common.BaseVar;
import com.hzdz.ls.common.NumberUtil;
import com.hzdz.ls.common.StringUtil;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.entity.SystemSession;
import com.hzdz.ls.service.SystemManagerServer;
import com.hzdz.ls.service.SystemSessionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyIntercepter implements HandlerInterceptor{
    private static final Map<String, SystemSession> sessionMap = new HashMap<>();

    private static SystemSessionServer systemSessionServer;

    public void setSystemSessionServer(SystemSessionServer systemSessionServer){
        this.systemSessionServer = systemSessionServer;
    }

    private static SystemManagerServer systemManagerServer;

    public void setSystemManagerServer(SystemManagerServer systemManagerServer){
        this.systemManagerServer = systemManagerServer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sid = (String) request.getSession().getAttribute("sid");
        if (StringUtil.isNull(sid)){
            request.setAttribute("code", -1);
            request.setAttribute("msg", "请先登录！");
            return false;
        }

        SystemSession session = sessionMap.get(sid);
        if (session == null){
            request.setAttribute("code", -1);
            request.setAttribute("msg", "登录过期！");
            return false;
        }

        if (System.currentTimeMillis() > session.getOutTime().getTime()){
            loginOut(request);
            request.setAttribute("code", -1);
            request.setAttribute("msg", "登录过期！");
            return false;
        }

        //更新失效时间
        systemSessionServer.updateOutTime(new Date(System.currentTimeMillis() + BaseVar.SESSION_OUTTIME), sid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 登录成功，记录登录信息
     * @return
     */
    public static void loginSuccess(HttpServletRequest request, int managerId){
        String sid = System.currentTimeMillis() + "" + NumberUtil.createNum(6);
        SystemSession systemSession = new SystemSession();
        systemSession.setManagerId(managerId);
        systemSession.setSid(sid);
        systemSession.setAddTime(new Date(System.currentTimeMillis()));
        systemSession.setOutTime(new Date(System.currentTimeMillis() + BaseVar.SESSION_OUTTIME));
        systemSession.setStatus(1);

        boolean result = systemSessionServer.add(systemSession);
        if (result){
            sessionMap.put(sid, systemSession);
            request.getSession().setAttribute("sid", sid);
        }
        //return true;
    }

    /**
     * 获得当前登录管理员的id
     * @return
     */
    public static int getManagerId(HttpServletRequest request){
        String sid = (String) request.getSession().getAttribute("sid");
        SystemSession systemSession = sessionMap.get(sid);
        return systemSession.getManagerId();
    }

    /**
     * 获取当前登陆管理员
     * @param request 当前请求
     * @return 当前登陆管理员的所有信息
     */
    public static SystemManager getManager(HttpServletRequest request){
        int id = getManagerId(request);
        return systemManagerServer.getManagerByID(id);
    }

    /**
     * 退出登录
     * @param request
     */
    public static void loginOut(HttpServletRequest request){
        String sid = (String) request.getSession().getAttribute("sid");

        request.getSession().removeAttribute("sid");
        if (!StringUtil.isNull(sid)){
            sessionMap.remove(sid);
        }
    }
}
