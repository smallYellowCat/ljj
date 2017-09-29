package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicMapper {
    int insertTopic(Topic topic);
}
