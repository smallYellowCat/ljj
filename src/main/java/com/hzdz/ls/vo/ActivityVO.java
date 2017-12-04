package com.hzdz.ls.vo;

import com.hzdz.ls.db.entity.SystemActivity;
import com.hzdz.ls.db.entity.SystemModule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
*
*@author 豆豆
*时间:
*/
@Setter
@Getter
@ToString
public class ActivityVO {
    private List<SystemModule> moduleList;
    //private SystemActivity systemActivity;
    private int id;
    private String activityName;
    private Date addTime;
    private int belongManager;
    private String userAccount;
    private int status;
    private String shareImage;
    private String shareText;
    private Date updateTime;
    private int templateId;
    private String QRCode;
}
