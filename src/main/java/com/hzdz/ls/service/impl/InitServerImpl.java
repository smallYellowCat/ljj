package com.hzdz.ls.service.impl;

import com.hzdz.ls.common.IPUtil;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.SystemActivity;
import com.hzdz.ls.db.entity.SystemActivityModuleMap;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.entity.SystemModule;
import com.hzdz.ls.db.impl.*;
import com.hzdz.ls.service.InitServer;
import com.hzdz.ls.vo.BelongModule;
import com.hzdz.ls.vo.InitActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InitServerImpl implements InitServer {

    @Autowired
    private SystemDeviceMapper systemDeviceMapper;

    @Autowired
    private SystemActivityMapper systemActivityMapper;

    @Autowired
    private SystemManagerMapper systemManagerMapper;

    @Autowired
    private SystemActivityModuleMapMapper systemActivityModuleMapMapper;

    @Autowired
    private SystemModuleMapper systemModuleMapper;

    @Override
    public Result init(HttpServletRequest request) throws Exception{
        Map<String, Object> data = new HashMap<>();
        String DID = IPUtil.getMACAddress(request);
        Integer activityId = systemDeviceMapper.queryActivityIdByDID(DID);
        if (activityId != null) {
            SystemActivity systemActivity = systemActivityMapper.queryActivityById(activityId);
            if (systemActivity != null){
                Integer managerId = systemActivity.getBelongManager();
                SystemManager systemManager = systemManagerMapper.queryManagerByID(managerId);
                if (systemManager != null){
                    InitActivityVO initActivityVO = new InitActivityVO();
                    initActivityVO.setId(activityId);
                    initActivityVO.setManagerId(managerId);
                    List<BelongModule> belongModuleList = new ArrayList<>();
                    List<SystemActivityModuleMap> moduleMapList = systemActivityModuleMapMapper.queryModuleByActivityId(activityId);
                    for (int i = 0; i < moduleMapList.size(); i++){
                        BelongModule belongModule = new BelongModule();
                        SystemModule systemModule = systemModuleMapper.queryModuleById(moduleMapList.get(i).getModuleId());
                        belongModule.setModuleId(systemModule.getId());
                        belongModule.setSortNum(moduleMapList.get(i).getSortNum());
                        belongModule.setModuleName(systemModule.getModuleName());
                        belongModule.setIcon(systemModule.getIcon());
                        belongModuleList.add(belongModule);
                    }
                    initActivityVO.setList(belongModuleList);
                    data.put("initActivityVO", initActivityVO);
                    data.put("code", 0);
                    data.put("msg", "查询成功！");
                } else {
                    data.put("code", -1);
                    data.put("msg", "查询失败,该活动对应管理员已被冻结！");
                }
            }else {
                data.put("code", -1);
                data.put("msg", "查询失败,活动不存在或未开启！");
            }
        }else {
            data.put("code", -1);
            data.put("msg", "查询失败,该设备不存在或未分配活动！");
        }
        return new ResultDetail<>(data);
    }
}
