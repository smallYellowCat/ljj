package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.TopicMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicMapMapper {
    int insertTopicMap(@Param("topicName") String topicName, @Param("QRCode") String QRCode);
    TopicMap queryByName(String topicName);
}
