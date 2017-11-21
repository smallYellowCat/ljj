package com.hzdz.ls.db.impl.module;

import com.github.pagehelper.Page;
import com.hzdz.ls.db.entity.module.CloudPhotography;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudPhotographyMapper {
    Page<CloudPhotography> queryAllByActivityId(int activityId);
}
