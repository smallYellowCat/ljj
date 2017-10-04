package com.hzdz.ls.service;

import com.hzdz.ls.db.entity.Topic;
import com.hzdz.ls.db.entity.TopicMap;
import com.hzdz.ls.db.impl.TopicMapMapper;
import com.hzdz.ls.db.impl.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        //若活动名称已存在，则只添加图片路径信息
        if (topicMapMapper.queryByName(topicNmae) == null){
            topicMapMapper.insertTopicMap(topicNmae);
        }
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

    public List<String> list(String topic){
        List<String> imageUrls = new ArrayList<>();
        TopicMap topicMap = topicMapMapper.queryByName(topic);
        if (topicMap != null){
            imageUrls = topicMapper.queryImageById(topicMap.getTopic_id());
        }
        return imageUrls;
    }
}
