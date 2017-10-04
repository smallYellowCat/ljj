package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenJson {
    private String access_token;
    private int expires_in;

}
