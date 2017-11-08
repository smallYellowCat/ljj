package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SystemSessionMapper {

    int add(SystemSession systemSession);
    int updateOutTime(@Param("date") Date date, @Param("sid")String sid);

}
