package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SystemTemplate {
    private int id;
    private String templateName;
    private String templateUrl;
    private Date addTime;
}
