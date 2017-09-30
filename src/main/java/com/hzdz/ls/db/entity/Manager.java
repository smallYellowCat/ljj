package com.hzdz.ls.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Manager {
    private int id;
    private String managerAccount;
    private String password;
    private Date registerTime;
    private Date loginTime;
}
