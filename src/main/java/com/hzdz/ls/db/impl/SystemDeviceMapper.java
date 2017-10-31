package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemDevice;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemDeviceMapper {

    int queryActivityIdByDID(String deviceId);
    int insertDevice(SystemDevice device);
}
