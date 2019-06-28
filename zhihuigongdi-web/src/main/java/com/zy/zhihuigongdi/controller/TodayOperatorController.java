package com.zy.zhihuigongdi.controller;

import com.zy.zhihuigongdi.bean.User;
import com.zy.zhihuigongdi.service.TodayOperatorService;
import com.zy.zhihuigongdi.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.HashMap;

/**
 *
 * 今天操作人员控制器
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/20 0020 下午 6:16
 */
@Controller
@RequestMapping("/admin")
public class TodayOperatorController extends BaseController {

    @Autowired
    private TodayOperatorService todayOperatorService;


    //去往导入页面
    @GetMapping("toTodayOperatorAdd")
    public String toTodayOperatorAdd(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        map.put("userName",user.getUserName());
        return "/todayOperator/TodayOperatorAdd";
    }


    //页面上传
    @PostMapping("dayOperatorUpload")
    @ResponseBody
    public Object dayOperatorUpload(MultipartFile file){
        star();
        try {
            InputStream inputStream = file.getInputStream();
            todayOperatorService.insertTodayOperator(inputStream);
            success(true);
        } catch (Exception e) {
            success(false);
            e.printStackTrace();
        }
        return end();
    }

}
