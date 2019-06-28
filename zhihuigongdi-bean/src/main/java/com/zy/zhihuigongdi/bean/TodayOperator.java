package com.zy.zhihuigongdi.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 今日操作人员的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_todayoperator")
public class TodayOperator {

    @TableId(value ="id_", type = IdType.AUTO)
    private Integer id;

    @TableField("cart_number_")
    private String cartNumber;

    @TableField("serial_number_")
    private String serialNumber;

    @TableField("name_")
    private String Name;

    @TableField("department_")
    private String department;

    @TableField("time_")
    private String time;

    @TableField("place_")
    private String place;

    @TableField("is_pass_")
    private String isPass;

    @TableField("describe_")
    private String describe;

    @TableField("parent_id_")
    private String parentId;

    @TableField("create_time_")
    private Date createTime;


}
