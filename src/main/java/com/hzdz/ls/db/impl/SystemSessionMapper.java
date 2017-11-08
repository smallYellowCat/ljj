package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemSession;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemSessionMapper {

    int add(SystemSession systemSession);
    //SystemSession
}
