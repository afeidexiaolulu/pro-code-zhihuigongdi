package com.zy.zhihuigongdi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.zhihuigongdi.bean.DangerType;
import com.zy.zhihuigongdi.util.MyPage;
import java.util.HashMap;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/18 0018 下午 1:57
 */
public interface DangerTypeService extends IService<DangerType> {

    //危险类型分页查询
    MyPage<DangerType> findAllDangerType(HashMap<String, Object> paramMap);

    //插入危险预警类型
    Integer addDangerType(DangerType dangerType);

    //根据id删除危险类型
    Integer delDangerType(Integer id);

    //查询所有预警类型
    List<DangerType> selectAll();
}
