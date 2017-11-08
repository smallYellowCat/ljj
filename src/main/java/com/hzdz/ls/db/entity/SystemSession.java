package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SystemSession {
    private String sid;
    private int managerId;
    private int status;
    private Date addTime;
    private Date outTime;
}
