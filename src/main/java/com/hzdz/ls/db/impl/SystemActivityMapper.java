package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemActivity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SystemActivityMapper {
    int addNewActivity(SystemActivity systemActivity);
    int updateShareImage(SystemActivity systemActivity);
    int deleteActivity(@Param("id") Integer id);
    int updateQrCode(SystemActivity systemActivity);
    SystemActivity selectActivityById(Integer id);
    String queryTemplateUrlById(int activityId);
}
