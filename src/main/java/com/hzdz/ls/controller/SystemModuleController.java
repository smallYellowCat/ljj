package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemModuleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
*
*@author 豆豆
*时间:
*/
@RestController
@RequestMapping("/back/systemModule")
public class SystemModuleController {

    @Autowired
    private SystemModuleServer systemModuleServer;

    /**
     * 新增模块
     * @param moduleName
     * @param request
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insertModule(@RequestParam String moduleName, @RequestParam String description, @RequestParam MultipartFile icon, HttpServletRequest request) throws Exception{
        return systemModuleServer.addModule(moduleName, description, icon, request);
    }

    @RequestMapping(value = "/queryModule", method = RequestMethod.POST)
    @ResponseBody
    public Result queryModule(HttpServletRequest request){
        return systemModuleServer.queryModule(request);
    }

}
