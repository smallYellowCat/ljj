package com.hzdz.ls.controller;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.hzdz.ls.common.*;
import com.hzdz.ls.db.entity.Personal;
import com.hzdz.ls.db.entity.Sign;
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
    @ResponseJSONP
    public Result hello(){
        Personal personal = new Personal();
        personal.setId(1);
        personal.setImage_url("/image/gi1111111rl.jpg");
        return new ResultDetail(personal);
    }

    /**
     * 测试用例
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ResponseJSONP
    public Result postHello(){
        Personal personal = new Personal();
        personal.setId(1);
        personal.setImage_url("/image/gi1111111rl.jpg");
        return new ResultDetail(personal);
    }

    /**获取微信签名*/
    @RequestMapping(value = "/getSign", method = RequestMethod.GET)
    @ResponseBody
    public Result verify(String url){
        Sign sign = WxUtil.getSign(url);
        return new ResultDetail(sign);
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
                //进行图片合成
                String originImage = CONTEXT_PATH + fileurl;
                String frameImage =CONTEXT_PATH + "frame.jpg";//模版图片
                ImageUtil.mergeImage(frameImage, originImage);

                String codeUrl = "upload/personal/code/"+QRcodeUtil.encode(BaseVar.BASE_URL +fileurl,
                        "", CONTEXT_PATH+"upload/personal/code/", fileUrl, true);
                data.put("code", 0);
                data.put("msg", "照片上传成功！");
                data.put("imageurl", fileurl);
                data.put("QRcode", codeUrl);
            }
        }
        return new ResultDetail(data);
    }
}
