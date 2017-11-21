package com.hzdz.ls.service.module;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.db.entity.module.CloudPhotography;
import com.hzdz.ls.db.impl.module.CloudPhotographyMapper;
import com.hzdz.ls.db.pagehelper.PageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
*
*@author 豆豆
*时间:
*/
@Service
public class CloudPhotographyServer {
    @Autowired
    private CloudPhotographyMapper cloudPhotographyMapper;

    public PageContent<CloudPhotography> list(int activityId, int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize);
        Page<CloudPhotography> list = cloudPhotographyMapper.queryAllByActivityId(activityId);
        List<CloudPhotography> volist = new ArrayList<>();
        for (CloudPhotography cp : list){
            //这步暂时看来是多余的，如果后续列表展示需要其他的信息则需要在这里对数据处理里
             volist.add(cp);
        }

        PageContent<CloudPhotography> pageContent = new PageContent<CloudPhotography>(volist, list.getPageNum(),
                list.getPageSize(), list.getTotal(), list.getPages());
        return pageContent;
    }
}
