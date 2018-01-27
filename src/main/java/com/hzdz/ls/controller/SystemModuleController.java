package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemModuleServer;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin(value = "*", maxAge = 3600)
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
    @ApiOperation(value = "新增模块", httpMethod = "POST")
    public Result insertModule(@RequestParam String moduleName, @RequestParam String description, @RequestParam MultipartFile icon, HttpServletRequest request) throws Exception{
        return systemModuleServer.addModule(moduleName, description, icon, request);
    }

    /**
     * 查询所有模块
     * */
    @RequestMapping(value = "/queryModule", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询所有模块", httpMethod = "POST")
    public Result queryModule(HttpServletRequest request){
        return systemModuleServer.queryModule(request);
    }


    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除模块", httpMethod = "DELETE")
    public Result deleteModule(@RequestParam Integer moduleId, HttpServletRequest request){
        return systemModuleServer.deleteModule(moduleId, request);
    }

}
