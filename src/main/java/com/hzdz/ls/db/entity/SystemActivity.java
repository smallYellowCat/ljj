package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SystemActivity {
    private int id;
    private String activityName;
    private Date addTime;
    private int belongManager;
    private int status;
    private String shareImage;
    private String shareText;
    private Date updateTime;
    private int templateId;
}
