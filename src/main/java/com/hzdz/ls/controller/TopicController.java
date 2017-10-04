package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.TopicMap;
import com.hzdz.ls.db.impl.TopicMapMapper;
import com.hzdz.ls.db.impl.TopicMapper;
import com.hzdz.ls.service.TopicServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicServer topicServer;
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(@RequestParam String topic){
        Map<String, Object> data = new HashMap<>();
        List<String> imageUrls;
        imageUrls = topicServer.list(topic);
        data.put("code", 0);
        data.put("imageUrls", imageUrls);
        return new ResultDetail(data);
    }
}
