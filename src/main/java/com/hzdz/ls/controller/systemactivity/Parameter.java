package com.hzdz.ls.controller.systemactivity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

/**
*
*@author 豆豆
*时间:
*/
@Setter
@Getter
public class Parameter {

    private String activityName;
    private Integer belongManager;
    private String imagePath;
    private String shareText;
    private Integer templateId;
    private Integer[] moduleIds;
    private Integer[] activityIds;
    private Integer activityId;

}
