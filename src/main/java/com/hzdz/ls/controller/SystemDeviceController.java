package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.SystemDeviceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/systemDevice")
public class SystemDeviceController {

    @Autowired
    private SystemDeviceServer systemDeviceServer;

    public Result inserDevice(){
        return  null;
    }
}
