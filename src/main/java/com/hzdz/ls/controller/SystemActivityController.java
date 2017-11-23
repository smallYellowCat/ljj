package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemActivityServer;
import com.hzdz.ls.service.SystemManagerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/back/systemActivity")
public class SystemActivityController {

    @Autowired
    private SystemActivityServer systemActivityServer;

    @RequestMapping(value = "/addNewActivity", method = RequestMethod.POST)
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

    @RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteActivity(@RequestParam Integer activityId, HttpServletRequest request){
        return systemActivityServer.deleteActivity(activityId, request);
    }

    @RequestMapping(value = "/updateShareImage", method = RequestMethod.POST)
    @ResponseBody
    public Result updateShareImage(@RequestParam Integer activityId, @RequestParam MultipartFile shareImage, @RequestParam String shareText, HttpServletRequest request) throws IOException{
        return systemActivityServer.updateShareImage(activityId, shareImage, shareText, request);
    }


}