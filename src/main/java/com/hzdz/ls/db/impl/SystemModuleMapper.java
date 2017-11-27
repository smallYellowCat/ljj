package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemModule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemModuleMapper {
    int insertModule(SystemModule systemModule);
    int updateIcon(SystemModule systemModule);
    List<SystemModule> queryAllModule();
    SystemModule queryModuleById(@Param("id") Integer id);
}
