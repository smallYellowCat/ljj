package com.hzdz.ls.controller;

import com.hzdz.ls.common.*;
import com.hzdz.ls.db.entity.Personal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserContrller {


    /**
     * 测试用例
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public Result hello(){
        Personal personal = new Personal();
        personal.setId(1);
        personal.setImage_url("/image/girl.jpg");
        return new ResultDetail(personal);
    }

    /**
     * 上传照片之后返回二维码
     * @param file
     * @return
     */
    @RequestMapping(value = "/getQRCode", method = RequestMethod.POST)
    @ResponseBody
    public Result getQRCode(@RequestParam MultipartFile file, HttpServletRequest request) throws Exception {
        String CONTEXT_PATH = request.getSession().getServletContext().getRealPath("/");
        Map data = new HashMap();
        if (file.isEmpty()){
            data.put("code", -1);
            data.put("msg", "照片上传失败！");
        }else {
            String fileUrl = FileUtil.upload4Stream(file.getInputStream(),
                    CONTEXT_PATH+"upload/personal",
                    file.getOriginalFilename());
            if (!StringUtil.checkEmpty(fileUrl)){
                data.put("code", -1);
                data.put("msg", "照片上传失败！");
            }else {
                //注意u的大小写差异
                String fileurl = "/upload/personal/"+fileUrl;
                String codeUrl = "upload/personal/code/"+QRcodeUtil.encode(BaseVar.BASE_URL +fileurl,
                        "", CONTEXT_PATH+"upload/personal/code/", fileUrl, true);
                data.put("code", 0);
                data.put("msg", "照片上传成功！");
                data.put("QRcode", codeUrl);
                //data.put("fileurl", "/upload/personal/"+fileUrl);
            }
        }
        return new ResultDetail(data);
    }
}
