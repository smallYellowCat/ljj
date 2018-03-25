package com.hzdz.ls.controller.systemactivity;

import com.hzdz.ls.common.RequestUtil;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemActivityServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
            @ApiImplicitParam(name = "imagePath", value = "imagePath", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "shareText", value = "shareText", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "templateId", value = "templateId", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "moduleIds", value = "moduleIds", required = true, dataType = "int", paramType = "form", allowMultiple = true)
    })
    public Result addNewActivity(@RequestBody Parameter parameter) throws Exception {
        String activityName = parameter.getActivityName();
        Integer belongManager = parameter.getBelongManager();
        String imagePath = parameter.getImagePath();
        String shareText = parameter.getShareText();
        Integer templateId = parameter.getTemplateId();
        Integer[] moduleIds = parameter.getModuleIds();
        HttpServletRequest request = RequestUtil.getRequest();
        return systemActivityServer.addNewActivity(activityName, belongManager, templateId, imagePath, shareText, moduleIds, request);
    }

    @RequestMapping(value = "/deleteActivity", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除活动", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityIds", value = "activityIds", required = true, dataType = "int", paramType = "form", allowMultiple = true)
    })
    public Result deleteActivity(@RequestBody Parameter parameter ) {
        Integer[] activityIds = parameter.getActivityIds();
        return systemActivityServer.deleteActivity(activityIds, RequestUtil.getRequest());
    }

    @RequestMapping(value = "/updateShareImage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新分享图片及内容", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "imagePath", value = "imagePath", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "shareText", value = "shareText", required = false, dataType = "String", paramType = "form"),
    })
    public Result updateShareImage(@RequestParam Integer activityId,
                                   @RequestParam String imagePath,
                                   @RequestParam(required = false) String shareText,
                                   HttpServletRequest request) throws IOException{
        return systemActivityServer.updateShareImage(activityId, imagePath, shareText, request);
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
            @ApiImplicitParam(name = "imagePath", value = "imagePath", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "shareText", value = "shareText", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "templateId", value = "templateId", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "moduleIds", value = "moduleIds", required = true, dataType = "int", paramType = "form", allowMultiple = true)
    })
    public Result modifyActivity(@RequestBody Parameter parameter){
        Integer activityId = parameter.getActivityId();
        String activityName = parameter.getActivityName();
        Integer belongManager = parameter.getBelongManager();
        String imagePath = parameter.getImagePath();
        String shareText = parameter.getShareText();
        Integer templateId = parameter.getTemplateId();
        Integer[] moduleIds = parameter.getModuleIds();
        return systemActivityServer.modifyActivity(activityId, activityName, belongManager,
                templateId, imagePath, shareText, moduleIds, RequestUtil.getRequest());
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