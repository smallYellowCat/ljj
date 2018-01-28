package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemTemplateService;
import com.hzdz.ls.service.impl.SystemTemplateServerImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/back/systemTemplate")
@CrossOrigin(value = "*", maxAge = 3600)
/**
*
*@author 豆豆
*时间:
*/
public class SystemTemplateController {

    @Autowired
    private SystemTemplateService systemTemplateService;

    /**
     * 新增模版文件
     * @param templateName
     * @param templateFile
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增模版文件", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateName", value = "templateName", required = true, dataType = "String", paramType = "form")
    })
    public Result addTemplate(@RequestParam String templateName,
                              @ApiParam MultipartFile templateFile){
        return systemTemplateService.addTemplate(templateName, templateFile);
    }

    /**
     * 查询所有模版
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有模版", httpMethod = "GET")
    public Result queryAll(){
        return systemTemplateService.queryAll();
    }

}
