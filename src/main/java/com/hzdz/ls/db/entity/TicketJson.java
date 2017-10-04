package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketJson {
    private int errcode;
    private String errmsg;
    private String ticket;
    private String expires_in;
}
