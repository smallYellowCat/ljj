package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.Topic;
import com.hzdz.ls.db.entity.TopicMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicMapper {
    int insertImage(@Param("topic_id") Integer topic_id, @Param("image_url") String image_url);
    List<String> queryImageById(Integer id);
    List<String> queryImageByTopicID(Integer id);
    List<Topic> queryImageByID(Integer id);
    int deleteImage(Integer id);
}
