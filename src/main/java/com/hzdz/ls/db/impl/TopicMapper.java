package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicMapper {
    int insertTopic(Topic topic);
    List<String> queryImageById(Integer id);
}
