package com.hzdz.ls.service;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.common.StringUtil;
import com.hzdz.ls.db.entity.TopicMap;
import com.hzdz.ls.db.impl.TopicMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicMapServer{

    @Autowired
    private TopicMapMapper topicMapMapper;

    public boolean inserTopic(String topicName, String QRCode){
        boolean result = false;
        if (StringUtil.checkEmpty(topicName)){
            return result;
        }

        if(topicMapMapper.queryByName(topicName) == null){
            return result;
        }

        int resultCode = topicMapMapper.insertTopicMap(topicName, QRCode);
        if (resultCode > 0){
              result = true;
        }
        return result;
    }

    public Result list(){
        List<TopicMap> topicList = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        topicList = topicMapMapper.queryAll();
        data.put("topicList",topicList);
        return new ResultDetail(data);
    }

    public Result delete(Integer id){
        Map<String, Object> data = new HashMap<>();
        if (topicMapMapper.deleteTopic(id) > 0){
            data.put("code", 0);
            data.put("msg", "删除成功");
        }else {
            data.put("code", -1);
            data.put("msg", "删除失败");
        }
        return new ResultDetail(data);
    }
}
