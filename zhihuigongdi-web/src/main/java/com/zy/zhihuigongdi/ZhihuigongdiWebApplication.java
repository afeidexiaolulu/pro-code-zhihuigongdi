package com.zy.zhihuigongdi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//开启声明式事务
@EnableTransactionManagement
//打开定时任务
@EnableScheduling
//mapper包所在位置
@MapperScan("com.zy.zhihuigongdi.dao")
@SpringBootApplication
public class ZhihuigongdiWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(ZhihuigongdiWebApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ZhihuigongdiWebApplication.class);
    }

}
