package com.hzdz.ls.service;

import com.hzdz.ls.common.Result;
import org.springframework.web.multipart.MultipartFile;

/**
*
*@author 豆豆
*时间:
*/
public interface SystemTemplateService {

    /**
     * 新增模版
     * @param templateName 模版名称
     * @param templateFile 模版文件
     * @return 返回操作信息
     */
    Result addTemplate(String templateName, MultipartFile templateFile);

    /**
     * 查询所有模版
     * @return 返回所有模版集合
     */
    Result queryAll();
}
