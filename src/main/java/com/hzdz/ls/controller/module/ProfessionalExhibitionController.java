package com.hzdz.ls.controller.module;


import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.module.ProfessionalExhibitionServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @RequestMapping(value = "/addNewProfessionalExhibition")
    @ResponseBody
    @ApiOperation(value = "新增专业展示", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vrUrl", value = "vrUrl", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "int", paramType = "form")
    })
    public Result addNewProfessionalExhibition(@ApiParam(value = "image", required = true) MultipartFile image,
                                               @RequestParam String vrUrl,
                                               @RequestParam Integer activityId,
                                               HttpServletRequest request) throws Exception{
        return professionalExhibitionServer.addNewProfessionalExhibition(image, vrUrl, activityId, request);
    }

    /**
     * 修改专业展示
     * @return
     */
    @RequestMapping("modify")
    @ResponseBody
    @ApiOperation(value = "修改专业展示", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "vrUrl", value = "vrUrl", required = false, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "status", value = "status", required = false, dataType = "Integer", paramType = "form")
    })
    public Result modify(@RequestParam Integer id,
                         @ApiParam MultipartFile image,
                         @RequestParam(required = false)String vrUrl,
                         @RequestParam(required = false)Integer status,
                         HttpServletRequest request){
        return professionalExhibitionServer.modify(id, image, vrUrl, status, request);
    }

    /**
     * 删除专业展示
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除专业展示", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int", paramType = "form")
    })
    public Result delete(Integer id, HttpServletRequest request){
        return professionalExhibitionServer.delete(id, request);
    }

}
