package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.impl.SystemTemplateServerImpl;
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
    private SystemTemplateServerImpl systemTemplateServerImpl;

    /**
     * 新增模版文件
     * @param templateName
     * @param templateFile
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addTemplate(@RequestParam("templateName") String templateName,
                              @RequestParam("templateFile")MultipartFile templateFile){
        return systemTemplateServerImpl.addTemplate(templateName, templateFile);
    }

    /**
     * 查询所有模版
     * @return
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public Result queryAll(){
        return systemTemplateServerImpl.queryAll();
    }

}
