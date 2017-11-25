package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SystemActivityModuleMap {
    private int id;
    private int activityId;
    private int moduleId;
    private Date addTime;
    private int sortNum;
}
