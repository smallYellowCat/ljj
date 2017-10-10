package com.hzdz.ls.service;

import com.hzdz.ls.common.FileUtil;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.Topic;
import com.hzdz.ls.db.entity.TopicMap;
import com.hzdz.ls.db.impl.TopicMapMapper;
import com.hzdz.ls.db.impl.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
    public boolean add(List<String> imageList, String QRCode, Integer topic_id){
        boolean result = false;
        int resultNum = 0;
        //若活动名称不存在，则返回错误
        if (topicMapMapper.queryTopicByid(topic_id) == null){
            return result;
        }
        resultNum = topicMapMapper.insertQRCodeToTopic(QRCode, topic_id);
        for (String list : imageList){
            resultNum = topicMapper.insertImage(topic_id, list);
        }
        if (resultNum > 0){
            result = true;
        }
        return result;
    }

    public Result list(Integer topic_id){
        Map<String, Object> data = new HashMap<>();
        List<String> imageUrls = new ArrayList<>();
        TopicMap topicMap = topicMapMapper.queryById(topic_id);

        imageUrls = topicMapper.queryImageById(topic_id);
        data.put("imageUrls", imageUrls);

        //topicMap = topicMapMapper.queryByName(topic);
        if (topicMap != null){
            data.put("QRCode", topicMap.getQRCode());
            data.put("topicName", topicMap.getTopicName());
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

    public Result delete(Integer id, HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        List<String> pathList = topicMapper.queryImageById(id);
        if(pathList == null && pathList.size() < 1){
            data.put("code", -1);
            data.put("msg", "参数错误");
            return new ResultDetail(data);
        }
        if (topicMapper.deleteImage(id) > 0){
            String path = request.getSession().getServletContext().getRealPath("/")+pathList.get(0);
            FileUtil.delete(path);
            data.put("code", 0);
            data.put("msg", "删除成功");
        }else {
            data.put("code", -1);
            data.put("msg", "删除失败");
        }
        return new ResultDetail(data);
    }
}
