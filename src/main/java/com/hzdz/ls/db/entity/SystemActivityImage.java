package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemActivityImage {
    private int id;
    private String image_url;
    private int activity_id;
}
