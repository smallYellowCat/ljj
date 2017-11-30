package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemDevice;
import com.hzdz.ls.db.entity.SystemManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemDeviceMapper {

    int queryActivityIdByDID(String deviceId);
    int queryBelongManagerByDID(String deviceID);
    int insertDevice(SystemDevice device);
    List<SystemDevice> queryDeviceListByManager(@Param("managerType") Integer managerType, @Param("belongManager") Integer belongManager, @Param("deviceId") String deviceId);
    int deleteByid(int id);
    int allocateDeviceToManager(SystemDevice systemDevice);
    int allocateActivityToDevice(SystemDevice systemDevice);
    SystemDevice queryDeviceById(Integer id);
}
