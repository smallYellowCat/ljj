package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.entity.module.CloudPhotography;
import com.hzdz.ls.intercepter.MyIntercepter;
import com.hzdz.ls.service.SystemActivityServer;
import com.hzdz.ls.service.SystemManagerServer;
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
    public Result addNewActivity(@RequestParam String activityName,
                                 @RequestParam Integer belongManager,
                                 @RequestParam MultipartFile shareImage,
                                 @RequestParam String shareText,
                                 @RequestParam Integer templateId,
                                 @RequestParam Integer[] moduleIds,
                                 HttpServletRequest request)
            throws Exception {
        return systemActivityServer.addNewActivity(activityName, belongManager, templateId, shareImage, shareText, moduleIds, request);
    }

    @RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除活动", httpMethod = "POST")
    public Result deleteActivity(@RequestParam Integer[] activityIds, HttpServletRequest request) {
        return systemActivityServer.deleteActivity(activityIds, request);
    }

    @RequestMapping(value = "/updateShareImage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新分享图片及内容", httpMethod = "POST")
    public Result updateShareImage(@RequestParam Integer activityId,
                                   @RequestParam(required = false) MultipartFile shareImage,
                                   @RequestParam(required = false) String shareText,
                                   HttpServletRequest request) throws IOException{
        return systemActivityServer.updateShareImage(activityId, shareImage, shareText, request);
    }

    @RequestMapping(value = "/updateModuleOrder", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更换模块顺序", httpMethod = "POST")
    public Result updateModuleOrder(@RequestParam Integer id1, @RequestParam Integer id2, HttpServletRequest request) throws IOException {
        return systemActivityServer.updateModuleOrder(id1, id2, request);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改活动", httpMethod = "POST")
    public Result modifyActivity(@RequestParam Integer activityId,
                                 @RequestParam String activityName,
                                 @RequestParam Integer belongManager,
                                 @RequestParam(required = false) MultipartFile shareImage,
                                 @RequestParam String shareText,
                                 @RequestParam Integer templateId,
                                 @RequestParam Integer[] moduleIds,
                                 HttpServletRequest request){
        return systemActivityServer.modifyActivity(activityId, activityName, belongManager,
                templateId, shareImage, shareText, moduleIds, request);
    }

    @RequestMapping(value = "/queryActivity", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询活动", httpMethod = "POST")
    public Result queryActivity(@RequestParam(required = false) Integer id,
                                @RequestParam(required = false) String activityName,
                                @RequestParam(required = false) Integer belongManager,
                                @ApiParam(name = "status", value = "活动状态： 0未开启， 1开启， 2已删除")@RequestParam(required = false) Integer status,
                                HttpServletRequest request){
        return systemActivityServer.queryActivity(id, activityName, belongManager, status, request);
    }

    @RequestMapping(value = "/modifyStatus", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改活动状态", httpMethod = "POST")
    public Result modifyActivityStatus(@ApiParam(name = "status", value = "活动状态： 0未开启， 1开启， 2已删除")@RequestParam int status, @RequestParam int activityId){
        return systemActivityServer.modifyActivityStatus(status, activityId);
    }



}