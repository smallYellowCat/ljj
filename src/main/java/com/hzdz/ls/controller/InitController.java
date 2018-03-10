package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.InitServer;
import com.hzdz.ls.service.SystemDeviceServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/init")
public class InitController {

    @Autowired
    private InitServer initServer;

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "机器初始化", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "macAddress", value = "macAddress", required = true, dataType = "String", paramType = "form")
    })
    public Result init(@RequestParam("macAddress") String macAddress){
        return initServer.init(macAddress);
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "上传图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "image", required = true, dataType = "file", paramType = "form")
    })
    public Result uploadImage(MultipartFile image){
        return initServer.uploadImage(image);
    }
}
