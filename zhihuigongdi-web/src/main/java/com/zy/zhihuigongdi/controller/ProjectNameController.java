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
 * 项目的名称修改
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/28 0028 下午 1:26
 */
@Controller
@RequestMapping("/admin")
public class ProjectNameController extends BaseController{

    @Autowired
    private IndexService indexService;


    //跳转到项目名称修改页面
    @GetMapping("toProjectNameUpdate")
    public String toProjectNameUpdate(HttpSession session, HashMap map){
        try {
            User user = (User) session.getAttribute(Const.LOGIN_USER);
            String userName = user.getUserName();
            map.put("userName",userName);
            //查询先项目名称
            String projectName = indexService.queryProjectName();
            map.put("projectName", projectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查询现在项目名称
        return "/projectName/ProjectNameUpdate";
    }


    @PostMapping("/updateProjectName")
    @ResponseBody
    public Object ProjectNameUpdate(String id, String projectName){
        star();
        try {
            Integer num = indexService.updateDaysOfSafetyInProduction(projectName, id);
            if(num == 1){
                success(true);
            }
        }catch (Exception e){
            success(false);
        }
       return end();

    }
}
