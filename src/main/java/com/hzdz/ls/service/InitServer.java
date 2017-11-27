package com.hzdz.ls.service;

import com.hzdz.ls.common.Result;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

@Repository
public interface InitServer {
    Result init(HttpServletRequest request) throws Exception;
}
