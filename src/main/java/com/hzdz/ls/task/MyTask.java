package com.hzdz.ls.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.hzdz.ls.db.entity.SystemSession;
import com.hzdz.ls.db.impl.SystemSessionMapper;
import com.hzdz.ls.task.impl.ITask;
//import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask implements ITask {

    @Autowired
    private SystemSessionMapper systemSessionMapper;

    @Scheduled(cron = "0 0/5 *  * * ? ")   //每分钟执行一次
    @Override
    public void checkSession() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date(System.currentTimeMillis())) + "----------Session状态检测任务执行开始");

        List<SystemSession> list = systemSessionMapper.queryEffectiveSession();
        for (SystemSession systemSession : list){
            Date now = new Date(System.currentTimeMillis());
            if (now.getTime() > systemSession.getOutTime().getTime()){
                systemSession.setStatus(0);
                systemSessionMapper.updateSessionStatus(systemSession);
            }
        }

        System.out.println(sdf.format(new Date(System.currentTimeMillis())) + "----------Session状态检测任务执行结束");
    }
}