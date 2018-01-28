package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.entity.module.CloudPhotography;
import com.hzdz.ls.intercepter.MyIntercepter;
import com.hzdz.ls.service.SystemActivityServer;
import com.hzdz.ls.service.SystemManagerServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/back/systemActivity")
public class SystemActivityController {

    @Autowired
    private SystemActivityServer systemActivityServer;

    @RequestMapping(value = "/addNewActivity", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增活动", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityName", value = "activityName", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "belongManager", value = "belongManager", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "shareText", value = "shareText", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "templateId", value = "templateId", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "moduleIds", value = "moduleIds", required = true, dataType = "int", paramType = "form", allowMultiple = true)
    })
    public Result addNewActivity(@RequestParam String activityName,
                                 @RequestParam Integer belongManager,
                                 @ApiParam MultipartFile shareImage,
                                 @RequestParam String shareText,
                                 @RequestParam Integer templateId,
                                 @RequestParam Integer[] moduleIds,
                                 HttpServletRequest request)
            throws Exception {
        return systemActivityServer.addNewActivity(activityName, belongManager, templateId, shareImage, shareText, moduleIds, request);
    }

    @RequestMapping(value = "/deleteActivity", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除活动", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityIds", value = "activityIds", required = true, dataType = "int", paramType = "form", allowMultiple = true)
    })
    public Result deleteActivity(@RequestParam Integer[] activityIds,
                                 HttpServletRequest request) {
        return systemActivityServer.deleteActivity(activityIds, request);
    }

    @RequestMapping(value = "/updateShareImage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新分享图片及内容", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "shareText", value = "shareText", required = false, dataType = "String", paramType = "form")
    })
    public Result updateShareImage(@RequestParam Integer activityId,
                                   @ApiParam MultipartFile shareImage,
                                   @RequestParam(required = false) String shareText,
                                   HttpServletRequest request) throws IOException{
        return systemActivityServer.updateShareImage(activityId, shareImage, shareText, request);
    }

    @RequestMapping(value = "/updateModuleOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更换模块顺序", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id1", value = "id1", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "id2", value = "id2", required = true, dataType = "int", paramType = "form")
    })
    public Result updateModuleOrder(@RequestParam Integer id1,
                                    @RequestParam Integer id2,
                                    HttpServletRequest request) throws IOException {
        return systemActivityServer.updateModuleOrder(id1, id2, request);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改活动", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "activityName", value = "activityName", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "belongManager", value = "belongManager", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "shareText", value = "shareText", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "templateId", value = "templateId", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "moduleIds", value = "moduleIds", required = true, dataType = "intt", paramType = "form", allowMultiple = true)
    })
    public Result modifyActivity(@RequestParam Integer activityId,
                                 @RequestParam String activityName,
                                 @RequestParam Integer belongManager,
                                 @ApiParam MultipartFile shareImage,
                                 @RequestParam String shareText,
                                 @RequestParam Integer templateId,
                                 @RequestParam Integer[] moduleIds,
                                 HttpServletRequest request){
        return systemActivityServer.modifyActivity(activityId, activityName, belongManager,
                templateId, shareImage, shareText, moduleIds, request);
    }

    @RequestMapping(value = "/queryActivity", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询活动", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "activityName", value = "activityName", required = false, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "belongManager", value = "belongManager", required = false, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "status", value = "活动状态： 0未开启， 1开启， 2已删除", required = false, dataType = "int", paramType = "form")
    })
    public Result queryActivity(@RequestParam(required = false) Integer id,
                                @RequestParam(required = false) String activityName,
                                @RequestParam(required = false) Integer belongManager,
                                @RequestParam(required = false) Integer status,
                                HttpServletRequest request){
        return systemActivityServer.queryActivity(id, activityName, belongManager, status, request);
    }

    @RequestMapping(value = "/modifyStatus", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改活动状态", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "status", value = "活动状态： 0未开启， 1开启， 2已删除", required = true, dataType = "int", paramType = "form")
    })
    public Result modifyActivityStatus(@RequestParam int status,
                                       @RequestParam int activityId){
        return systemActivityServer.modifyActivityStatus(status, activityId);
    }



}