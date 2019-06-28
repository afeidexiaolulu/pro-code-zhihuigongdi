package com.zy.zhihuigongdi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.zhihuigongdi.bean.DangerWarning;
import com.zy.zhihuigongdi.util.MyPage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/18 0018 下午 4:05
 */
public interface DangerWarningService extends IService<DangerWarning> {

    //危险预警分页查询
    Page<DangerWarning> findAllDangerWarning(HashMap<String, Object> paramMap);

    //删除危险预警
    Integer delDangerWarning(Integer id);

    //根据id查询危险预警
    DangerWarning queryDangerWarning(Integer id);

    //更新
    Integer updateDangerWarning(DangerWarning dangerWarning);

    //批量插入
    Integer addDanderWarningBatch(ArrayList<DangerWarning> dangerWarnings);
}
