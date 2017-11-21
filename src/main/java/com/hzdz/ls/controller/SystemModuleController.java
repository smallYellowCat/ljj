package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemModuleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
*
*@author 豆豆
*时间:
*/
@RestController
@RequestMapping("/systemModule")
public class SystemModuleController {

    @Autowired
    private SystemModuleServer systemModuleServer;

    /**
     * 新增模块
     * @param moduleName
     * @param moduleUrl
     * @param request
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insertModule(@RequestParam String moduleName, @RequestParam String moduleUrl, HttpServletRequest request){
        return systemModuleServer.addModule(moduleName, moduleUrl, request);
    }

    @RequestMapping(value = "/queryModule", method = RequestMethod.POST)
    @ResponseBody
    public Result queryModule(HttpServletRequest request){
        return systemModuleServer.queryModule(request);
    }

}
