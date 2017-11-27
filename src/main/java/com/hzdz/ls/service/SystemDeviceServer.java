package com.hzdz.ls.service;

import com.hzdz.ls.common.*;
import com.hzdz.ls.db.entity.SystemDevice;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.entity.SystemModule;
import com.hzdz.ls.db.impl.SystemActivityMapper;
import com.hzdz.ls.db.impl.SystemActivityModuleMapMapper;
import com.hzdz.ls.db.impl.SystemDeviceMapper;
import com.hzdz.ls.intercepter.MyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    /*public Result startQuery(String deviceId) {
        Map<String, Object> data = new HashMap<>();
        int activityId = systemDeviceMapper.queryActivityIdByDID(deviceId);
        if (activityId < 0) {
            data.put("code", -1);
            data.put("msg", "该设备未开启任何活动");
            return new ResultDetail<>(data);
        }

        String templateUrl = systemActivityMapper.queryTemplateUrlById(activityId);
        List<SystemModule> modules = systemActivityModuleMapMapper.queryModuleIdsById(activityId);
        if (modules == null) {
            data.put("code", -1);
            data.put("msg", "该活动未配置可用模块");
            return new ResultDetail<>(data);
        }

        data.put("activityId", activityId);
        data.put("modules", modules);
        data.put("templateUrl", templateUrl);

        data.put("code", 0);
        data.put("msg", "初始化成功");

        return new ResultDetail<>(data);

    }*/

    /**
     * 新增设备
     * @return
     */
    public Result addDevice(String DID){
        Map<String, Object> data = new HashMap<>();
        if (!StringUtil.checkEmpty(DID) || DID.length() != BaseVar.DID_LENGTH) {
            data.put("code", -1);
            data.put("msg", "设备号不正确！");
        } else {
            SystemDevice device = new SystemDevice();
            device.setDeviceId(DID);
            device.setAddTime(new Date(System.currentTimeMillis()));
            device.setUpdateTime(new Date(System.currentTimeMillis()));
            device.setStatus(0);
            if (systemDeviceMapper.insertDevice(device) != 1) {
                data.put("code", -1);
                data.put("msg", "系统错误！");
            } else {
                data.put("code", 0);
                data.put("msg", "新增设备成功！");
            }
        }
        return new ResultDetail<>(data);
    }

    /**
     * 设备管理查看列表
     *
     * @param request
     * @return
     */
    public Result list(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        List<SystemDevice> deviceList = null;
        SystemManager manager = MyIntercepter.getManager(request);
        if (manager == null) {
            data.put("code", -1);
            data.put("msg", "系统错误！");
        } else {
            deviceList = systemDeviceMapper.queryDeviceListByManager(manager);
            data.put("deviceList", deviceList);
        }
        return new ResultDetail<>(data);
    }

    /**
     * 删除设备
     *
     * @param id 主键id
     * @return
     */
    public Result deleteByid(int id) {
        if (systemDeviceMapper.deleteByid(id) != 1) {
            return Result.FAILURE;
        }
        return Result.SUCCESS;
    }

    /**
     * 分配设备给管理员（超管操作）
     *
     * @return
     */
    public Result allocateDeviceToManager(Integer id, Integer managerId, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        SystemManager systemManager = MyIntercepter.getManager(request);
        if (systemManager.getManagerType() == 1) {
            SystemDevice systemDevice = systemDeviceMapper.queryDeviceById(id);
            if (systemDevice.getBelongManager() == null) {
                systemDevice.setBelongManager(managerId);
                systemDevice.setUpdateTime(new Date(System.currentTimeMillis()));
                if (systemDeviceMapper.allocateDeviceToManager(systemDevice) < 1) {
                    data.put("code", -1);
                    data.put("msg", "无法分配设备！");
                } else {
                    data.put("code", 0);
                    data.put("msg", "分配成功");
                }
            } else {
                data.put("code", -1);
                data.put("msg", "该设备已经被分配！");
            }
        } else {
            data.put("code", -1);
            data.put("msg", "非超管不能分配设备！");
        }
        return new ResultDetail<>(data);
    }

    /**
     * 分配活动给设备（管理员操作）
     *
     * @return
     */
    public Result allocateActivityToDevice(Integer id, Integer activityId, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        SystemManager nowSystemManager = MyIntercepter.getManager(request);
        SystemDevice systemDevice = systemDeviceMapper.queryDeviceById(id);
        if (nowSystemManager.getId() == systemDevice.getBelongManager() || nowSystemManager.getManagerType() == 1) {
            if (systemDevice.getStatus() == 0){
                systemDevice.setUpdateTime(new Date(System.currentTimeMillis()));
                systemDevice.setStatus(1);
                systemDevice.setActivityId(activityId);
                if(systemDeviceMapper.allocateActivityToDevice(systemDevice) < 1){
                    data.put("code", -1);
                    data.put("msg", "分配活动失败！");
                }else {
                    data.put("code", 0);
                    data.put("msg", "分配活动成功！");
                }
            }else {
                data.put("code", -1);
                data.put("msg", "该设备已分配活动！");
            }
        } else {
            data.put("code", -1);
            data.put("msg", "不能分配不属于自己的设备！");
        }

        return new ResultDetail<>(data);
    }
}
