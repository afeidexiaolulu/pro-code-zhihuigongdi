package com.zy.zhihuigongdi.controller;

import com.zy.zhihuigongdi.bean.User;
import com.zy.zhihuigongdi.service.IndexService;
import com.zy.zhihuigongdi.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 *
 * 安全工時controller
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/24 0024 下午 3:04
 */

@Controller
@RequestMapping("/admin")
public class SafetyTimeController extends BaseController{

    @Autowired
    private IndexService indexService;

    //去往更新页面跳转
    @GetMapping("/toSafetyTimeUpdate")
    public String toSafetyTimeUpdate(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        //
        String day = indexService.queryDaysOfSafetyInProduction();

        map.put("userName",user.getUserName());
        map.put("safetyTime",day);
        return "/safetyTime/SafetyTimeUpdate";
    }

    //更新
    @PostMapping("/updateSafetyTime")
    @ResponseBody
    public Object updateSafetyTime(String id, String safetyTimeUpdate){
        star();
        try {
            Integer updateNum = indexService.updateDaysOfSafetyInProduction(safetyTimeUpdate, id);
            success(updateNum == 1);
        }catch (Exception e){
            success(false);
            message("更新安全时间失败");
        }
        return end();
    }

}
