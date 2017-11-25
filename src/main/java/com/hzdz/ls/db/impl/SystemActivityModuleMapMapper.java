package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SwapData;
import com.hzdz.ls.db.entity.SystemActivityModuleMap;
import com.hzdz.ls.db.entity.SystemModule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemActivityModuleMapMapper {

    /**
     * 通过活动id查询当前活动拥有的所有模块
     * @param activityId
     * @return
     */
    List<SystemModule> queryModuleIdsById(int activityId);

    int addNewMap(SystemActivityModuleMap systemActivityModuleMap);

    int deleteActivityById(Integer activityId);

    SwapData updateModuleOrder(SwapData swapData);
}
