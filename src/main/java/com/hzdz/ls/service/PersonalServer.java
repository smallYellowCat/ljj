package com.hzdz.ls.service;

import com.hzdz.ls.db.impl.PersonalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalServer {

    @Autowired
    private PersonalMapper personalMapper;
}
