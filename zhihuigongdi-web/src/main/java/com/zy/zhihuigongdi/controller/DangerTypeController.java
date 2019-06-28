package com.zy.zhihuigongdi.controller;

import com.zy.zhihuigongdi.bean.DangerType;
import com.zy.zhihuigongdi.bean.User;
import com.zy.zhihuigongdi.service.DangerTypeService;
import com.zy.zhihuigongdi.util.Const;
import com.zy.zhihuigongdi.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/18 0018 下午 1:46
 *
 * 危险预警类型控制器
 *
 */
@Controller
@RequestMapping("/admin")
public class DangerTypeController extends BaseController{


    @Autowired
    private DangerTypeService dangerTypeService;

    //跳到危险类型页面
    @GetMapping("/toDangerType")
    public String toDangerType(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        map.put("userName",user.getUserName());
        return "/dangerType/DangerType";
    }

    //
    //查询所有的危险类型 分页
    @PostMapping("/findAllDangerType")
    @ResponseBody
    public Object findAllDangerType(Integer pageSize, Integer pageNo){
        star();
        try {
            //将条件进行封装
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("pageSize", pageSize);
            paramMap.put("pageNo", pageNo);
            MyPage<DangerType> page = dangerTypeService.findAllDangerType(paramMap);
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


    //新增数据类型跳转
    @GetMapping("/toDangerTypeAdd")
    public String toDangerTypeAdd(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        String userName = user.getUserName();
        map.put("userName",userName);
        return "/dangerType/DangerTypeAdd";
    }

    //新增危险数据类型
    @PostMapping("/addDangerType")
    @ResponseBody
    public Object addDangerType(DangerType dangerType){
        star();
        try{
            Integer addNum = dangerTypeService.addDangerType(dangerType);
            if(addNum != 1){
                throw new RuntimeException();
            }
            success(true);
        }catch (Exception e) {
            success(false);
            message("添加数据失败");
        }
        return end();
    }

    //根据iD删除危险预警类型
    @GetMapping("/delDangerType")
    @ResponseBody
    public Object delDangerType(Integer id){
        star();
        try {
            Integer delNum = dangerTypeService.delDangerType(id);
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


}
