package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.intercepter.MyIntercepter;
import com.hzdz.ls.service.SystemDeviceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/back/systemDevice")
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
    public Result insertDevice(@RequestParam String DID){
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

    /**
     * 分配设备给管理员（超管操作）
     * @return
     */
    @RequestMapping(value="allocateDeviceToManager", method = RequestMethod.POST)
    @ResponseBody
    public Result allocateDeviceToManager(@RequestParam Integer id, @RequestParam Integer managerId, HttpServletRequest request){
        return systemDeviceServer.allocateDeviceToManager(id, managerId, request);
    }

    /**
     * 分配活动给设备
     * @return
     */
    @RequestMapping(value = "allocateActivityToDevice", method = RequestMethod.POST)
    @ResponseBody
    public Result allocateActivityToDevice(@RequestParam Integer id, @RequestParam Integer activityId, HttpServletRequest request){
        return systemDeviceServer.allocateActivityToDevice(id, activityId, request);
    }
}