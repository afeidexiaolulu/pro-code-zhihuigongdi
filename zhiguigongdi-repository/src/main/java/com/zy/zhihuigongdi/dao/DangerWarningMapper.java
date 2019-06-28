package com.zy.zhihuigongdi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.zhihuigongdi.bean.DangerWarning;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/18 0018 下午 4:07
 */

@Component
public interface DangerWarningMapper extends BaseMapper<DangerWarning> {
    //批量插入
    Integer addDanderWarningBatch(@Param("dangerWarnings") ArrayList<DangerWarning> dangerWarnings);

    //查询最后一天记录
    Date selectLastCreateTime();
}
