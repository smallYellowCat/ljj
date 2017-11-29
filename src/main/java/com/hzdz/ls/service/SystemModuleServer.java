package com.hzdz.ls.service;

import com.hzdz.ls.common.*;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.entity.SystemModule;
import com.hzdz.ls.db.impl.SystemModuleMapper;
import com.hzdz.ls.intercepter.MyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemModuleServer {

    @Autowired
    private SystemModuleMapper systemModuleMapper;


    /**
     * 新增模块（模块的文件手动上传到/ljj/module/moduleId的目录下，先在后台新增模块然后上传文件）
     * @param moduleName 模块中文名
     * @return
     */
    @Transactional(rollbackForClassName = "Exception")
    public Result addModule(String moduleName, String description, MultipartFile icon, HttpServletRequest request) throws Exception{
        Map<String, Object> data = new HashMap<>();
        boolean roollerBackFlag = false;
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleName(moduleName);
        systemModule.setDescription(description);
        systemModule.setAddTime(new Date(System.currentTimeMillis()));
        if (systemModuleMapper.insertModule(systemModule) < 1){
            data.put("code", -1);
            data.put("msg", "新建模版失败！");
        }else {
            Integer moduleId = systemModule.getId();
            // 获取当前项目根路径
            String path = request.getSession().getServletContext().getRealPath("/");
            String iconPath = BaseVar.MODULE_URL + moduleId;
            String iconUrl = FileUtil.upload4Stream(icon.getInputStream(), path + iconPath, icon.getOriginalFilename());
            if (!StringUtil.checkEmpty(iconUrl)) {
                data.put("code", -1);
                data.put("msg", "图片上传失败！");
                roollerBackFlag = true;
            }else {
                // 更新图片路径
                systemModule.setIcon(iconPath + "/" + iconUrl);
                if (systemModuleMapper.updateIcon(systemModule) < 1) {
                    data.put("code", -1);
                    data.put("msg", "创建模块失败！");
                    roollerBackFlag = true;
                }else {
                    data.put("code", 0);
                    data.put("msg", "创建模块成功！");
                }
            }
        }
        //判断是否回滚事务
        if (roollerBackFlag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return new ResultDetail<>(data);
    }

    private String getPath(HttpServletRequest request, int moduleId){
        return request.getSession().getServletContext().getRealPath("/")+"module\\"+moduleId+"\\";
    }

    public Result queryModule(HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        SystemManager systemManager = MyIntercepter.getManager(request);
        if(systemManager.getManagerType() != 1){
            data.put("code", -1);
            data.put("msg", "非超管不能查询！");
        }else{
            List<SystemModule> list = systemModuleMapper.queryAllModule();
            data.put("code", 0);
            data.put("msg", "查询成功！");
            data.put("systemModuleList", list);
        }
        return new ResultDetail<>(data);
    }

    public Result deleteModule(Integer moduleId, HttpServletRequest request){
        SystemManager manager = MyIntercepter.getManager(request);
        if (manager != null && manager.getManagerType() == 1){
            if (systemModuleMapper.deleteModuleById(moduleId) == 1){
                return Result.SUCCESS;
            }
        }
        return Result.FAILURE;
    }
}
