package com.hzdz.ls.service;

import com.hzdz.ls.db.entity.SystemSession;
import com.hzdz.ls.db.impl.SystemSessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
*@author 豆豆
*时间:
*/
@Service
public class SystemSessionServer {

    @Autowired
    private SystemSessionMapper systemSessionMapper;

    public boolean add(SystemSession systemSession){
        return true;
    }
}
