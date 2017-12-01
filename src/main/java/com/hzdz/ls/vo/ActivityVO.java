package com.hzdz.ls.vo;

import com.hzdz.ls.db.entity.SystemActivity;
import com.hzdz.ls.db.entity.SystemModule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ActivityVO {
    private SystemActivity systemActivity;
    private List<SystemModule> moduleList;
}
