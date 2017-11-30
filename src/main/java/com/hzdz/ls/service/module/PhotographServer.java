package com.hzdz.ls.service.module;

import com.hzdz.ls.common.*;
import com.hzdz.ls.intercepter.MyIntercepter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class PhotographServer {

    public Result getQRCode(Integer activityId, MultipartFile file, HttpServletRequest request) throws Exception {
        String CONTEXT_PATH = request.getSession().getServletContext().getRealPath("/");
        Integer managerId = MyIntercepter.getManagerId(request);
        Map data = new HashMap();
        if (file.isEmpty()){
            data.put("code", -1);
            data.put("msg", "照片上传失败！");
        }else {
            String imagePath = BaseVar.MANAGER_URL + managerId + "/" + activityId + "/" + BaseVar.PERSONAL_PHOTOGRAPHY_URL;
            String imageName = FileUtil.upload4Stream(file.getInputStream(),
                    CONTEXT_PATH + imagePath,
                    file.getOriginalFilename());
            if (!StringUtil.checkEmpty(imageName)){
                data.put("code", -1);
                data.put("msg", "照片上传失败！");
            }else {
                //注意u的大小写差异
                String fileUrl = imagePath + imageName;
                //进行图片合成
                String codeUrl = imagePath + "code/"
                        + QRcodeUtil.encode(CONTEXT_PATH+"share.html?photo=" +fileUrl,
                        "", CONTEXT_PATH + imagePath + "code/", imageName, true);
                data.put("code", 0);
                data.put("msg", "照片上传成功！");
                data.put("imageurl", fileUrl);
                data.put("QRcode", codeUrl);
            }
        }
        return new ResultDetail(data);
    }

}

