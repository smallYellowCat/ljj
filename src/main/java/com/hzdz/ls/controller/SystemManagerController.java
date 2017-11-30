package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemManagerServer;
import com.hzdz.ls.service.module.CloudPhotographyServer;
import org.omg.CORBA.ORBPackage.InconsistentTypeCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/back/systemManager")
public class SystemManagerController {

    @Autowired
    private SystemManagerServer systemManagerServer;

    @Autowired
    private CloudPhotographyServer cloudPhotographyServer;

    /***
     * 新增管理员
     * @param userAccount
     * @param password
     * @param managerType
     * @param request
     * @return
     */
    @RequestMapping(value = "/addNewManager", method = RequestMethod.POST)
    @ResponseBody
    public Result addNewManager(@RequestParam String userAccount, @RequestParam String password, @RequestParam Integer managerType, @RequestParam String remarks, HttpServletRequest request){
        return systemManagerServer.addNewManager(userAccount, password, managerType, remarks, request);
    }

    /**
     * 更改密码
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePassword(@RequestParam Integer id, @RequestParam String password, @RequestParam String oldPassword, HttpServletRequest request){
        return systemManagerServer.updatePassword(id, password, oldPassword, request);
    }

    /**
     * 冻结管理员
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/frozenManager", method = RequestMethod.POST)
    @ResponseBody
    public Result frozenManager(@RequestParam Integer id, HttpServletRequest request){
        return systemManagerServer.frozenManager(id, request);
    }

    /**
     * 管理员解冻
     * @param id 管理员id
     * @param request 请求
     * @return 返回操作结果
     */
    @RequestMapping(value = "/thawManager", method = RequestMethod.POST)
    @ResponseBody
    public Result thawManager(@RequestParam Integer id, HttpServletRequest request){
        return systemManagerServer.thawManager(id, request);
    }

    /**
     * 重置密码
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Result resetPassword(@RequestParam Integer id, HttpServletRequest request){
        return systemManagerServer.resetPassword(id, request);
    }

    /**
     * 查询所有管理员（不含超管）
     * @param request
     * @return
     */
    @RequestMapping("/queryAllManager")
    @ResponseBody
    public Result selectAllManager(@RequestParam(required = false) Integer id,
                                   @RequestParam(required = false) String userAccount,
                                   @RequestParam(required = false) Integer frozen,
                                   HttpServletRequest request){
        return systemManagerServer.selectAllManager(id, userAccount, frozen, request);
    }

    /**
     * 登录验证
     * @param userAccount
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    @ResponseBody
    public Result loginVerify(@RequestParam String userAccount, @RequestParam String password, HttpServletRequest request){
        return systemManagerServer.loginVerify(userAccount, password, request);
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Result logout(HttpServletRequest request){
        return systemManagerServer.logout(request);
    }





}

