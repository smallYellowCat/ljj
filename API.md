# 智能交互机接口文档V2.0  
#### 说明：访问的服务器的根路径统一为101.132.147.167:8080/ljj

管理员表（SystemManager）  
managerType（管理员类型： 0 普通管理员， 1 超级管理员）  
frozen（是否冻结（1：冻结；0：不冻结））  
 
活动表（SystemActivity）   
status（活动状态： 0未开启， 1开启， 2已删除）    

设备表（SystemDevice）  
status（设备状态： 0未分配活动， 1已分配活动）  

会话表（SystemSession）  
status（会话状态： 0过期， 1正常， 2挤下线 ）  

专业展示表（ProfessionalExhibitionController）  
status（状态：0：未开启；1：开启；2：删除）
 


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
  "code": 0,
  "data": {
    "msg": "新增管理员成功！",
    "code": 0,
    "systemManager": {
      "addTime": "2017-11-30 14:13:18",
      "frozen": 0,
      "id": 33,
      "managerType": 0,
      "password": "e1cadcfba8873bc19a0ebebd16264b72",
      "remarks": "test",
      "userAccount": "user11"
    }
  },
  "msg": "请求成功"
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
  "code": 0,
  "data": {
    "msg": "创建活动成功！",
    "code": 0,
    "systemActivity": {
      "activityName": "上树",
      "addTime": "2017-11-30 14:22:27",
      "belongManager": 8,
      "id": 34,
      "shareImage": "upload/manager/8/34/2017113014222722194578585.jpg",
      "shareText": "树上",
      "status": 0,
      "templateId": 1,
      "updateTime": "2017-11-30 14:22:27"
    }
  },
  "msg": "请求成功"
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
  "code": 0,
  "data": {
    "msg": "新增设备成功！",
    "code": 0,
    "systemDevice": {
      "activityId": 0,
      "addTime": "2017-11-30 14:06:53",
      "deviceId": "10-C3-7B-23-3A-E2",
      "id": 4,
      "status": 0,
      "updateTime": "2017-11-30 14:06:53"
    }
  },
  "msg": "请求成功"
}
```


12：查询管理员拥有的设备接口（超管拥有所有的设备，普通管理员只能查看分配到的设备）  
路径：/back/systemDevice/list  
方法：POST  
传参：   
String deviceId（非必填,MAC地址17位）  
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
路径：/back/cloudPhotography/cloudUpload  
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

17：个人展示（用户云摄影上传照片返回二维码接口）  
路径：/machine/getQRCode  
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
路径：/machine/cloudPhotographyList  
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
	String description  
	MultipartFile icon  
返回：  
```json
{
  "code": 0,
  "data": {
    "msg": "创建模块成功！",
    "code": 0,
    "systemModule": {
      "addTime": "2017-11-30 14:20:37",
      "description": "小小小",
      "icon": "upload/module/13/2017113014203763167227206.jpg",
      "id": 13,
      "moduleName": "大大大"
    }
  },
  "msg": "请求成功"
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
	Integer id1  （主键记录id）  
	Integer id2  （主键记录id）  
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
```json
{
  "code": 0,
  "data": {
    "msg": "新增成功",
    "code": 0,
    "systemTemplate": {
      "addTime": "2017-11-30 15:09:04",
      "id": 2,
      "templateName": "狂打豆豆",
      "templateUrl": "2017113015090468619907247.jpg"
    }
  },
  "msg": "请求成功"
}
```

25.查询所有模版接口  
路径：/back/systemTemplate/query  
方法：get  
传参：   
返回：  
```json
{
  "code": 0,
  "data": {
    "templates": [
      {
        "addTime": "2017-11-30 15:07:16",
        "id": 1,
        "templateName": "狂打豆豆",
        "templateUrl": "2017113015071615860988403.jpg"
      },
      {
        "addTime": "2017-11-30 15:09:05",
        "id": 2,
        "templateName": "狂打豆豆",
        "templateUrl": "2017113015090468619907247.jpg"
      }
    ]
  },
  "msg": "请求成功"
}
```

26.删除模块（超管接口）  
路径：/back/systemModule/delete   
方法：delete   
传参：  
Integer moduleId 模块id     
返回：
```json
{
  "code": 0,
  "msg": "请求成功"
}
```

27.修改活动
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
```json
{
  "code": 0,
  "msg": "请求成功"
}
```
  

28.查询活动  
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
    "activityVOList": [
      {
        "moduleList": [
          {
            "addTime": "2017-11-24 22:53:52",
            "description": "天天打豆豆",
            "icon": "upload/module/12/2017112422535181293060585.jpg",
            "id": 12,
            "moduleName": "打豆豆"
          },
          {
            "addTime": "2017-12-01 19:50:19",
            "description": "小小小",
            "icon": "upload/module/14/2017120119501948018943749.jpg",
            "id": 14,
            "moduleName": "大大大"
          }
        ],
          "activityName": "下树1",
          "addTime": "2017-11-30 14:22:27",
          "belongManager": 8,
          "userAccount": "admin",
          "id": 34,
          "shareImage": "upload/manager/8/34/2017113016070409340965778.jpg",
          "shareText": "树下1",
          "status": 0,
          "templateId": 88888,
          "updateTime": "2017-11-30 14:22:27"
      }
    ],
    "code": 0
  },
  "msg": "请求成功"
}
```

29.新增专业展示  
路径:/professionalExhibition/addNewProfessionalExhibition  
方法:POST  
传参:   
     MultipartFile image  
     String vrUrl  
     Integer activityId  
返回:
```json
{
  "code": 0,
  "data": {
    "msg": "新增成功！",
    "code": 0,
    "professionalExhibition": {
      "activityId": 32,
      "addTime": "2017-11-30 13:49:55",
      "id": 4,
      "imageUrl": "upload/manager/8/32/2/2017113013495572577704532.jpg",
      "status": 0,
      "vrUrl": "www.baidu.com"
    }
  },
  "msg": "请求成功"
}
```

30.查看专业展示  
路径:/machine/professionalExhibitionList  
方法:POST    
传参:   
Integer activityId  
返回:  
```json
{
  "code": 0,
  "data": {
    "msg": "查询成功！",
    "code": 0,
    "list": [
      {
        "activityId": 28,
        "addTime": "2017-11-25 00:00:00",
        "id": 1,
        "imageUrl": "Z:\\idea\\workspace\\ljj\\target\\ljj\\upload/manager/8/28/2//2017112513302691518552864.jpg",
        "status": 0,
        "vrUrl": "gdsfgfsdfghsd"
      },
      {
        "activityId": 28,
        "addTime": "2017-11-25 00:00:00",
        "id": 2,
        "imageUrl": "upload/manager/8/28/2//2017112513323482240606263.jpg",
        "status": 0,
        "vrUrl": "gdsfgfsdfghsd"
      },
      {
        "activityId": 28,
        "addTime": "2017-11-25 00:00:00",
        "id": 3,
        "imageUrl": "upload/manager/8/28/2/2017112513333684860941331.jpg",
        "status": 0,
        "vrUrl": "gdsfgfsdfghsd"
      }
    ]
  },
  "msg": "请求成功"
}
```

31.批量删除云摄影  
路径:back/cloudPhotography/deleteCloudUpload       
方法:POST      
传参:   
Integer[] ids  
返回:  
```json
{
  "code": 0,
  "data": {
    "msg": "删除成功！",
    "code": 0
  },
  "msg": "请求成功"
}
```

32.获取微信签名
路径:machine/getSign     
方法:POST      
传参:   
String url
返回:  
```json
{
  "code": 0,
  "data": {
    "appid": "wxa8edc412f0f47073",
    "nonceStr": "5ecac616-2c51-401e-9f4f-2d82980f6749",
    "signature": "bb0cedda5ee2d45e7f9e91c2736e50574774159f",
    "timestamp": "1512018228"
  },
  "msg": "请求成功"
}
```

33.修改专业展示  
路径:back/professionalExhibition/modify     
方法:POST      
传参:   
Integer id   
MultipartFile image （非必填）  
String vrUrl        （非必填）  
Integer status      （非必填）  
返回:  
```json
{
  "code": 0,
  "data": {
    "msg": "修改成功！",
    "code": 0
  },
  "msg": "请求成功"
}
```

34.删除专业展示  
路径:back/professionalExhibition/delete   
方法:POST      
传参:   
Integer id 
返回:  
```json
{
  "code": 0,
  "data": {
    "msg": "删除成功！",
    "code": 0
  },
  "msg": "请求成功"
}
```
35：解冻管理员接口（只有超管可以调用）  
路径：/back/systemManager/thawManager  
方法：POST  
传参：   
	Integer id  被解冻管理员id
返回：  
```json
{
  "code":0,
  "data":
  {
    "msg":"解冻帐号成功！",
    "code":0
  },
  "msg":"请求成功"
}
```

36:修改活动状态  
路径 ： /back/systemActivity/modifyStatus  
方法：POST  
传参：  
 int status  0:关闭 1：开启  
 int activityId  
返回：  
```json
{
  "code":0,
  "msg":"请求成功"
}
```
