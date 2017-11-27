package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.service.InitServer;
import com.hzdz.ls.service.SystemDeviceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/init")
public class InitController {

    @Autowired
    private InitServer initServer;

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    public Result init(@RequestParam("macAddress") String macAddress){
        return initServer.init(macAddress);
    }
}
