package com.hzdz.ls.service.module;

import com.hzdz.ls.common.BaseVar;
import com.hzdz.ls.common.FileUtil;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.SystemActivity;
import com.hzdz.ls.db.entity.SystemManager;
import com.hzdz.ls.db.entity.module.ProfessionalExhibition;
import com.hzdz.ls.db.impl.SystemActivityMapper;
import com.hzdz.ls.db.impl.module.ProfessionalExhibitionMapper;
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
public class ProfessionalExhibitionServer {

    @Autowired
    private ProfessionalExhibitionMapper professionalExhibitionMapper;

    @Autowired
    private SystemActivityMapper systemActivityMapper;

    @Transactional(rollbackForClassName = "Exception")
    public Result addNewProfessionalExhibition(MultipartFile image, String vrUrl, Integer activityId, HttpServletRequest request) throws Exception{
        Map<String, Object> data = new HashMap<>();
        // 得到项目根路径
        String CONTEXT_PATH = request.getSession().getServletContext().getRealPath("/");
        // 得到当前登录管理员
        Integer managerId = MyIntercepter.getManagerId(request);
        // 创建专业展示
        ProfessionalExhibition professionalExhibition = new ProfessionalExhibition();
        professionalExhibition.setVrUrl(vrUrl);
        professionalExhibition.setAddTime(new Date(System.currentTimeMillis()));
        professionalExhibition.setActivityId(activityId);
        //存放图片
        String imagePath = BaseVar.MANAGER_URL + managerId + "/" + activityId + "/" + BaseVar.PROFESSIONAL_EXHIBITION_URL;
        String imageName = FileUtil.upload4Stream(image.getInputStream(), CONTEXT_PATH + imagePath, image.getOriginalFilename());
        String imageUrl = imagePath + imageName;
        professionalExhibition.setImageUrl(imageUrl);
        if (professionalExhibitionMapper.addNewProfessionalExhibition(professionalExhibition) < 1){
            data.put("code", -1);
            data.put("msg", "新增失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }else {
            data.put("code", 0);
            data.put("msg", "新增成功！");
        }
        return new ResultDetail<>(data);
    }

    public Result queryProfessionalExhibition(Integer activityId){
        Map<String, Object> data = new HashMap<>();
        List<ProfessionalExhibition> list = professionalExhibitionMapper.queryProfessionalExhibition(activityId);
        if (list.size() == 0){
            data.put("code", -1);
            data.put("msg", "查询失败！");
        }else {
            data.put("list", list);
            data.put("code", 0);
            data.put("msg", "查询成功！");
        }
        return new ResultDetail<>(data);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result modify(Integer id, MultipartFile image, String vrUrl, Integer status, HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        Boolean flag = false;
        // 得到当前登录的管理员
        SystemManager systemManager = MyIntercepter.getManager(request);
        // 通过专业展示id得到专业展示
        ProfessionalExhibition professionalExhibition = professionalExhibitionMapper.getById(id);
        Integer activityId = professionalExhibition.getActivityId();
        // 通过专业展示所属活动得到活动
        SystemActivity systemActivity = systemActivityMapper.selectActivityById(activityId);
        // 得到活动所属管理员id
        Integer managerId = systemActivity.getBelongManager();
        // 判断当前登录管理员是否超管或与活动所属管理员id是否相同
        if (systemManager.getManagerType() == 1 || systemManager.getId() == managerId){
            if (image != null && !image.isEmpty()){
                try {
                    //存放图片
                    String imagePath = BaseVar.MANAGER_URL + managerId + "/" + activityId + "/" + BaseVar.PROFESSIONAL_EXHIBITION_URL;
                    String imageName = FileUtil.upload4Stream(image.getInputStream(), BaseVar.BASE_URL + imagePath, image.getOriginalFilename());
                    String imageUrl = imagePath + imageName;
                    String customaryImage = professionalExhibition.getImageUrl();
                    if (!FileUtil.delete(BaseVar.BASE_URL + customaryImage)){
                        flag = true;
                    }
                    professionalExhibition.setImageUrl(imageUrl);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (vrUrl != null){
                professionalExhibition.setVrUrl(vrUrl);
            }
            if (status != null){
                professionalExhibition.setStatus(status);
            }
            if (professionalExhibitionMapper.modify(professionalExhibition) < 1){
                flag = true;
            }
        }else {
            data.put("code", -1);
            data.put("msg", "非超管不能修改不属于自己的专业展示！");
        }
        if (flag){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            data.put("code", -1);
            data.put("msg", "修改失败！");
        }else {
            data.put("code", 0);
            data.put("msg", "修改成功！");
        }
        return new ResultDetail<>(data);
    }

    public Result delete(Integer id, HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        // 得到当前登录的管理员
        SystemManager systemManager = MyIntercepter.getManager(request);
        // 通过专业展示id得到专业展示
        ProfessionalExhibition professionalExhibition = professionalExhibitionMapper.getById(id);
        Integer activityId = professionalExhibition.getActivityId();
        // 通过专业展示所属活动得到活动
        SystemActivity systemActivity = systemActivityMapper.selectActivityById(activityId);
        // 得到活动所属管理员id
        Integer managerId = systemActivity.getBelongManager();
        // 判断当前登录管理员是否超管或与活动所属管理员id是否相同
        if (systemManager.getManagerType() == 1 || systemManager.getId() == managerId){
            if(professionalExhibitionMapper.delete(id) < 1){
                data.put("code", -1);
                data.put("msg", "删除失败！");
            }else {
                data.put("code", 0);
                data.put("msg", "删除成功！");
            }
        }else {
            data.put("code", -1);
            data.put("msg", "非超管不能删除不属于自己的专业展示！");
        }
        return new ResultDetail<>(data);
    }

}
