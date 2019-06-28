package com.zy.zhihuigongdi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.zhihuigongdi.bean.TodayOperator;
import org.springframework.stereotype.Component;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/20 0020 下午 6:17
 */
@Component
public interface TodayOperatorMapper extends BaseMapper<TodayOperator> {
    //根据时间删除
    Integer deleteByTime(String dateSubString);
}
