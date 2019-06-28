package com.zy.zhihuigongdi.controller;


import com.zy.zhihuigongdi.bean.RegisteredPersonnel;
import com.zy.zhihuigongdi.bean.User;
import com.zy.zhihuigongdi.service.RegisteredPersonnelService;
import com.zy.zhihuigongdi.util.Const;
import com.zy.zhihuigongdi.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台管理的控制层
 */

@Controller
@RequestMapping("/admin")
public class RegisteredPersonnelController extends BaseController {

    @Autowired
    private RegisteredPersonnelService registeredPersonnelService;

    //跳转到后台管理首页
    @RequestMapping("/toAdminIndex")
    public String toAdminIndex(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        String userName = user.getUserName();
        map.put("userName",userName);
        return "/adminIndex";
    }


    //跳转到注册人员管理页面
    @GetMapping("/toRegPersonnel")
    public String toRegPersonnel(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        String userName = user.getUserName();
        map.put("userName",userName);
        return "/regPersonnel/RegPersonnel";
    }

    //跳转到增加注册人员页面
    @GetMapping("/toRegPersonnelAdd")
    public String toRegPersonnelAdd(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        String userName = user.getUserName();
        map.put("userName",userName);
        return "/regPersonnel/RegPersonnelAdd";
    }

    //查询所有的注册工人 分页
    @GetMapping("/findAllRegPersonnel")
    @ResponseBody
    public Object findAllRegPersonnel(RegisteredPersonnel queryCondition, Integer pageSize, Integer pageNo){
        star();
        try {
            //将条件进行封装
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("pageSize", pageSize);
            paramMap.put("pageNo", pageNo);
            paramMap.put("registeredPersonnel", queryCondition);
            MyPage<RegisteredPersonnel> page = registeredPersonnelService.findAllRegPersonnel(paramMap);
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

    //后台添加注册工人
    @PostMapping("/addRegPersonnel")
    @ResponseBody
    public Object addRegPersonnel(RegisteredPersonnel registeredPersonnel){
        star();
        try {
            Integer num = registeredPersonnelService.addRegPersonnel(registeredPersonnel);
            if(num != 1){
                throw new Exception("插入注册工人失败");
            }
            success(true);
        }catch (Exception e){
            e.printStackTrace();
            message("添加注册工人失败");
            success(false);
        }
        return end();
    }

    //后台删除注册工人
    @ResponseBody
    @RequestMapping("/delRegPersonnel")
    public Object delRegPersonnel(Integer id){
        star();
        try {
            Integer delNum = registeredPersonnelService.delRegPersonnel(id);
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

    //批量删除
    @GetMapping("/delRegPersonnelBatch")
    @ResponseBody
    public Object delRegPersonnelBatch(Integer[] id){

        star();
        try {
            Integer delNum = registeredPersonnelService.delRegPersonnelBatch(id);
            System.out.println(id);
            if(delNum != id.length){
                throw new RuntimeException();
            }
            success(true);
        } catch (Exception e) {
            message("批量删除失败");
            success(false);
            e.printStackTrace();
        }
        return end();
    }

    //更新注册工人状态
    @GetMapping("updateRegPersonnelBlack")
    @ResponseBody
    public Object updateRegPersonnel(Integer id){
        star();
        try {
            Integer upNum = registeredPersonnelService.updateRegPersonnel1(id);
            success(upNum == 1);
        } catch (Exception e) {
            success(false);
            message("更新状态失败");
            e.printStackTrace();
        }
       return end();
    }


    //跳到编辑页面
    @GetMapping("toUpdateRegPersonnel")
    public Object queryRegPersonnelById(Integer id, HashMap map){
        star();
        try {
            RegisteredPersonnel rP = registeredPersonnelService.queryRegPersonnelById(id);
            map.put("rP",rP);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/regPersonnel/RegPersonnelUpdate";
    }

    //后台更改注册工人信息
    //updateRegPersonnel
    @PostMapping("/updateRegPersonnel")
    @ResponseBody
    public Object updateRegPersonnel(RegisteredPersonnel registeredPersonnel){
        star();
        try {
            Integer updateNum = registeredPersonnelService.updateRegPersonnel(registeredPersonnel);
            if(updateNum != 1){
                throw new Exception();
            }
            success(true);
        }catch (Exception e){
            message("更新员工信息失败");
            success(false);
            e.printStackTrace();
        }
        return end();
    }

    //身份证重复校验并返回性别
    @PostMapping("/regIdentityCheckAndSex")
    @ResponseBody
    public Object regIdentityCheck(String identityNumber) {
        star();
        try {
            Integer num = registeredPersonnelService.regIdentityCheck(identityNumber);
            if(num == 1){
                throw new RuntimeException("此身份证号重复");
            }else {
                //验证性别
                int idxSexStart = 16;
                //查看出生年月
                int idxAgeStart = 6;
                //年龄
                String idxAge = identityNumber.substring(idxAgeStart, idxAgeStart+4);
                //如果是15位的证件号码
                if(identityNumber.length() == 15) {
                    idxSexStart = 14;
                    idxAgeStart = 6;
                    idxAge = "19"+identityNumber.substring(idxAgeStart, idxAgeStart+2);
                }
                //性别
                String idxSexStr = identityNumber.substring(idxSexStart, idxSexStart + 1);
                int idxSex = Integer.parseInt(idxSexStr) % 2;
                String sex = (idxSex == 1) ? "男" : "女";
                //计算年龄
                int nowYear = Calendar.getInstance().get(Calendar.YEAR);
                Integer age = nowYear - new Integer(idxAge);
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("age", age);
                resultMap.put("sex", sex);
                data(resultMap);
                success(true);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            message(message);
            success(false);
        }
        return end();
    }

    @GetMapping("toRegPersonnelBlack")
    public String toRegPersonnelBlack(HttpSession session, HashMap map){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        String userName = user.getUserName();
        map.put("userName",userName);
        return "/regPersonnel/RegPersonnelBlack";
    }
    
}
