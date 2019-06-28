package com.zy.zhihuigongdi.util;


import com.zy.zhihuigongdi.util.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 此配置类相当于将注册器注册到springMVC
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    //注入拦截器
    @Autowired
    private LoginInterceptor loginInterceptor;


    //将拦截器注入
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**");
    }


}
