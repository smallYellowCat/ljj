package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.TopicMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicMapMapper {
    int insertTopicMap(@Param("topicName") String topicName, @Param("QRCode") String QRCode);
    TopicMap queryById(Integer topic_id);
    List<TopicMap> queryAll();
    int deleteTopic(Integer id);
    int createTopic(String topicName);
    int insertQRCodeToTopic(@Param("QRCode") String QRCode, @Param("topic_id") Integer topic_id);
    String queryTopicByid(Integer topic_id);
}
