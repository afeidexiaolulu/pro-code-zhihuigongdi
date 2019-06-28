package com.zy.zhihuigongdi.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 注册人员实体类
 */

@Data
@TableName("t_registeredpersonnel")
public class RegisteredPersonnel {

    @TableId(value= "id_",type = IdType.AUTO)
    private Integer id;

    @TableField("serial_number_")
    private String serialNumber;

    @TableField("name_")
    private String name;

    @TableField("sex_")
    private String sex;

    @TableField("phone_number_")
    private String phoneNumber;

    @TableField("belong_company_")
    private String belongCompany;

    @TableField("age_")
    private String age;

    @TableField("technical_grade_")
    private String technicalGrade;

    @TableField("statu_")
    private String statu;

    @TableField("create_time_")
    private Date createTime;

    @TableField("identity_number_")
    private String identityNumber;

}
