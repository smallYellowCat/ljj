package com.hzdz.ls.service;

import com.hzdz.ls.common.MD5Util;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.impl.SystemManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SystemManagerServer {

    @Autowired
    private SystemManagerMapper systemManagerMapper;


    public Result addNewManager(String userAccount, String password, Integer managerType, HttpServletRequest request){
        Map<String, Object> data = new HashMap<String, Object>();
        SystemManager systemManager = new SystemManager();
        if (userAccount.length() > 16 || userAccount.length() < 6){
            data.put("code", -1);
            data.put("msg", "用户名长度不符合要求，新增管理员失败！");
            return new ResultDetail(data);
        }
        if (password.length() > 16 || password.length() < 6){
            data.put("code", -1);
            data.put("msg", "密码长度不符合要求，新增管理员失败！");
            return new ResultDetail(data);
        }
        if(systemManagerMapper.isRepeat(userAccount) > 0){
            data.put("code", -1);
            data.put("msg", "该用户已存在，新增管理员失败！");
            return new ResultDetail(data);
        }
        password = MD5Util.toMd5(password);
        systemManager.setUserAccount(userAccount);
        systemManager.setPassword(password);
        systemManager.setAddTime(new Date(System.currentTimeMillis()));
        systemManager.setManagerType(managerType);
        if (systemManagerMapper.addNewManager(systemManager) < 1){
            data.put("code", -1);
            data.put("msg", "新增管理员失败！");
        }else{
            data.put("code", 0);
            data.put("msg", "新增管理员成功！");
        }
        return new ResultDetail(data);
    }

    public Result updatePassword(int id, String password, HttpServletRequest request){
        Map<String, Object> data = new HashMap<String, Object>();
        if (password.length() > 16 || password.length() < 6){
            data.put("code", -1);
            data.put("msg", "密码长度不符合要求，新增管理员失败！");
            return new ResultDetail(data);
        }
        password = MD5Util.toMd5(password);
        SystemManager systemManager = new SystemManager();
        systemManager.setId(id);
        systemManager.setPassword(password);
        if (systemManagerMapper.updatePassword(systemManager) < 1){
            data.put("code", -1);
            data.put("msg", "修改密码失败！");
        }else{
            data.put("code", 0);
            data.put("msg", "修改密码成功！");
        }
        return new ResultDetail(data);
    }

    public Result frozenManager(int id, HttpServletRequest request){
        Map<String, Object> data = new HashMap<String, Object>();
        SystemManager systemManager = new SystemManager();
        systemManager.setId(id);
        if (systemManagerMapper.frozenManager(systemManager) < 1){
            data.put("code", -1);
            data.put("msg", "冻结帐号失败！");
        }else{
            data.put("code", 0);
            data.put("msg", "冻结帐号成功！");
        }
        return new ResultDetail(data);
    }

    public Result resetPassword(Integer id){
        Map<String, Object> data = new HashMap<String, Object>();
        if(systemManagerMapper.resetPassword(id) < 1){
            data.put("code", -1);
            data.put("msg", "重置密码失败！");
        }else {
            data.put("code", -1);
            data.put("msg", "重置密码成功！");
        }
        return new ResultDetail(data);
    }

    public Result selectAllManager(){
        Map<String, Object> data = new HashMap<String, Object>();
        List<SystemManager> managerList = systemManagerMapper.selectAllManager();
        if(managerList.size() == 0){
            data.put("code", -1);
            data.put("msg", "查询失败！");
        }else {
            data.put("code", -1);
            data.put("msg", "查询成功！");
            data.put("managerList", managerList);
        }
        return new ResultDetail(data);
    }
}
