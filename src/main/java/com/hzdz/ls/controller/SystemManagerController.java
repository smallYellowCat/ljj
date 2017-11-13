package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemManagerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/systemManager")
public class SystemManagerController {

    @Autowired
    private SystemManagerServer systemManagerServer;

    @RequestMapping("/addNewManager")
    @ResponseBody
    public Result addNewManager(@RequestParam String userAccount, @RequestParam String password, @RequestParam Integer managerType, HttpServletRequest request){
        return systemManagerServer.addNewManager(userAccount, password, managerType, request);
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public Result updatePassword(@RequestParam Integer id, @RequestParam String password, HttpServletRequest request){
        return systemManagerServer.updatePassword(id, password, request);
    }

    @RequestMapping("/frozenManager")
    @ResponseBody
    public Result frozenManager(@RequestParam Integer id, HttpServletRequest request){
        return systemManagerServer.frozenManager(id, request);
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public Result resetPassword(@RequestParam Integer id){
        return systemManagerServer.resetPassword(id);
    }

    @RequestMapping("/selectAllManager")
    @ResponseBody
    public Result selectAllManager(){
        return systemManagerServer.selectAllManager();
    }
}

