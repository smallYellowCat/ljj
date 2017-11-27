package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemManagerServer;
import com.hzdz.ls.service.module.CloudPhotographyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    public Result addNewManager(@RequestParam String userAccount, @RequestParam String password, @RequestParam Integer managerType, HttpServletRequest request){
        return systemManagerServer.addNewManager(userAccount, password, managerType, request);
    }

    /**
     * 更改密码
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePassword(@RequestParam String password, HttpServletRequest request){
        return systemManagerServer.updatePassword(password, request);
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
    public Result selectAllManager(HttpServletRequest request){
        return systemManagerServer.selectAllManager();
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

    /**
     * 云摄影上传照片
     * @param files
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cloudUpload", method = RequestMethod.POST)
    @ResponseBody
    public Result multiUploadImage(@RequestParam MultipartFile[] files,
                                   @RequestParam Integer activityId,
                                   HttpServletRequest request) throws Exception {
        return cloudPhotographyServer.multiUploadImage(files, activityId, request);
    }
}

