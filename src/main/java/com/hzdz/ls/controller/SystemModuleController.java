package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemModuleServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moduleName", value = "moduleName", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "description", value = "description", required = true, dataType = "String", paramType = "form")
    })
    public Result insertModule(@RequestParam String moduleName,
                               @RequestParam String description,
                               @ApiParam MultipartFile icon,
                               HttpServletRequest request) throws Exception{
        return systemModuleServer.addModule(moduleName, description, icon, request);
    }

    /**
     * 查询所有模块
     * */
    @RequestMapping(value = "/queryModule", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有模块", httpMethod = "GET")
    public Result queryModule(HttpServletRequest request){
        return systemModuleServer.queryModule(request);
    }


    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除模块", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moduleId", value = "moduleId", required = true, dataType = "int", paramType = "form")
    })
    public Result deleteModule(@RequestParam Integer moduleId,
                               HttpServletRequest request){
        return systemModuleServer.deleteModule(moduleId, request);
    }

}
