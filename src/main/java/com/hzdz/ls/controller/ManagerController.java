package com.hzdz.ls.controller;

import com.aliyuncs.exceptions.ClientException;
import com.hzdz.ls.common.*;
import com.hzdz.ls.db.entity.Manager;
import com.hzdz.ls.db.impl.ManagerMapper;
import com.hzdz.ls.service.TopicMapServer;
import com.hzdz.ls.service.TopicServer;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private TopicServer topicServer;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private TopicMapServer topicMapServer;

    /**
     * 发送短信验证码
     * @param phoneNum
     * @return
     * @throws ClientException
     * @throws InterruptedException
     */
    @RequestMapping(value = "/send/code", method =  RequestMethod.POST)
    @ResponseBody
    public Result getCode(@RequestParam String phoneNum) throws ClientException, InterruptedException {
        Map data = new HashMap();
        if (!StringUtil.checkPhoneNum(phoneNum)) {
            return new Result(-1, "手机号码错误");
        }
        int code = NumberUtil.createNum(6);
        if(!SMSUtil.sendMsg(code)){
            data.put("code", -1);
            data.put("msg", "验证码发送失败");
        }else {
            data.put("code", 0);
            data.put("codeNum", code);
            data.put("msg", "验证码发送成功");
        }
        return new ResultDetail<>(data);
    }


    /**
     * 注册管理员
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestParam String name, @RequestParam String password){
        Map<String, Object> data = new HashMap<>();
        //对密码进行MD5加密
        password = MD5Util.toMd5(password);
        Manager manager = new Manager();
        Date current = new Date(System.currentTimeMillis());
        manager.setManagerAccount(name);
        manager.setPassword(password);
        manager.setRegisterTime(current);
        manager.setLoginTime(current);

        if(managerMapper.insert(manager) < 1){
            data.put("code", -1);
            data.put("msg", "新增管理员失败！");
        }else {
            data.put("code", 0);
            data.put("msg", "新增管理员成功！");
        }

        return new ResultDetail<>(data);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestParam String name, @RequestParam String password){
        Map<String, Object> data = new HashMap<>();
        //对密码进行MD5加密
        password = MD5Util.toMd5(password);

        if (managerMapper.login(name, password) != null){
            data.put("code", 0);
            data.put("msg", "登录成功！");
        }else {
            data.put("code", -1);
            data.put("msg", "用户不存在，请检查帐号密码！");
        }
        return new ResultDetail<>(data);
    }


    /**
     * 管理员多文件上传
     * @param files
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/multiupload", method = RequestMethod.POST)
    @ResponseBody
    public Result multiUploadImage(@RequestParam MultipartFile[] files,
                                   @RequestParam Integer topic_id,
                                   HttpServletRequest request) throws Exception {
        String CONTEXT_PATH = request.getSession().getServletContext().getRealPath("/");
        String topic = topicMapServer.queryTopicName(topic_id);
        Map<String, Object> data =new HashMap<>();
        if (!StringUtil.checkEmpty(topic)){
            data.put("code", -1);
            data.put("msg", "参数错误，活动不存在！");
            //data.put("QRcode", codeUrl);
            return new ResultDetail(data);
        }

        //二维码名称，使用最后一个文件的名字
        String codeName = "";
        int n = files.length;
        List<String> imageList = new ArrayList<>();
        //String[] images = new String[n];
        if (n < 1){
            data.put("code", -1);
            data.put("msg", "空文件！");
            return new ResultDetail(data);
        }

        for (int i = 0; i < n; i++) {
            MultipartFile file = files[i];
            codeName = FileUtil.upload4Stream(file.getInputStream(),
                    CONTEXT_PATH + "upload/manager/"+topic,
                    file.getOriginalFilename());
            if (!StringUtil.checkEmpty(codeName)) {
                data.put("code", -1);
                data.put("msg", "照片上传失败！");
                return new ResultDetail(data);
            }
            //将图片名称存起来
            imageList.add("/upload/manager/" + topic + "/" + codeName);

        }
        String codeUrl = "upload/manager/" + topic + "/code/" + QRcodeUtil.encode(BaseVar.BASE_URL+"/index.html?topic_id="+topic_id,
                "", CONTEXT_PATH + "upload/manager/" + topic +"/code/", codeName, true);
        boolean result = topicServer.add(imageList, codeUrl, topic_id);
        if (!result){
            data.put("code", -1);
            data.put("msg", "照片上传失败！");
            return new ResultDetail(data);
        }

        data.put("code", 0);
        data.put("msg", "照片上传成功！");
        //data.put("QRcode", codeUrl);

        return new ResultDetail(data);
    }

    /**
     * 活动管理
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result cloudManager(){
        return new ResultDetail(topicMapServer.list());
    }

    /**
     * 删除活动
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public Result delete(@RequestParam Integer id, HttpServletRequest request){
        return topicMapServer.delete(id, request);
    }

    /**
     * 创建活动
     * @param topicName
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public Result createTopic(@RequestParam String topicName){
        return topicMapServer.createTopic(topicName);
    }

}
