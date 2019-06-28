package com.zy.zhihuigongdi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.zhihuigongdi.bean.TodayOperator;
import java.io.InputStream;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/20 0020 下午 6:16
 */
public interface TodayOperatorService extends IService<TodayOperator> {

    //插入
    void insertTodayOperator(InputStream inputStream) throws Exception;
}
