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

    /***
     * 删除模块
     * @param moduleId 模块id
     * @return 被删除模块数
     */
    int deleteModuleById(Integer moduleId);

    List<SystemModule> getModuleByActivityId(@Param("activityId") Integer activityId);

}
