package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemTemplateMapper {
    int addTemplate(SystemTemplate template);
    List<SystemTemplate> queryAll();
}
