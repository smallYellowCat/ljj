package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("topic")
public class TopicController {

    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(){
        return null;
    }
}
