package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemActivity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemActivityMapper {
    int addNewActivity(SystemActivity systemActivity);
    int updateShareImage(SystemActivity systemActivity);
    int deleteActivity(Integer id);
    String queryTemplateUrlById(int activityId);
}
