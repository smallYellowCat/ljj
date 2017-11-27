package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SystemManager {
    private int id;
    private String userAccount;
    private String password;
    private int managerType;
    private Date addTime;
    private Date lastLoginTime;
    private String remarks;
    private int frozen;
}
