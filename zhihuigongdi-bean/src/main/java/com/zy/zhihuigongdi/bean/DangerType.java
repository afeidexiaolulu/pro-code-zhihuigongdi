package com.zy.zhihuigongdi.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/18 0018 下午 1:52
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_dangertype")
public class DangerType {

    @TableId(value = "id_", type = IdType.AUTO)
    private Integer id;

    @TableField("danger_type_")
    private String dangerType;

    @TableField("create_time_")
    private Date createTime;
}
