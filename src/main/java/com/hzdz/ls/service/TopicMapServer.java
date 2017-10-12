package com.hzdz.ls.service;

import com.hzdz.ls.common.FileUtil;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.common.StringUtil;
import com.hzdz.ls.db.entity.TopicMap;
import com.hzdz.ls.db.impl.TopicMapMapper;
import com.hzdz.ls.db.impl.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicMapServer{

    @Autowired
    private TopicMapMapper topicMapMapper;
    @Autowired
    private TopicMapper topicMapper;

    /*public boolean inserTopic(String topicName, String QRCode){
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
    }*/

    public Result list(){
        List<TopicMap> topicList = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        topicList = topicMapMapper.queryAll();
        data.put("topicList",topicList);
        return new ResultDetail(data);
    }

    public Result delete(Integer id, HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        List<String> pathList = topicMapper.queryImageById(id);

        if (topicMapMapper.deleteTopic(id) > 0){
            for(int i = 0; i < pathList.size(); i++){
                String path = request.getSession().getServletContext().getRealPath("/")+pathList.get(i);
                pathList.set(i, path);
            }
            if (pathList.size() > 0) FileUtil.batchDelete(pathList);
            data.put("code", 0);
            data.put("msg", "删除成功");
        }else {
            data.put("code", -1);
            data.put("msg", "删除失败");
        }
        return new ResultDetail(data);
    }

    public Result createTopic(String topicName){
        Map<String, Object> data = new HashMap<>();
        //boolean result = false;
        int resultNum = 0;
        if (!StringUtil.checkEmpty(topicName)){
            data.put("code", -1);
            data.put("msg", "活动名为空");
        }

        resultNum = topicMapMapper.createTopic(topicName);

        if (resultNum < 1 ){
            data.put("code", -1);
            data.put("msg", "活动创建失败");
        }
        data.put("code", 0);
        data.put("msg", "活动创建成功");
        return new ResultDetail(data);
    }

    public String queryTopicName(Integer id){
        return topicMapMapper.queryTopicByid(id);
    }
}
