package com.hzdz.ls.service;

import com.hzdz.ls.common.Result;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
*
*@author 豆豆
*时间:
*/
@Repository
public interface InitServer {
    /**
     * 开机初始化
     * @param macAddress mac地址
     * @return 返回初始化信息
     * @throws Exception 异常信息
     */
    Result init(String macAddress);
    Result uploadImage(MultipartFile image);
}
