package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
*广告机控制器
*@author 豆豆
*时间:
*/
@RestController
@RequestMapping("/machine")
@CrossOrigin(value = "*", maxAge = 3600)
public class MachineController {

    @RequestMapping(value = "/personPhotography", method = RequestMethod.POST)
    public Result personPhotography(@RequestParam MultipartFile imageFuile,
                                    @RequestParam Integer activityId){
        return null;
    }
}
