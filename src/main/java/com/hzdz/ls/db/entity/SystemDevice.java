package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SystemDevice {
    private int id;
    private String deviceId;
    private Date addTime;
    private int belongManager;
    private Date updateTime;
    private int status;
    private int activityId;
}
