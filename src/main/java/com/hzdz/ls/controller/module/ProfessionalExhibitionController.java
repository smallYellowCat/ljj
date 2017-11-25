package com.hzdz.ls.controller.module;


import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.module.ProfessionalExhibitionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/professionalExhibition")
public class ProfessionalExhibitionController {

    @Autowired
    ProfessionalExhibitionServer professionalExhibitionServer;

    @RequestMapping("/addNewProfessionalExhibition")
    @ResponseBody
    public Result addNewProfessionalExhibition(@RequestParam MultipartFile image, @RequestParam String vrUrl,@RequestParam Integer activityId, HttpServletRequest request) throws Exception{
        return professionalExhibitionServer.addNewProfessionalExhibition(image, vrUrl, activityId, request);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestParam Integer activityId, HttpServletRequest request){
        return professionalExhibitionServer.queryProfessionalExhibition(activityId);
    }

}
