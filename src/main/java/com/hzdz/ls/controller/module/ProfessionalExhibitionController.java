package com.hzdz.ls.controller.module;


import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.module.ProfessionalExhibitionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("back/professionalExhibition")
@CrossOrigin(value = "*", maxAge = 3600)
public class ProfessionalExhibitionController {

    @Autowired
    ProfessionalExhibitionServer professionalExhibitionServer;

    /**
     * 新增专业展示
     * @param image
     * @param vrUrl
     * @param activityId
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/addNewProfessionalExhibition")
    @ResponseBody
    public Result addNewProfessionalExhibition(@RequestParam MultipartFile image, @RequestParam String vrUrl,@RequestParam Integer activityId, HttpServletRequest request) throws Exception{
        return professionalExhibitionServer.addNewProfessionalExhibition(image, vrUrl, activityId, request);
    }

    /**
     * 修改专业展示
     * @return
     */
    @RequestMapping("modify")
    @ResponseBody
    public Result modify(Integer id,
                         @RequestParam(required = false)MultipartFile image,
                         @RequestParam(required = false)String vrUrl,
                         @RequestParam(required = false)Integer status,
                         HttpServletRequest request){
        return professionalExhibitionServer.modify(id, image, vrUrl, status, request);
    }

    /**
     * 删除专业展示
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Result delete(Integer id, HttpServletRequest request){
        return professionalExhibitionServer.delete(id, request);
    }

}
