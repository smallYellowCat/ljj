package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.common.WxUtil;
import com.hzdz.ls.db.entity.Sign;
import com.hzdz.ls.db.entity.module.CloudPhotography;
import com.hzdz.ls.db.pagehelper.PageContent;
import com.hzdz.ls.service.module.CloudPhotographyServer;
import com.hzdz.ls.service.module.PhotographServer;
import com.hzdz.ls.service.module.ProfessionalExhibitionServer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
*广告机控制器
*@author 豆豆
*时间:
*/
@RestController
@RequestMapping("/machine")
@CrossOrigin(value = "*", maxAge = 3600)
public class MachineController {

    @Autowired
    private PhotographServer photographServer;

    @Autowired
    private CloudPhotographyServer cloudPhotographyServer;

    @Autowired
    ProfessionalExhibitionServer professionalExhibitionServer;

    /**
     * 获取微信签名
     */
    @RequestMapping(value = "/getSign", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取微信签名", httpMethod = "POST")
    public Result verify(@RequestParam String url){
        Sign sign = WxUtil.getSign(url);
        return new ResultDetail<>(sign);
    }

    /**
     * 个人展示（上传照片之后返回二维码）
     * @param file
     * @return
     */
    @RequestMapping(value = "/getQRCode", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "个人展示（上传照片之后返回二维码）", httpMethod = "POST")
    public Result getQRCode(@RequestParam Integer activityId, @RequestParam MultipartFile file, HttpServletRequest request) throws Exception {
        return photographServer.getQRCode(activityId, file, request);
    }

    /**
     * 云摄影图片查看
     * @param id 活动id
     * @param pageNo 当前页码
     * @param pageSize 分页大小
     * @return 图片集合和分页信息
     */
    @RequestMapping(value = "/cloudPhotographyList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "云摄影图片查看", httpMethod = "POST")
    public Result list(@RequestParam int id, @RequestParam int pageNo, @RequestParam int pageSize){
        PageContent<CloudPhotography> pageContent = cloudPhotographyServer.list(id, pageNo, pageSize);
        return new ResultDetail<>(pageContent);
    }

    /**
     * 专业展示（VR）
     * @param activityId
     * @param request
     * @return
     */
    @RequestMapping("/professionalExhibitionList")
    @ResponseBody
    @ApiOperation(value = "专业展示", httpMethod = "POST")
    public Result list(@RequestParam Integer activityId, HttpServletRequest request){
        return professionalExhibitionServer.queryProfessionalExhibition(activityId);
    }

}
