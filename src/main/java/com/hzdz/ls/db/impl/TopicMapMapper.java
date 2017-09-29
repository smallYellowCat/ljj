package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.TopicMap;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicMapMapper {
    int insertTopicMap(String topicName);
    TopicMap queryByName(String topicName);
}
