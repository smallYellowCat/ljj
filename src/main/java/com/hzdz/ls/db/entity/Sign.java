package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Sign {

    private String appid;
    private String timestamp;
    private String nonceStr;
    private String jsapi_ticket;
    private String signature;
}
