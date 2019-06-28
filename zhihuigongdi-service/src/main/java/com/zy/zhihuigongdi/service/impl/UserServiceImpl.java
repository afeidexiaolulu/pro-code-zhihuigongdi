package com.zy.zhihuigongdi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.zhihuigongdi.dao.UserMapper;
import com.zy.zhihuigongdi.bean.User;
import com.zy.zhihuigongdi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *管理员服务层
 *
 *
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    //根据登录名查询用户
    @Override
    public User getAdminByLoginAcct(String loginAcct) {

        return userMapper.getAdminByLoginAcct(loginAcct);
    }
}
