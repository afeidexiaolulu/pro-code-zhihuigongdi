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
 * 危险报警实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_dangerwarning")
public class DangerWarning {

    @TableId(value ="id_", type =IdType.AUTO)
    private Integer id;

    @TableField("type_")
    private String type;

    @TableField("number_")
    private Integer num;

    @TableField("create_time_")
    private Date createTime;

}
