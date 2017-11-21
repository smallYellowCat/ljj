package com.hzdz.ls.controller.module;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.module.CloudPhotography;
import com.hzdz.ls.db.pagehelper.PageContent;
import com.hzdz.ls.service.module.CloudPhotographyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
*云摄影
*@author 豆豆
*时间:
*/
@RestController
@RequestMapping("/cloudPhotography")
public class CloudPhotographyController {

    @Autowired
    private CloudPhotographyServer cloudPhotographyServer;

    /**
     * 云摄影图片查看
     * @param id 活动id
     * @param pageNo 当前页码
     * @param pageSize 分页大小
     * @return 图片集合和分页信息
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(@RequestParam int id, @RequestParam int pageNo, @RequestParam int pageSize){
        PageContent<CloudPhotography> pageContent = cloudPhotographyServer.list(id, pageNo, pageSize);
        return new ResultDetail<>(pageContent);
    }
}
