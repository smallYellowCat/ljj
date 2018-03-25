package com.hzdz.ls.service.impl;


import com.hzdz.ls.common.*;

import com.hzdz.ls.db.entity.SystemModule;
import com.hzdz.ls.db.impl.SystemActivityModuleMapMapper;
import com.hzdz.ls.db.impl.SystemDeviceMapper;
import com.hzdz.ls.db.impl.SystemModuleMapper;
import com.hzdz.ls.service.InitServer;

import com.hzdz.ls.vo.InitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
*
*@author 豆豆
*时间:
*/
@Service
public class InitServerImpl implements InitServer {

    @Autowired
    private SystemModuleMapper systemModuleMapper;

    @Autowired
    private SystemDeviceMapper systemDeviceMapper;

    @Autowired
    private SystemActivityModuleMapMapper systemActivityModuleMapMapper;

    @Override
    public Result init(String macAddress){
        Map<String, Object> data = new HashMap<>();
        if (StringUtil.checkEmpty(macAddress) || macAddress.length() != BaseVar.DID_LENGTH){
            data.put("code", -1);
            data.put("msg", "参数非法");
            return new ResultDetail<>(data);
        }
        int activityId = systemDeviceMapper.queryActivityIdByDID(macAddress);
        int managerId = systemDeviceMapper.queryBelongManagerByDID(macAddress);
        List<SystemModule> modules = systemActivityModuleMapMapper.queryModuleIdsById(activityId);

        InitVO initVO = new InitVO();
        initVO.setActivityId(activityId);
        initVO.setBelongManager(managerId);
        initVO.setArray(modules);

        data.put("initVO", initVO);
        return new ResultDetail<>(data);
    }

    @Override
    public Result uploadImage(MultipartFile image) {
        Map<String, Object> data = new HashMap<>();
        // 进行文件上传操作
        String fileUrl = null;
        try {
            fileUrl = FileUtil.upload4Stream(image.getInputStream(), BaseVar.IMAGE_URL_TRUE, image.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!StringUtil.checkEmpty(fileUrl)) {
            data.put("code", -1);
            data.put("msg", "图片上传失败！");
        }else {
            data.put("code", 0);
            data.put("msg", "图片上传成功！");
            data.put("imagePath", BaseVar.IMAGE_URL + fileUrl);
        }
        return new ResultDetail<>(data);
    }
}
