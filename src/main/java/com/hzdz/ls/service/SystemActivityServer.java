package com.hzdz.ls.service;

import com.hzdz.ls.common.*;
import com.hzdz.ls.db.entity.SwapData;
import com.hzdz.ls.db.entity.SystemActivity;
import com.hzdz.ls.db.entity.SystemActivityModuleMap;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.impl.SystemActivityMapper;
import com.hzdz.ls.db.impl.SystemActivityModuleMapMapper;
import com.hzdz.ls.intercepter.MyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackForClassName = "Exception")
public class SystemActivityServer {

    @Autowired
    private SystemActivityMapper systemActivityMapper;

    @Autowired
    private SystemActivityModuleMapMapper systemActivityModuleMapMapper;


    public Result addNewActivity(String activityName,
                                 Integer belongManager,
                                 Integer templateId,
                                 MultipartFile shareImage,
                                 String shareText,
                                 Integer[] moduleIds,
                                 HttpServletRequest request) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        boolean roollerBackFlag = false;
        // 判断上传文件是否为空
        if (shareImage.isEmpty()) {
            data.put("code", -1);
            data.put("msg", "图片上传失败！");
        } else {
            // 获取当前项目根路径
            String path = request.getSession().getServletContext().getRealPath("/");
            // 创建活动对象，进行插入操作，获取新增后的主键ID
            SystemActivity systemActivity = new SystemActivity();
            systemActivity.setActivityName(activityName);
            systemActivity.setAddTime(new Date(System.currentTimeMillis()));
            systemActivity.setBelongManager(belongManager);
            systemActivity.setStatus(0);
            systemActivity.setShareImage(path);
            systemActivity.setShareText(shareText);
            systemActivity.setUpdateTime(new Date(System.currentTimeMillis()));
            systemActivity.setTemplateId(templateId);
            if (systemActivityMapper.addNewActivity(systemActivity) < 1) {
                data.put("code", -1);
                data.put("msg", "创建活动失败！");
                roollerBackFlag = true;
            } else {
                // 新增后主键ID作为文件夹名
                int systemActivityId = systemActivity.getId();
                String imagePath = BaseVar.MANAGER_URL + belongManager + "/" + systemActivityId;
                // 进行文件上传操作
                String fileUrl = FileUtil.upload4Stream(shareImage.getInputStream(), path + imagePath, shareImage.getOriginalFilename());
                if (!StringUtil.checkEmpty(fileUrl)) {
                    data.put("code", -1);
                    data.put("msg", "图片上传失败！");
                    roollerBackFlag = true;
                } else {
                    // 更新图片路径
                    systemActivity.setId(systemActivityId);
                    systemActivity.setShareImage(imagePath + "/" + fileUrl);
                    if (systemActivityMapper.updateShareImage(systemActivity) < 1) {
                        data.put("code", -1);
                        data.put("msg", "创建活动失败！");
                        roollerBackFlag = true;
                    } else {
                        // 新增活动与模版间的映射
                        SystemActivityModuleMap systemActivityModuleMap = null;
                        boolean setMapFlag = false;
                        // 遍历模版数组ID
                        for (int i = 0; i < moduleIds.length; i++) {
                            systemActivityModuleMap = new SystemActivityModuleMap();
                            systemActivityModuleMap.setActivityId(systemActivityId);
                            systemActivityModuleMap.setModuleId(moduleIds[i]);
                            systemActivityModuleMap.setSortNum(i + 1);
                            systemActivityModuleMap.setAddTime(new Date(System.currentTimeMillis()));
                            if (systemActivityModuleMapMapper.addNewMap(systemActivityModuleMap) < 1) {
                                setMapFlag = true;
                                break;
                            }
                        }
                        if (setMapFlag) {
                            data.put("code", -1);
                            data.put("msg", "添加活动模块失败！");
                            roollerBackFlag = true;
                        } else {
                            data.put("systemActivity", systemActivity);
                            data.put("code", 0);
                            data.put("msg", "创建活动成功！");
                        }
                    }
                }
            }
        }
        //判断是否回滚事务
        if (roollerBackFlag) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return new ResultDetail(data);
    }

    @Transactional(rollbackForClassName = "Exception")
    public Result deleteActivity(Integer activityId, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        boolean roollerBackFlag = false;
        SystemManager systemManager = MyIntercepter.getManager(request);
        SystemActivity systemActivity = systemActivityMapper.selectActivityById(activityId);
        int belongId = systemActivity.getBelongManager();
        if (systemManager.getManagerType() == 1 || belongId == systemManager.getId()) {
            if (systemActivityMapper.deleteActivity(activityId) < 1) {
                data.put("code", -1);
                data.put("msg", "删除活动失败！");
            } else {
                if (systemActivityModuleMapMapper.deleteActivityById(activityId) < 1) {
                    roollerBackFlag = true;
                    data.put("code", -1);
                    data.put("msg", "删除活动失败！");
                } else {
                    data.put("code", 0);
                    data.put("msg", "删除活动成功！");
                }
            }
        } else {
            data.put("code", -1);
            data.put("msg", "删除活动失败，非超管不能删除不属于自己的活动！");
        }
        //判断是否回滚事务
        if (roollerBackFlag) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return new ResultDetail(data);
    }

    public Result updateShareImage(Integer activityId, MultipartFile shareImage, String shareText, HttpServletRequest request) throws IOException {
        Map<String, Object> data = new HashMap<>();
        // 获取原来的活动
        SystemActivity systemActivity = systemActivityMapper.selectActivityById(activityId);
        if (shareImage == null || shareImage.isEmpty()) {
            systemActivity.setShareText(shareText);
            systemActivity.setUpdateTime(new Date(System.currentTimeMillis()));
            if (systemActivityMapper.updateShareImage(systemActivity) < 1) {
                data.put("code", -1);
                data.put("msg", "更新图文信息失败！");
            } else {
                data.put("code", 0);
                data.put("msg", "更新图文信息成功！");
            }
        } else {
            // 获取活动所属管理员
            Integer belongManager = systemActivity.getBelongManager();
            // 获取图片原来的路径
            String customaryPath = systemActivity.getShareImage();
            // 获取服务器根路径
            String originalPath = request.getSession().getServletContext().getRealPath("/");
            // 拼接图片路径
            String imagePath = BaseVar.MANAGER_URL + belongManager + "/" + activityId;
            // 进行文件上传操作
            String fileUrl = FileUtil.upload4Stream(shareImage.getInputStream(), originalPath + imagePath, shareImage.getOriginalFilename());
            if (!StringUtil.checkEmpty(fileUrl)) {
                data.put("code", -1);
                data.put("msg", "图片上传失败！");
            } else {
                // 设置新的图片
                systemActivity.setShareImage(imagePath + "/" + fileUrl);
                if (shareText != null) {
                    systemActivity.setShareText(shareText);
                }
                systemActivity.setUpdateTime(new Date(System.currentTimeMillis()));
                if (systemActivityMapper.updateShareImage(systemActivity) < 1) {
                    data.put("code", -1);
                    data.put("msg", "更新图文信息失败！");
                } else {
                    FileUtil.delete(originalPath + customaryPath);
                    data.put("code", 0);
                    data.put("msg", "更新图文信息成功！");
                }
            }
        }
        return new ResultDetail(data);
    }

    public Result updateModuleOrder(Integer id1, Integer id2, HttpServletRequest request) throws IOException {
        Map<String, Object> data = new HashMap<>();
        SwapData swapData = new SwapData();
        swapData.setId1(id1);
        swapData.setId2(id2);
        systemActivityModuleMapMapper.updateModuleOrder(swapData);
        if (swapData.getResult() == 1) {
            data.put("code", 0);
            data.put("msg", "更新排序成功！");
        } else {
            data.put("code", -1);
            data.put("msg", "更新排序失败！");
        }
        return new ResultDetail<>(data);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result modifyActivity(Integer activityId,
                                 String activityName,
                                 Integer belongManager,
                                 Integer templateId,
                                 MultipartFile shareImage,
                                 String shareText,
                                 Integer[] moduleIds,
                                 HttpServletRequest request) {
        //事务回滚标志
        boolean transactionFlag = false;
        SystemManager manager = MyIntercepter.getManager(request);
        if (manager == null || manager.getManagerType() != 1) {
            return Result.FAILURE;
        }

        if (activityId == null || !StringUtil.checkEmpty(activityName) || belongManager == null
                || templateId == null || !StringUtil.checkEmpty(shareText)) {
            return Result.FAILURE;
        }

        SystemActivity activity = new SystemActivity();
        activity.setId(activityId);
        activity.setActivityName(activityName);
        activity.setBelongManager(belongManager);
        activity.setTemplateId(templateId);
        activity.setShareText(shareText);

        if (shareImage != null && !shareImage.isEmpty()) {
            try {
                SystemActivity customaryActivity = systemActivityMapper.selectActivityById(activityId);
                FileUtil.delete(BaseVar.BASE_URL + customaryActivity.getShareImage());
                //图片上传
                String imagePath = BaseVar.MANAGER_URL + belongManager + "/" + activityId + "/";
                String imageName = FileUtil.upload4Stream(shareImage.getInputStream(),
                         BaseVar.BASE_URL + imagePath,
                        shareImage.getOriginalFilename());
                String imageUrl = imagePath + imageName;
                if (StringUtil.checkEmpty(imageUrl)) {
                    activity.setShareImage(imageUrl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        int num = systemActivityMapper.modifyActivity(activity);

        if (moduleIds != null && moduleIds.length > 0) {
            //更新模块映射
            systemActivityModuleMapMapper.deleteActivityById(activityId);
            // 新增活动与模版间的映射
            SystemActivityModuleMap systemActivityModuleMap = null;
            boolean setMapFlag = false;
            for (int i = 0; i < moduleIds.length; i++) {
                systemActivityModuleMap = new SystemActivityModuleMap();
                systemActivityModuleMap.setActivityId(activityId);
                systemActivityModuleMap.setModuleId(moduleIds[i]);
                systemActivityModuleMap.setSortNum(i + 1);
                systemActivityModuleMap.setAddTime(new Date(System.currentTimeMillis()));
                if (systemActivityModuleMapMapper.addNewMap(systemActivityModuleMap) < 1) {
                    setMapFlag = true;
                    break;
                }
            }
            if (!setMapFlag){
                transactionFlag = true;
            }
        }

        //事务回滚
        if (!transactionFlag) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.FAILURE;
        }

        return Result.SUCCESS;
    }


    public Result queryActivity(Integer id, String activityName, Integer belongManager, Integer status, HttpServletRequest request) {
        SystemManager systemManager = MyIntercepter.getManager(request);
        if (systemManager.getManagerType() == 1) {
            return queryActivityBySuperManager(id, activityName, belongManager, status);
        } else {
            return queryActivityByOrdinaryManager(id, activityName, systemManager, status);
        }
    }

    public Result queryActivityByOrdinaryManager(Integer id, String activityName, SystemManager systemManager, Integer status) {
        Map<String, Object> data = new HashMap<>();
        List<SystemActivity> list = systemActivityMapper.queryActivityByOrdinaryManager(id, activityName, status, systemManager.getId());
        if (list != null && list.size() != 0) {
            data.put("activityList", list);
            data.put("code", 0);
            data.put("msg", "查询成功！");
        } else {
            data.put("code", -1);
            data.put("msg", "查询失败！");
        }
        return new ResultDetail<>(data);
    }

    public Result queryActivityBySuperManager(Integer id, String activityName, Integer belongManager, Integer status) {
        Map<String, Object> data = new HashMap<>();
        List<SystemActivity> list = systemActivityMapper.queryActivityBySuperManager(id, activityName, status, belongManager);
        if (list != null && list.size() != 0) {
            data.put("activityList", list);
            data.put("code", 0);
            data.put("msg", "查询成功！");
        } else {
            data.put("code", -1);
            data.put("msg", "查询失败！");
        }
        return new ResultDetail<>(data);
    }

}
