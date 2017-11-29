package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemActivity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SystemActivityMapper {
    int addNewActivity(SystemActivity systemActivity);
    int updateShareImage(SystemActivity systemActivity);
    int deleteActivity(@Param("id") Integer id);
    int updateQrCode(SystemActivity systemActivity);
    SystemActivity selectActivityById(@Param("id") Integer id);
    String queryTemplateUrlById(int activityId);
    SystemActivity queryActivityById(@Param("id") Integer id);
    List<SystemActivity> queryActivityByOrdinaryManager(@Param("id") Integer id, @Param("activityName") String activityName, @Param("status") Integer status, @Param("belongManager") Integer belongManager);
    List<SystemActivity> queryActivityBySuperManager(@Param("id") Integer id, @Param("activityName") String activityName, @Param("status") Integer status, @Param("belongManager") Integer belongManager);
}
