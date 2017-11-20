package com.hzdz.ls.service;

import com.hzdz.ls.common.FileUtil;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.entity.SystemModule;
import com.hzdz.ls.db.impl.SystemModuleMapper;
import com.hzdz.ls.intercepter.MyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param moduleUrl 模块的html文件名
     * @return
     */
    public Result addModule(String moduleName, String moduleUrl, HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        //得到当前最后一条id
        int currentMaxId = systemModuleMapper.getMaxModuleId()+1;
        //递归创建文件夹
        boolean result = FileUtil.mkDirs(getPath(request, currentMaxId));
        if (!result){
            data.put("code", -1);
            data.put("msg", "创建目录失败");
            return new ResultDetail(data);
        }

        SystemModule module = new SystemModule();
        module.setModuleName(moduleName);
        module.setModuleUrl(getPath(request,currentMaxId) + moduleUrl);
        module.setAddTime(new Date(System.currentTimeMillis()));
        int resultNum = systemModuleMapper.insertModule(module);
        if (resultNum < 1){
            data.put("code", -1);
            data.put("msg", "数据库系统错误");
            return new ResultDetail(data);
        }

        data.put("code", 0);
        data.put("msg", "新增成功");
        return new ResultDetail(data);
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
}
