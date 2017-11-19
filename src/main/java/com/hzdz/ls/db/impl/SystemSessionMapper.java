package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SystemSessionMapper {

    int add(SystemSession systemSession);
    int updateOutTime(@Param("outTime") Date date, @Param("sid")String sid);
    List<SystemSession> queryEffectiveSession();
    int updateSessionStatus(SystemSession systemSession);
}
