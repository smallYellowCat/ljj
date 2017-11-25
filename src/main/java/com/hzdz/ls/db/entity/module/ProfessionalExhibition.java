package com.hzdz.ls.db.entity.module;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProfessionalExhibition {
    private int id;
    private String imageUrl;
    private String vrUrl;
    private Date addTime;
    private int status;
    private int activityId;
}
