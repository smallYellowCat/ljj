package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserPhotoMap {
    private int id;
    private String photoUrl;
    private int userId;
    private Date addTime;
}
