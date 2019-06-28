package com.zy.zhihuigongdi.controller;

import com.zy.zhihuigongdi.bean.User;
import com.zy.zhihuigongdi.service.UserService;
import com.zy.zhihuigongdi.util.Const;
import com.zy.zhihuigongdi.util.LoginResult;
import com.zy.zhihuigongdi.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *此控制器管理员相关操作的控制器
 */


@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    //跳转到登录页面
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }


    //进行登录操作  ajax请求加@ResponseBody
    @RequestMapping("/doLogin")
    @ResponseBody
    public LoginResult doLogin(String loginAcct, String pwd, HttpSession session) {
        LoginResult lR = new LoginResult();
        try {
            //根据用户名查询用户
            User user = userService.getAdminByLoginAcct(loginAcct);
            //查询不出来,用户名错误
            if(user == null){
                //用户名错误
                lR.setSuccess(false);
                lR.setMessage(Const.LOGIN_LOGINACCT_ERROR);
                return lR;
            }
            //如果admin不为空,验证密码，
            if(!MD5Util.digest(pwd).equals(user.getPwd())){
                //密码错误
                lR.setSuccess(false);
                lR.setMessage(Const.LOGIN_USERPSWD_ERROR);
                return lR;
            }else {
                //密码正确，将用户放到session中
                session.setAttribute(Const.LOGIN_USER,user);
                lR.setSuccess(true);
            }
        } catch (Exception e) {
            //异常，失败
            lR.setSuccess(false);
            lR.setMessage(Const.LOGIN_EXC);
            e.printStackTrace();
        }
        return lR;
    }

    //注销登录
    @GetMapping("/loginOut")
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        //如果session不为空
        if(session != null){
            //移除session中的用户数据并销毁
            session.removeAttribute(Const.LOGIN_MEMBER);
            session.invalidate();
        }
        //重定向登录页面
        return "redirect:/login";
    }

}
