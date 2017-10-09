package com.hzdz.ls.service;

import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.Topic;
import com.hzdz.ls.db.entity.TopicMap;
import com.hzdz.ls.db.impl.TopicMapMapper;
import com.hzdz.ls.db.impl.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicServer {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private TopicMapMapper topicMapMapper;

    //@Transactional
    public boolean add(String topicNmae, List<String> imageList, String QRCode){
        boolean result = false;
        int resultNum = 0;
        //若活动名称已存在，则只添加图片路径信息,
        if (topicMapMapper.queryByName(topicNmae) == null){
            topicMapMapper.insertTopicMap(topicNmae, QRCode);
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

    public Result list(String topic){
        Map<String, Object> data = new HashMap<>();
        List<String> imageUrls = new ArrayList<>();
        TopicMap topicMap = topicMapMapper.queryByName(topic);
        if (topicMap != null){
            imageUrls = topicMapper.queryImageById(topicMap.getTopic_id());
            data.put("imageUrls", imageUrls);
        }
        topicMap = topicMapMapper.queryByName(topic);
        if (topicMap != null){
            data.put("QRCode", topicMap.getQRCode());
        }
        return new ResultDetail(data);
    }

    public Result detail(Integer id){
        List<Topic> list = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        list = topicMapper.queryImageByID(id);
        data.put("list", list);
        return new ResultDetail(data);
    }

    public Result delete(Integer id){
        Map<String, Object> data = new HashMap<>();
        if (topicMapper.deleteImage(id) > 0){
            data.put("code", 0);
            data.put("msg", "删除成功");
        }else {
            data.put("code", -1);
            data.put("msg", "删除失败");
        }
        return new ResultDetail(data);
    }
}
