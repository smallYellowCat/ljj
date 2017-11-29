# 智能交互机接口文档V2.0  
#### 说明：访问的服务器的根路径统一为101.132.147.167:8080/ljj


1：登录验证接口  
路径：/back/systemManager/loginVerify  
方法：POST  
传参：   
    String userAccount   
    String password  
返回：
```json
{
  "code":0,
  "data":
  {
    "msg":"登录成功！",
    "code":0,
    "systemManager":
    {
      "addTime":"2017-11-03 15:37:48",
      "frozen":0,
      "id":5,
      "managerType":0,
      "password":"",
      "userAccount":"user555"
    }
  },
  "msg":"请求成功"
}
```

2：退出登录  
路径：/back/systemManager/logout  
方法：POST  
传参：   
返回：     
```json
{
  "code":0,
  "data":
  {
    "msg":"退出成功！",
    "code":0
  },
  "msg":"请求成功"
}
```

3：重置密码接口（只有超管可以调用）  
路径：/back/systemManager/resetPassword  
方法：POST  
传参：   
	Integer id  
返回：  
```json
{
  "code":0,
  "data":
  {
    "msg":"重置密码成功！",
    "code":0
  },
  "msg":"请求成功"
}
```


4：新增管理员接口  
路径：/back/systemManager/addNewManager  
方法：POST  
传参：     
	String userAccount	（长度6-16）  
	String password		（长度6-16）  
	Integer managerType	（0：普通管理员；1：超级管理员）  
	String remarks  备注  
返回：  
```json
{
  "code":0,
  "data":
  {
    "msg":"新增管理员成功！",
    "code":0
  },
  "msg":
  "请求成功"
}
```


5：修改密码接口  
路径：/back/systemManager/updatePassword  
方法：POST  
传参：   
	Intege id  
	String password  
	String oldPassword  
返回：  
```json
{
  "code":0,
  "data":
  {
  "msg":"修改密码成功！",
  "code":0
  },
    "msg":"请求成功"
}
```

6：冻结管理员接口（只有超管可以调用）  
路径：/back/systemManager/frozenManager  
方法：POST  
传参：   
	Integer id  
返回：  
```json
{
  "code":0,
  "data":
  {
    "msg":"冻结帐号成功！",
    "code":0
  },
  "msg":"请求成功"
}
```

7：查询所有管理员（不含超管）  
路径：/back/systemManager/queryAllManager  
方法：POST  
传参：   Integer id          （非必填）
        String userAccount  （非必填）
        Integer frozen      （非必填）
返回：   
```json
{
  "code":0,
  "data":
  {
    "msg":"查询成功！",
    "code":0,
    "managerList":
    [
      {
        "addTime":"2017-11-01 17:08:31",
        "frozen":0,
        "id":1,
        "managerType":0,
        "userAccount":"user1"
      },
      {
        "addTime":"2017-11-01 17:09:13",
        "frozen":1,
        "id":2,
        "managerType":0,
        "userAccount":"user2"
      }
    ]
  },
  "msg":"请求成功"
}
```

8：创建活动接口  
路径：/back/systemActivity/addNewActivity  
方法：POST  
传参：   
	String activityName,  
	Integer belongManager,  
	MultipartFile shareImage,  
	String shareText,  
	Integer templateId,  
	Integer[] moduleIds,  
返回：
```json
{
  "code":0,
  "data":
  {
  "msg":"创建活动成功！",
  "code":0
  },
  "msg":"请求成功"
}
```

9：删除活动接口（普通管理员只能删自己的，超管可以删所有）  
路径：/back/systemActivity/deleteActivity  
方法：POST  
传参：   
	Integer activityId  
返回：  
```json
{
  "code":0,
  "data":
  {
    "msg":"删除活动成功！",
    "code":0
  },
  "msg":"请求成功"
}
```

10：更新分享图文信息接口  
路径：/back/systemActivity/updateShareImage  
方法：POST  
传参：   
	Integer activityId  
	MultipartFile shareImage  （非必填）
	String shareText          （非必填）
返回：  
```json
{
  "code":0,
  "data":
  {
  "msg":"更新图文分享信息成功！",
  "code":0
  },
  "msg":"请求成功"
}
```


11：新增设备接口  
路径：/back/systemDevice/add  
方法：POST  
传参：   
	String DID （MAC地址）  
返回：  
```json
{
  "code":0,
  "data":
  {
    "msg":"新增设备成功！",
    "code":0
  },
  "msg":"请求成功"
}
```


12：查询管理员拥有的设备接口（超管拥有所有的设备，普通管理员只能查看分配到的设备）  
路径：/back/systemDevice/list  
方法：GET  
传参：   
返回：  
```json
{
  "code":0,
  "data":
  {
    "deviceList":
    [
      {
        "activityId":0,
        "addTime":"2017-11-13 20:14:56",
        "deviceId":"0123456789123456",
        "id":1,
        "status":0,
        "updateTime":"2017-11-13 20:14:56"
      },
      {
        "activityId":0,
        "addTime":"2017-11-15 23:14:08",
        "deviceId":"0123456789123458",
        "id":3,
        "status":0,
        "updateTime":"2017-11-15 23:14:08"
      }
    ]
  },
  "msg":"请求成功"
}
```

13：删除设备接口  
路径：/back/systemDevice/delete  
方法：POST  
传参：int id(数据库主键id)   
返回：  
```json
{
  "code":0,
  "msg":"请求成功"
}
```


14：分配设备给管理员接口（超管操作）  
路径：/back/systemDevice/allocateDeviceToManager  
方法：POST  
传参：   
	Integer id  
	Integer managerId  
返回：  
```json
{
  "code":0,
  "data":
  {
    "msg":"分配成功",
    "code":0
  },
  "msg":"请求成功"
}
```

15：分配活动给设备接口  
路径：/back/systemDevice/allocateActivityToDevice  
方法：POST  
传参：   
	Integer id		（设备id）  
	Integer activityId	（活动id）  
返回：  
```json
{
  "code":0,
  "data":
  {
    "msg":"分配活动成功",
    "code":0
  },
  "msg":"请求成功"
}
```


16：管理员云摄影上传照片接口  
路径：/back/systemManager/cloudUpload  
方法：POST  
传参：   
	MultipartFile[] files  
	Integer activityId  
返回：  
```json
{
  "code":0,
  "data":
  {
    "msg":"照片上传成功",
    "code":0
  },
  "msg":"请求成功"
}
```

17：用户云摄影上传照片返回二维码接口  
路径：/user/getQRCode  
方法：POST  
传参：   
	MultipartFile[] file  
返回：  
```json
{
    "code":0,
    "data":
        {
            "msg":"照片上传成功！",
            "code":0,
            "QRcode":"upload/personal/code/2017112317203690686252447.jpg	",
            "imageurl":"upload/personal/2017112317203690686252447.jpg"
        },
    "msg":"请求成功"
}
```

18：云摄影图片查看接口  
路径：/cloudPhotography/list  
方法：POST  
传参：   
	int id  
	int pageNo  
	int pageSize  
返回：  
```json
{
    "code":0,
    "data":
        {
            "array":
            [
                {
                    "activityId":28,
                    "addTime":"2017-11-23 14:26:15",
                    "id":3,
                    "imageUrl":"/upload/manager/6/28/1/2017112314261482743037669.jpg"
                }
            ],
            "pageNo":1,
            "pageSize":5,
            "total":4,
            "totalPage":1
        },
    "msg":"请求成功"
}
```

19：新增模块接口  
路径：/back/systemModule/insert  
方法：POST  
传参：   
	String moduleName  
	String moduleUrl  
返回：  
```json
{
    "code":0,
    "data":
        {
            "msg":"新增成功",
            "code":0
        },
    "msg":"请求成功"
}
```

20：查询模块接口  
路径：/back/systemModule/queryModule  
方法：POST  
传参：   
返回：  
```json
{
  "code": 0,
  "data": {
    "msg": "查询成功！",
    "code": 0,
    "systemModuleList": [
      {
        "addTime": "2017-10-31 15:55:29",
        "id": 2,
        "moduleName": "AA",
        "moduleUrl": "AA"
      },
      {
        "addTime": "2017-10-31 17:38:49",
        "id": 10,
        "moduleName": "bb",
        "moduleUrl": "E:\\IntelijIdea_workspace\\ljj\\target\\ljjmodule/10/bb"
      },
      {
        "addTime": "2017-10-31 17:42:02",
        "id": 11,
        "moduleName": "bb",
        "moduleUrl": "E:\\IntelijIdea_workspace\\ljj\\target\\ljj\\module\\11\\bb"
      }
    ]
  },
  "msg": "请求成功"
}
```




21：改变模版顺序接口  
路径：/back/systemActivity/updateModuleOrder  
方法：POST  
传参：   
	Integer id1  
	Integer id2  
返回：  
```json
{
    "code":0,
    "data":
        {
            "msg":"更新排序成功！",
            "code":0
        },
        "msg":"请求成功"
}
```

22：查询模块接口  
路径：/back/systemModule/queryModule  
方法：POST  
传参：   
返回：  
```json
{
    "code":0,
    "data":
        {
            "msg":"查询成功！",
            "code":0,
            "systemModuleList":
            [
                {
                    "addTime":"2017-10-31 15:55:29",
                    "description":"",
                    "icon":"",
                    "id":2,
                    "moduleName":"AA"
                }
            ]
        },
    "msg":"请求成功"
}
```

23.开机初始化接口  
路径：/init/init 
方法：POST  
传参： String macAddress  
返回：  


24.新增模版接口  
路径：/back/systemTemplate/add  
方法：POST  
传参： String templateName,  模版名  
        MultipartFile templateFile  模版文件  
返回：  


25.查询所有模版接口  
路径：/back/systemTemplate/query  
方法：get  
传参：   
返回：  

26.删除模块（超管接口）  
路径：/back/systemModule/delete   
方法：delete   
传参：  
Integer moduleId 模块id     
返回：

修改活动
路径：/back/systemActivity/modify   
方法：post   
传参：   
  Integer activityId   
  String activityName  
  Integer belongManager  
  MultipartFile shareImage  非必填
  String shareText  
  Integer templateId  
  Integer[] moduleIds  
返回：
  

26.查询活动
路径:/back/systemActivity/queryActivity
方法:POST
传参:Integer id              （非必填）
    String activityName     （非必填）
    Integer belongManager   （非必填）
    Integer status          （非必填）
返回:
```json
{
  "code": 0,
  "data": {
    "msg": "查询成功！",
    "code": 0,
    "activityList": [
      {
        "activityName": "天天打豆豆13",
        "addTime": "2017-11-06 16:37:42",
        "belongManager": 111,
        "id": 14,
        "shareImage": "Z:\\idea\\workspace\\ljj\\target\\ljj\\",
        "shareText": "吃饭睡觉打豆豆3",
        "status": 0,
        "templateId": 222,
        "updateTime": "2017-11-06 16:37:42"
      }
    ]
  },
  "msg": "请求成功"
}
```