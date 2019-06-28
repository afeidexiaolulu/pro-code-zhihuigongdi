package com.zy.zhihuigongdi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.zhihuigongdi.bean.DangerWarning;
import com.zy.zhihuigongdi.dao.DangerWarningMapper;
import com.zy.zhihuigongdi.service.DangerWarningService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/18 0018 下午 4:06
 */

@Service
public class DangerWarningServiceImpl extends ServiceImpl<DangerWarningMapper, DangerWarning> implements DangerWarningService {

    @Autowired
    private DangerWarningMapper dangerWarningMapper;

    //根据id查询
    @Override
    public DangerWarning queryDangerWarning(Integer id) {
        return dangerWarningMapper.selectById(id);
    }

    //批量插入危险预警数
    @Override
    public Integer addDanderWarningBatch(ArrayList<DangerWarning> dangerWarnings) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(now);
        QueryWrapper<DangerWarning> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("create_time_",dateString);
        List<DangerWarning> dangerWarnings1 = dangerWarningMapper.selectList(queryWrapper);
        if(dangerWarnings1.size() != 0){
            ArrayList<Integer> idList = new ArrayList<>();
            for (DangerWarning dangerWarning : dangerWarnings1) {
                idList.add(dangerWarning.getId());
            }
            dangerWarningMapper.deleteBatchIds(idList);
        }
        return dangerWarningMapper.addDanderWarningBatch(dangerWarnings);
    }

    //更新
    @Override
    public Integer updateDangerWarning(DangerWarning dangerWarning) {
        return dangerWarningMapper.updateById(dangerWarning);
    }

    //分页查询
    @Override
    public Page<DangerWarning> findAllDangerWarning(HashMap<String, Object> paramMap) {
        Integer pageNo = (Integer) paramMap.get("pageNo");
        Integer pageSize = (Integer) paramMap.get("pageSize");
        String queryDate = (String) paramMap.get("queryDate");
        //查询最后一天日期
        Date date = dangerWarningMapper.selectLastCreateTime();
        QueryWrapper<DangerWarning> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryDate)){
            queryWrapper.like("create_time_", queryDate);
        }else {
            queryWrapper.eq("create_time_", date);
        }
        Page<DangerWarning> warningPage = new Page<>(pageNo, pageSize);
        dangerWarningMapper.selectPage(warningPage, queryWrapper);
        return warningPage;
    }

    @Override
    public Integer delDangerWarning(Integer id) {
        return dangerWarningMapper.deleteById(id);
    }
}
