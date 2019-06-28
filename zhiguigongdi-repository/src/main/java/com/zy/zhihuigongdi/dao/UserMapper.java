package com.zy.zhihuigongdi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.zhihuigongdi.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {


    //根据用户名查询用户
    @Select("select login_acct, pwd, user_name, create_time from t_user where login_acct = #{loginAcct}")
    User getAdminByLoginAcct(String loginAcct);
}
