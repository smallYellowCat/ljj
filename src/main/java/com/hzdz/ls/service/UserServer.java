package com.hzdz.ls.service;

import com.hzdz.ls.common.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
*
*@author 豆豆
*时间:
*/
@Service
public class UserServer {

    public Result getQRCode(MultipartFile file, HttpServletRequest request) throws Exception {
        String CONTEXT_PATH = request.getSession().getServletContext().getRealPath("/");
        Map data = new HashMap();
        if (file.isEmpty()){
            data.put("code", -1);
            data.put("msg", "照片上传失败！");
        }else {
            String imageName = FileUtil.upload4Stream(file.getInputStream(),
                    CONTEXT_PATH+"upload/personal",
                    file.getOriginalFilename());
            if (!StringUtil.checkEmpty(imageName)){
                data.put("code", -1);
                data.put("msg", "照片上传失败！");
            }else {
                //注意u的大小写差异
                String fileUrl = BaseVar.PERSONAL_PHOTO_FILE+imageName;
                //进行图片合成

                String codeUrl = BaseVar.PERSONAL_CODE_FILE
                        + QRcodeUtil.encode(CONTEXT_PATH+"share.html?photo=" +fileUrl,
                        "", CONTEXT_PATH+BaseVar.PERSONAL_CODE_FILE, imageName, true);
                data.put("code", 0);
                data.put("msg", "照片上传成功！");
                data.put("imageurl", fileUrl);
                data.put("QRcode", codeUrl);
            }
        }
        return new ResultDetail(data);
    }

}
