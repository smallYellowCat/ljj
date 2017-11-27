package com.hzdz.ls.service;

import com.hzdz.ls.common.*;
import com.hzdz.ls.db.entity.SystemTemplate;
import com.hzdz.ls.db.impl.SystemTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemTemplateServer {

    @Autowired
    private SystemTemplateMapper templateMapper;

    public Result addTemplate(String templateName, MultipartFile templateFile){
        Map<String, Object> data = new HashMap<>();
        if (templateFile == null || templateFile.isEmpty()){
            data.put("code", -1);
            data.put("msg", "参数非法");
            return new ResultDetail<>(data);
        }
        String templateUrl = "";
        try {
            templateUrl = FileUtil.upload4Stream(templateFile.getInputStream(), BaseVar.TEMPLATE_FILE, templateFile.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (StringUtil.checkEmpty(templateUrl)){
            data.put("code", -1);
            data.put("msg", "文件上传失败");
            return new ResultDetail<>(data);
        }

        SystemTemplate template = new SystemTemplate();
        template.setTemplateName(templateName);
        template.setTemplateUrl(templateUrl);
        template.setAddTime(new Date(System.currentTimeMillis()));
        if (templateMapper.addTemplate(template) < 1){
            data.put("code", -1);
            data.put("msg", "系统错误");
            return new ResultDetail<>(data);
        }

        return Result.SUCCESS;
    }

    public Result queryAll(){
        Map<String, Object> data = new HashMap<>();
        List<SystemTemplate> templates =  templateMapper.queryAll();
        data.put("templates", templates);
        return new ResultDetail<>(data);
    }
}
