package com.hzdz.ls.service;

import com.hzdz.ls.db.entity.Topic;
import com.hzdz.ls.db.entity.TopicMap;
import com.hzdz.ls.db.impl.TopicMapMapper;
import com.hzdz.ls.db.impl.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicServer {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private TopicMapMapper topicMapMapper;

    //@Transactional
    public boolean add(String topicNmae, List<String> imageList){
        boolean result = false;
        int resultNum = 0;
        topicMapMapper.insertTopicMap(topicNmae);
        TopicMap topicMap = null;
        topicMap = topicMapMapper.queryByName(topicNmae);
        if (topicMap == null){
            return result;
        }
        int id = topicMap.getTopic_id();
        for (String list : imageList){
            Topic topic = new Topic();
            topic.setImage_url(list);
            topic.setTopic_id(id);
            resultNum = topicMapper.insertTopic(topic);
        }
        if (resultNum > 0){
            result = true;
        }
        return result;
    }
}
