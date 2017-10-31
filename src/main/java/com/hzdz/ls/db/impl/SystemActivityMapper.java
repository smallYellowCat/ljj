package com.hzdz.ls.db.impl;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemActivityMapper {

    String queryTemplateUrlById(int activityId);
}
