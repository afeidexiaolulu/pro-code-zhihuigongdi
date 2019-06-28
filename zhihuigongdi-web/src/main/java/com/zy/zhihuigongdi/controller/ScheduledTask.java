package com.zy.zhihuigongdi.controller;

import com.zy.zhihuigongdi.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Autowired
    private IndexService indexService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduledTask(){
        //查询当前安全生产天数
        String day = indexService.queryDaysOfSafetyInProduction();
        Integer newDay = Integer.parseInt(day)+1;
        //每隔一天加一
        Integer num = indexService.updateDaysOfSafetyInProduction(newDay.toString(),"2");
        System.out.println("更新成功,当前安全生产天数为:"+newDay);
    }
}
