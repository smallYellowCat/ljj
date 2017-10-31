package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SystemModule {
    private int id;
    private String moduleName;
    private Date addTime;
    private String moduleUrl;
}
