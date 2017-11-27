package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemDevice;
import com.hzdz.ls.db.entity.SystemManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemDeviceMapper {

    int queryActivityIdByDID(@Param("deviceId") String deviceId);
    int insertDevice(SystemDevice device);
    List<SystemDevice> queryDeviceListByManager(SystemManager systemManager);
    int deleteByid(int id);
    int allocateDeviceToManager(SystemDevice systemDevice);
    int allocateActivityToDevice(SystemDevice systemDevice);
    SystemDevice queryDeviceById(Integer id);
}
