package com.hzdz.ls.service;

import com.hzdz.ls.common.StringUtil;
import com.hzdz.ls.db.entity.TopicMap;
import com.hzdz.ls.db.impl.TopicMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
