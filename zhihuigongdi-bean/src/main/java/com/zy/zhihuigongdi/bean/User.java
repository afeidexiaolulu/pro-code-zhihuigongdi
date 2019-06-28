package com.zy.zhihuigongdi.bean;


//管理员用户实体类

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;


@Data
@TableName(value = "t_user")
public class User {

    private Integer id;

    private String loginAcct;

    private String pwd;

    private String userName;

    private Date createTime;
}
