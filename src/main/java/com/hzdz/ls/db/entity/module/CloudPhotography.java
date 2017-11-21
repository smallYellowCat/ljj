package com.hzdz.ls.db.entity.module;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CloudPhotography {
    private int id;
    private int activityId;
    private String imageUrl;
    private Date addTime;
}
