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

    public Result getQRCode(String imageFilePath, HttpServletRequest request) throws Exception {
        String CONTEXT_PATH = request.getSession().getServletContext().getRealPath("/");
        Map data = new HashMap();
        if (imageFilePath.isEmpty()){
            data.put("code", -1);
            data.put("msg", "照片上传失败！");
        }else {
            String imagePath = BaseVar.IMAGE_URL;
            String imageName = imageFilePath.substring(BaseVar.IMAGE_URL.length());
            //进行图片合成
            String codeUrl = imagePath + "code/"
                    + QRcodeUtil.encode(CONTEXT_PATH+"share.html?photo=" +imageFilePath,
                    "", CONTEXT_PATH + imagePath + "code/", imageName, true);
            data.put("code", 0);
            data.put("msg", "照片上传成功！");
            data.put("imageurl", imageFilePath);
            data.put("QRcode", codeUrl);

        }
        return new ResultDetail(data);
    }

}

