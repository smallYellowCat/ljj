package com.hzdz.ls.controller.module;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.module.CloudPhotography;
import com.hzdz.ls.db.pagehelper.PageContent;
import com.hzdz.ls.service.module.CloudPhotographyServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public Result multiUploadImage(@RequestParam(value = "files", required = true) MultipartFile[] files,
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
    public Result deleteCloudUpload(@RequestParam Integer[] ids, HttpServletRequest request){
        return cloudPhotographyServer.deleteCloudUpload(ids, request);
    }

}
