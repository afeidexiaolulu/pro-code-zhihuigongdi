package com.zy.zhihuigongdi.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.zhihuigongdi.bean.DangerType;
import com.zy.zhihuigongdi.bean.DangerWarning;
import com.zy.zhihuigongdi.bean.User;
import com.zy.zhihuigongdi.service.DangerTypeService;
import com.zy.zhihuigongdi.service.DangerWarningService;
import com.zy.zhihuigongdi.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * 危险预警控制器
 *
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/18 0018 下午 4:02
 */
@Controller
@RequestMapping("/admin")
public class DangerWarningController extends BaseController {

    @Autowired
    private DangerWarningService dangerWarningService;

    @Autowired
    private DangerTypeService dangerTypeService;

    //危险预警首页跳转
    @GetMapping("/toDangerWarning")
    public String toDangerWarning(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        map.put("userName",user.getUserName());
        return "/dangerWarning/DangerWarning";
    }

    //危险预警分页查询
    @PostMapping("/findAllDangerWarning")
    @ResponseBody
    public Object findAllDangerWarning(Integer pageNo, Integer pageSize, String queryDate){
        star();
        try {
            //将条件进行封装
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("pageSize", pageSize);
            paramMap.put("pageNo", pageNo);
            paramMap.put("queryDate", queryDate);
            Page<DangerWarning> page = dangerWarningService.findAllDangerWarning(paramMap);
            //返回结果
            success(true);
            data(page);
        }catch ( RuntimeException e){
            e.printStackTrace();
            success(false);
            message(Const.LOGIN_EXC);
        }
        return end();
    }

    //根据id删除危险预警
    @GetMapping("/delDangerWarning")
    @ResponseBody
    public Object delDangerType(Integer id){
        star();
        try {
            Integer delNum = dangerWarningService.delDangerWarning(id);
            if(delNum != 1){
                throw new Exception("删除失败");
            }
            success(true);
        } catch (Exception e) {
            success(false);
            message("删除失败");
            e.printStackTrace();
        }
        return end();
    }

    //修改危险预警,页面跳转
    @GetMapping("/toUpdateDangerWarning")
    public String toUpdateDangerWarning(Integer id, HashMap map, HttpSession session){
        //根据id查询预警  进行回显
        DangerWarning dangerWarning = dangerWarningService.queryDangerWarning(id);
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        map.put("userName",user.getUserName());
        map.put("dW",dangerWarning);
        //将数据返回
        return "/dangerWarning/DangerWarningUpdate";
    }

    //修改
    @PostMapping("/updateDangerWarning")
    @ResponseBody
    public Object updateDangerWarning(DangerWarning dangerWarning){
        star();
        try {
            Integer updateNum = dangerWarningService.updateDangerWarning(dangerWarning);
            if(updateNum != 1){
                throw new RuntimeException();
            }
            success(true);
        }catch (Exception e){
            success(false);
            message("更新失败");
        }
        return end();
    }


    //新增危险预警页面跳转
    @GetMapping("/toDangerWarningAdd")
    public String toDangerWarningAdd(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        String userName = user.getUserName();
        //查询预警类型
        List<DangerType> dangerTypeList = dangerTypeService.selectAll();
        Object json = JSON.toJSON(dangerTypeList);
        map.put("dangerTypeList",json);
        map.put("userName",userName);
        return "/dangerWarning/DangerWarningAdd";
    }

    @PostMapping("/addDanderWarning")
    @ResponseBody
    public Object addDanderWarning(String[] type, Integer[] num){
        star();
        ArrayList<DangerWarning> dangerWarnings = new ArrayList<>();
        try {
            for(int i=0; i<type.length; i++){
                DangerWarning dw = new DangerWarning(null,type[i],num[i],new Date());
                dangerWarnings.add(dw);
            }
            Integer insertNum = dangerWarningService.addDanderWarningBatch(dangerWarnings);

            success(insertNum == num.length);
        }catch (Exception e){
            success(false);
            message("添加数据失败");
        }
        return end();
    }


}
