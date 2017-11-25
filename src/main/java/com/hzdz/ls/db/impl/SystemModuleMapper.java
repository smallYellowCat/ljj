package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemModule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemModuleMapper {
    int insertModule(SystemModule systemModule);
    int updateIcon(SystemModule systemModule);
    List<SystemModule> queryAllModule();
}
