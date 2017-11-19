package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemActivityImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemActivityImageMapper {
    int addActivityImage(@Param("image_url") String image, @Param("activity_id") int activityId);
}
