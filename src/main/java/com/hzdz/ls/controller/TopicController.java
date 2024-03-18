package com.hzdz.ls.controller;

import com.hzdz.ls.common.Result;

import com.hzdz.ls.service.TopicServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RequestMapping("/topic")
@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class TopicController {

    @Autowired
    private TopicServer topicServer;

    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(@RequestParam Integer topic_id){
        return topicServer.list(topic_id);
    }

    /**
     * 活动详情
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Result detail(@RequestParam Integer id){
        return topicServer.detail(id);
    }

    /**
     * 删除具体活动下的某一张图片
     * @param id
     * @return
     */
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestParam Integer id, HttpServletRequest request){
        return topicServer.delete(id, request.getSession().getServletContext().getRealPath("/"));
    }

    /**
     * 根据图片的url生成二维码
     * @param imageUrl
     * @return
     */
    @RequestMapping(value="urlToQR", method = RequestMethod.POST)
    @ResponseBody
    public Result urlToQR(String imageUrl){
        return topicServer.urlToQR(imageUrl);
    }
}
