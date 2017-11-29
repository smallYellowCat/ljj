package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemActivity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SystemActivityMapper {
    int addNewActivity(SystemActivity systemActivity);
    int updateShareImage(SystemActivity systemActivity);

    /**
     * 更新分享图片
     * @param activityId 活动id
     * @param imageUrl 图片地址
     * @return 被操作数
     */
    int updateShareImageById(@Param("activityId") Integer activityId, @Param("imageUrl") String imageUrl);

    /**
     * 更新分享文字
     * @param activityId 活动id
     * @param shareText 分享文字
     * @return 被操作数
     */
    int updateShareTextById(@Param("activityId") Integer activityId, @Param("shareText") String shareText);

    /**
     * 修改活动
     * @param activity 活动实体
     * @return 成功：1， 失败：0
     */
    int modifyActivity(SystemActivity activity);
    int deleteActivity(@Param("id") Integer id);
    int updateQrCode(SystemActivity systemActivity);
    SystemActivity selectActivityById(@Param("id") Integer id);
    String queryTemplateUrlById(int activityId);
    SystemActivity queryActivityById(@Param("id") Integer id);
    List<SystemActivity> queryActivityByOrdinaryManager(@Param("id") Integer id, @Param("activityName") String activityName, @Param("status") Integer status, @Param("belongManager") Integer belongManager);
    List<SystemActivity> queryActivityBySuperManager(@Param("id") Integer id, @Param("activityName") String activityName, @Param("status") Integer status, @Param("belongManager") Integer belongManager);
}
