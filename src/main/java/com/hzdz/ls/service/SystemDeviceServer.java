package com.hzdz.ls.service;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.SystemModule;
import com.hzdz.ls.db.impl.SystemActivityMapper;
import com.hzdz.ls.db.impl.SystemActivityModuleMapMapper;
import com.hzdz.ls.db.impl.SystemDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemDeviceServer {

    @Autowired
    private SystemDeviceMapper systemDeviceMapper;
    @Autowired
    private SystemActivityModuleMapMapper systemActivityModuleMapMapper;
    @Autowired
    private SystemActivityMapper systemActivityMapper;

    /***
     * 开机初始化查询
     * @param deviceId
     * @return
     */
    public Result startQuery(String deviceId){
        Map<String, Object> data = new HashMap<>();
        int activityId = systemDeviceMapper.queryActivityIdByDID(deviceId);
        if (activityId < 0){
            data.put("code", -1);
            data.put("msg", "该设备未开启任何活动");
            return new ResultDetail(data);
        }

        String templateUrl = systemActivityMapper.queryTemplateUrlById(activityId);
        List<SystemModule> modules = systemActivityModuleMapMapper.queryModuleIdsById(activityId);
        if (modules == null){
            data.put("code", -1);
            data.put("msg", "该活动未配置可用模块");
            return new ResultDetail(data);
        }

        data.put("activityId", activityId);
        data.put("modules", modules);
        data.put("templateUrl", templateUrl);

        data.put("code", 0);
        data.put("msg", "初始化成功");

        return new ResultDetail(data);

    }

    /**
     * 新增设备
     * @param DID
     * @return
     */
    public Result addDevice(String DID){
        Map<String, Object> data = new HashMap<>();
        return new ResultDetail(data);
    }
}
