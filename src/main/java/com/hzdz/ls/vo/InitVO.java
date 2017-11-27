package com.hzdz.ls.vo;

import com.hzdz.ls.db.entity.SystemModule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InitVO {
    private Integer activityId;
    private Integer belongManager;
    List<SystemModule> array;
}
