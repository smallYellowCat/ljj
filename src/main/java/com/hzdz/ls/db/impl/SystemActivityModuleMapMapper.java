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

    int deleteActivityById(@Param("activityId") Integer activityId);

    SwapData updateModuleOrder(SwapData swapData);

    List<SystemActivityModuleMap> queryModuleByActivityId(@Param("activityId") Integer activityId);

    int deteleByActivityId(@Param("activityId") Integer activityId);
    /**
     * 修改活动接口
     * @param moduleIds
     * @param activityId
     * @return
     */
    //int updateActivityModule(@Param("moduleIds") Integer[] moduleIds, @Param("activityId") Integer activityId);
}
