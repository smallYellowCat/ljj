package com.hzdz.ls.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InitActivityVO {
    private int id;
    private int managerId;
    private List<BelongModule> list;
}

