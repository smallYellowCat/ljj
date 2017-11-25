package com.hzdz.ls.service.module;

import com.hzdz.ls.common.BaseVar;
import com.hzdz.ls.common.FileUtil;
import com.hzdz.ls.common.Result;
import com.hzdz.ls.common.ResultDetail;
import com.hzdz.ls.db.entity.module.ProfessionalExhibition;
import com.hzdz.ls.db.impl.module.ProfessionalExhibitionMapper;
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
public class ProfessionalExhibitionServer {

    @Autowired
    private ProfessionalExhibitionMapper professionalExhibitionMapper;

    @Transactional(rollbackForClassName = "Exception")
    public Result addNewProfessionalExhibition(MultipartFile image, String vrUrl, Integer activityId, HttpServletRequest request) throws Exception{
        Map<String, Object> data = new HashMap<>();
        String CONTEXT_PATH = request.getSession().getServletContext().getRealPath("/");
        Integer managerId = MyIntercepter.getManagerId(request);
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

}
