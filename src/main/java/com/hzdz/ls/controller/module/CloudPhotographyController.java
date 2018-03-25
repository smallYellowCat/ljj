package com.hzdz.ls.controller.module;

import com.hzdz.ls.common.RequestUtil;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.module.CloudPhotography;
import com.hzdz.ls.db.pagehelper.PageContent;
import com.hzdz.ls.service.module.CloudPhotographyServer;
import io.swagger.annotations.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
*云摄影
*@author 豆豆
*时间:
*/
@RestController
@RequestMapping("back/cloudPhotography")
@CrossOrigin(value = "*", maxAge = 3600)
public class CloudPhotographyController {

    @Autowired
    private CloudPhotographyServer cloudPhotographyServer;

    /**
     * 云摄影上传照片
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cloudUpload", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "云摄影上传照片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imagePaths", value = "imagePaths", required = true, dataType = "String", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "int", paramType = "form")
    })
    public Result multiUploadImage(@RequestBody Parameter parameter) throws Exception {
        String[] imagePaths = parameter.getImagePaths();
        Integer activityId = parameter.getActivityId();
        return cloudPhotographyServer.multiUploadImage(imagePaths, activityId, RequestUtil.getRequest());
    }

    /**
     * 删除云摄影照片
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value = "deleteCloudUpload", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除云摄影照片", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "int", paramType = "form", allowMultiple = true)
    })
    public Result deleteCloudUpload(@RequestBody Parameter parameter){
        Integer[] ids = parameter.getIds();
        return cloudPhotographyServer.deleteCloudUpload(ids, RequestUtil.getRequest());
    }

}

/**
*
*@author 豆豆
*时间:
*/
@Setter
@Getter
class Parameter{
    private Integer[] ids;
    private String[] imagePaths;
    private Integer activityId;

}
