package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.intercepter.MyIntercepter;
import com.hzdz.ls.service.SystemDeviceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/systemDevice")
public class SystemDeviceController {

    @Autowired
    private SystemDeviceServer systemDeviceServer;

    /**
     * 新增设备
     * @param DID 设备号
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result inserDevice(@RequestParam String DID){
        return  systemDeviceServer.addDevice(DID);
    }

    /**
     * 设备列表查看
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(HttpServletRequest request){
        return systemDeviceServer.list(request);
    }

    /**
     * 删除设备
     * @param id 主键id
     * @return
     */
    @RequestMapping(value="delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(int id){
        return systemDeviceServer.deleteByid(id);
    }
}
