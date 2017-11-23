package com.hzdz.ls.service;

import com.hzdz.ls.common.*;
import com.hzdz.ls.db.entity.SystemActivity;
import com.hzdz.ls.db.entity.SystemActivityImage;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.entity.SystemSession;
import com.hzdz.ls.db.impl.SystemActivityImageMapper;
import com.hzdz.ls.db.impl.SystemActivityMapper;
import com.hzdz.ls.db.impl.SystemManagerMapper;
import com.hzdz.ls.intercepter.MyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class SystemManagerServer {

    @Autowired
    private SystemManagerMapper systemManagerMapper;

    @Autowired
    private SystemActivityMapper systemActivityMapper;

    @Autowired
    private SystemActivityImageMapper systemActivityImageMapper;

    public Result addNewManager(String userAccount, String password, Integer managerType, HttpServletRequest request){
        Map<String, Object> data = new HashMap<String, Object>();
        SystemManager nowSystemManager = MyIntercepter.getManager(request);
        SystemManager systemManager = new SystemManager();
        if(nowSystemManager.getManagerType() != 1) {
            data.put("code", -1);
            data.put("msg", "非超管不能新增管理员，新增管理员失败！");
            return new ResultDetail(data);
        }
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

    public Result updatePassword(String password, HttpServletRequest request){
        Map<String, Object> data = new HashMap<String, Object>();
        if (password.length() > 16 || password.length() < 6){
            data.put("code", -1);
            data.put("msg", "密码长度不符合要求，新增管理员失败！");
            return new ResultDetail(data);
        }
        password = MD5Util.toMd5(password);
        SystemManager systemManager = MyIntercepter.getManager(request);
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
        SystemManager nowSystemManager = MyIntercepter.getManager(request);
        if(nowSystemManager.getManagerType() != 1) {
            data.put("code", -1);
            data.put("msg", "非超管不能冻结帐号！");
        }else {
            SystemManager systemManager = getManagerByID(id);
            if (systemManager.getFrozen() == 1){
                data.put("code", -1);
                data.put("msg", "该帐号已被冻结！");
            }else {
                if (systemManagerMapper.frozenManager(systemManager) < 1) {
                    data.put("code", -1);
                    data.put("msg", "冻结帐号失败！");
                } else {
                    data.put("code", 0);
                    data.put("msg", "冻结帐号成功！");
                }
            }
        }
        return new ResultDetail(data);
    }

    public Result resetPassword(Integer id, HttpServletRequest request){
        Map<String, Object> data = new HashMap<String, Object>();
        SystemManager systemManager = MyIntercepter.getManager(request);
        if(systemManager.getManagerType() == 1) {
            if (systemManagerMapper.resetPassword(id) < 1) {
                data.put("code", -1);
                data.put("msg", "重置密码失败！");
            } else {
                data.put("code", 0);
                data.put("msg", "重置密码成功！");
            }
        }else {
            data.put("code", -1);
            data.put("msg", "非超管不能重置密码");
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
            data.put("code", 0);
            data.put("msg", "查询成功！");
            data.put("managerList", managerList);
        }
        return new ResultDetail(data);
    }

    public SystemManager getManagerByID(int id){
        return systemManagerMapper.getManagerByID(id);
    }

    public Result loginVerify(String userAccount, String password, HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        password = MD5Util.toMd5(password);
        SystemManager systemManager = systemManagerMapper.loginVerify(userAccount, password);
        if(systemManager == null){
            data.put("code", -1);
            data.put("msg", "用户名或密码错误！");
        }else {
            if (systemManager.getFrozen() == 1){
                data.put("code", -1);
                data.put("msg", "该用户已被冻结！");
            }else {
                if(systemManagerMapper.loginSucceed(systemManager) < 1){
                    data.put("code", -1);
                    data.put("msg", "登录失败！");
                }else {
                    systemManager.setPassword("");
                    data.put("systemManager", systemManager);
                    data.put("code", 0);
                    data.put("msg", "登录成功！");
                    MyIntercepter.loginSuccess(request, systemManager.getId());
                }
            }
        }
        return new ResultDetail<>(data);
    }

    public Result logout(HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        MyIntercepter.loginOut(request);
        data.put("code", 0);
        data.put("msg", "退出成功！");
        return new ResultDetail<>(data);
    }

    @Transactional(rollbackForClassName = "Exception")
    public Result multiUploadImage(MultipartFile[] files, Integer activityId, HttpServletRequest request) throws Exception{
        Map<String, Object> data = new HashMap<>();
        String CONTEXT_PATH = request.getSession().getServletContext().getRealPath("/");
        SystemActivity systemActivity = systemActivityMapper.selectActivityById(activityId);
        //String activityName = systemActivity.getActivityName();
        if (systemActivity != null){
            //二维码名称，使用最后一个文件的名字
            String codeName = "";
            int n = files.length;
            List<String> imageList = new ArrayList<>();
            if (n < 1){
                data.put("code", -1);
                data.put("msg", "空文件！");
            }else {
                for (int i = 0; i < n; i++) {
                    MultipartFile file = files[i];
                    codeName = FileUtil.upload4Stream(file.getInputStream(),
                            CONTEXT_PATH + "upload/manager/"+activityId,
                            file.getOriginalFilename());
                    if (!StringUtil.checkEmpty(codeName)) {
                        data.put("code", -1);
                        data.put("msg", "照片上传失败！");
                        return new ResultDetail<>(data);
                    }else {
                        //将图片名称存起来
                        imageList.add("/upload/manager/" + activityId + "/" + codeName);
                    }
                }
                String codeUrl = "upload/manager/" + activityId + "/code/" + QRcodeUtil.encode(BaseVar.BASE_URL+"/index.html?activityId="+activityId,
                        "", CONTEXT_PATH + "upload/manager/" + activityId +"/code/", codeName, true);
                systemActivity.setQRCode(codeUrl);
                if(systemActivityMapper.updateQrCode(systemActivity) < 1){
                    data.put("code", -1);
                    data.put("msg", "照片上传失败！");
                }else {
                    for (String image : imageList){
                        if (systemActivityImageMapper.addActivityImage(image, activityId) < 1){
                            data.put("code", -1);
                            data.put("msg", "照片上传失败！");
                            return new ResultDetail<>(data);
                        }
                    }
                    data.put("code", 0);
                    data.put("msg", "照片上传成功！");
                }
            }
        } else {
            data.put("code", -1);
            data.put("msg", "该活动不存在！");
        }
        return new ResultDetail<>(data);
    }
}
