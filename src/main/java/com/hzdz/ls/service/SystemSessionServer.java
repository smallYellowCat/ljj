package com.hzdz.ls.service;

import com.hzdz.ls.db.entity.SystemSession;
import com.hzdz.ls.db.impl.SystemSessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        int num = systemSessionMapper.add(systemSession);
        if (num > 0){
            return true;
        }
        return false;
    }

    /**
     * 更新过期时间
     * @param date
     * @param sid
     * @return
     */
    public boolean updateOutTime(Date date, String sid){
        int num = systemSessionMapper.updateOutTime(date, sid);
        if (num > 0){
            return true;
        }
        return false;
    }
}
