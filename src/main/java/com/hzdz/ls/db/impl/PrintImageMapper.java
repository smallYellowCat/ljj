package com.hzdz.ls.db.impl;

import com.hzdz.ls.db.entity.PrintImage;

public interface PrintImageMapper {
    int insert(PrintImage printImage);
    String queryUrlById(int id);
}
