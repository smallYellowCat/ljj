package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemActivityServer;
import com.hzdz.ls.service.SystemManagerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/systemActivity")
public class SystemActivityController {

    @Autowired
    private SystemActivityServer systemActivityServer;

    @RequestMapping("/addNewActivity")
    @ResponseBody
    public Result addNewActivity(@RequestParam String activityName,
                                 @RequestParam Integer belongManager,
                                 @RequestParam MultipartFile shareImage,
                                 @RequestParam String shareText,
                                 @RequestParam Integer templateId,
                                 @RequestParam Integer[] moduleIds,
                                 HttpServletRequest request)
    throws Exception{
        return systemActivityServer.addNewActivity(activityName, belongManager, templateId, shareImage, shareText, moduleIds, request);
    }

    @RequestMapping("/deleteActivity")
    @ResponseBody
    public Result deleteActivity(@RequestParam Integer activityId, HttpServletRequest request){
        return systemActivityServer.deleteActivity(activityId, request);
    }
}
