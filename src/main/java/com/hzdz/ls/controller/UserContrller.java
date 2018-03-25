package com.hzdz.ls.controller;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.hzdz.ls.common.*;
import com.hzdz.ls.db.entity.Personal;
import com.hzdz.ls.db.entity.Sign;
import com.hzdz.ls.service.UserServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@RequestMapping("/test")
//@RestController
//@CrossOrigin(value = "*", maxAge = 3600)
public class UserContrller {

    @Autowired
    private UserServer userServer;

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
        return new ResultDetail<>(personal);
    }

    /**获取微信签名*/
    @RequestMapping(value = "/getSign", method = RequestMethod.GET)
    @ResponseBody
    public Result verify(String url){
        Sign sign = WxUtil.getSign(url);
        return new ResultDetail<>(sign);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改活动", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moduleIds", value = "moduleIds", required = true, dataType = "intt", paramType = "form", allowMultiple = true)
    })
    public Result modifyActivity(@RequestParam Integer[] moduleIds){
        Map<String, Object> data = new HashMap<>();
        data.put("id", moduleIds);
        return new ResultDetail<>(data);
    }



    /**
     * 上传照片之后返回二维码
     * @param file
     * @return
     */
    @RequestMapping(value = "/getQRCode", method = RequestMethod.POST)
    @ResponseBody
    public Result getQRCode(@RequestParam MultipartFile file, HttpServletRequest request) throws Exception {
        return userServer.getQRCode(file, request);
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public Result test1(HttpServletRequest request) throws Exception{
        Map<String, Object> data = new HashMap<>();
        String mac = IPUtil.getMACAddress(request);
        data.put("mac", mac);
        return new ResultDetail<>(data);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    @ResponseBody
    public Result test2(HttpServletRequest request) throws Exception{
        Map<String, Object> data = new HashMap<>();
        String path = request.getSession().getServletContext().getRealPath("/");
        path = path + "upload/111/1";
        Boolean b = FileUtil.batchDeleteByRecursion(path);
        data.put("b", b);
        return new ResultDetail<>(data);
    }

    @RequestMapping(value = "/test3", method = RequestMethod.POST/*, consumes = "multipart/form-data", headers = "content-type=multipart/form-data"*/)
    @ResponseBody
    @ApiOperation(value = "测试", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户名", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form"),
    })
    public Result test3(@RequestParam String userAccount,
                        @RequestParam String password,
                        @ApiParam MultipartFile file,
                        HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("userAccount", userAccount);
        data.put("password", password);
        return new ResultDetail<>(data);
    }

}
