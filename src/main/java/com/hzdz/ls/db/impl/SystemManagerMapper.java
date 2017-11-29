package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.SystemManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemManagerMapper {
    int isRepeat(@Param("userAccount") String userAccount);
    int addNewManager(SystemManager systemManager);
    int updatePassword(SystemManager systemManager);
    int frozenManager(SystemManager systemManager);
    int resetPassword(Integer id);
    List<SystemManager> selectAllManager(@Param("id") Integer id, @Param("userAccount") String userAccount, @Param("frozen") Integer frozen);
    SystemManager getManagerByID(int id);
    SystemManager queryManagerByID(@Param("id") Integer id);
    SystemManager loginVerify(@Param("userAccount") String userAccount, @Param("password") String password);
    int loginSucceed(SystemManager systemManager);
}
