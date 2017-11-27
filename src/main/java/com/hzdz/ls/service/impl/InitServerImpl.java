package com.hzdz.ls.service.impl;


import com.hzdz.ls.common.BaseVar;
import com.hzdz.ls.common.Result;

import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.common.StringUtil;
import com.hzdz.ls.db.entity.SystemModule;
import com.hzdz.ls.db.impl.SystemActivityModuleMapMapper;
import com.hzdz.ls.db.impl.SystemDeviceMapper;
import com.hzdz.ls.db.impl.SystemModuleMapper;
import com.hzdz.ls.service.InitServer;

import com.hzdz.ls.vo.InitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
*
*@author 豆豆
*时间:
*/
@Service
public class InitServerImpl implements InitServer {

    @Autowired
    private SystemModuleMapper systemModuleMapper;

    @Autowired
    private SystemDeviceMapper systemDeviceMapper;

    @Autowired
    private SystemActivityModuleMapMapper systemActivityModuleMapMapper;

    @Override
    public Result init(String macAddress){
        Map<String, Object> data = new HashMap<>();
        if (StringUtil.checkEmpty(macAddress) || macAddress.length() != BaseVar.DID_LENGTH){
            data.put("code", -1);
            data.put("msg", "参数非法");
            return new ResultDetail<>(data);
        }
        int activityId = systemDeviceMapper.queryActivityIdByDID(macAddress);
        int managerId = systemDeviceMapper.queryBelongManagerByDID(macAddress);
        List<SystemModule> modules = systemActivityModuleMapMapper.queryModuleIdsById(activityId);

        InitVO initVO = new InitVO();
        initVO.setActivityId(activityId);
        initVO.setBelongManager(managerId);
        initVO.setArray(modules);

        data.put("initVO", initVO);
        return new ResultDetail<>(data);
    }
}
