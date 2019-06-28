package com.zy.zhihuigongdi.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.zhihuigongdi.bean.User;


public interface UserService extends IService<User> {

    User getAdminByLoginAcct(String loginAcct);
}
