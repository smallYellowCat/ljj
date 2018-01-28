package com.hzdz.ls.controller.module;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.module.CloudPhotography;
import com.hzdz.ls.db.pagehelper.PageContent;
import com.hzdz.ls.service.module.CloudPhotographyServer;
import io.swagger.annotations.*;
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
     * @param files
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cloudUpload", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "云摄影上传照片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "int", paramType = "form")
    })
    public Result multiUploadImage(@ApiParam(name = "files", value = "此处为多文件上传，但swagger不支持MutipartFile[]类型，因此Swagger UI中不产生文件上传界面，请注意~~", required = true) MultipartFile[] files,
                                   @RequestParam Integer activityId,
                                   HttpServletRequest request) throws Exception {
        return cloudPhotographyServer.multiUploadImage(files, activityId, request);
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
    public Result deleteCloudUpload(@RequestParam Integer[] ids, HttpServletRequest request){
        return cloudPhotographyServer.deleteCloudUpload(ids, request);
    }

}
