package com.zy.zhihuigongdi.util;

import com.zy.zhihuigongdi.util.Const;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;


/**
 * 此拦截器用于验证用户登录
 */

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //查看用户请求路径是不是在白名单中
        String requestURI = request.getRequestURI();
        HashSet<String> baimingdan = new HashSet<>();
        baimingdan.add("/login");
        baimingdan.add("/doLogin");
        if(baimingdan.contains(requestURI)){
            //放行
            return true;
        }else {
            //查看用户是否登录
            HttpSession session = request.getSession();
            Object user = session.getAttribute(Const.LOGIN_USER);
            if(user != null){
                //登录成功
                return true;
            }else {
                //重定向到登录页面
                response.sendRedirect("/login");
                return false;
            }
        }
    }


}
