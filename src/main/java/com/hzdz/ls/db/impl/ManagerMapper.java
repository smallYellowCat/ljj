package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerMapper {
    int insert(Manager manager);
    Manager login(@Param("managerAccount") String managerAccount, @Param("password") String password);
}
