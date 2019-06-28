package com.zy.zhihuigongdi.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.zhihuigongdi.bean.DangerType;
import com.zy.zhihuigongdi.dao.DangerTypeMapper;
import com.zy.zhihuigongdi.service.DangerTypeService;
import com.zy.zhihuigongdi.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/18 0018 &#x4e0b;&#x5348; 1:57
 */
@Service
public class DangerTypeIServiceImpl extends ServiceImpl<DangerTypeMapper, DangerType> implements DangerTypeService  {

    @Autowired
    private DangerTypeMapper dangerTypeMapper;

    @Override
    public MyPage<DangerType> findAllDangerType(HashMap<String, Object> paramMap) {
        Integer pageNo = (Integer) paramMap.get("pageNo");
        Integer pageSize = (Integer) paramMap.get("pageSize");

        Page<DangerType> dangerTypePage = new Page<>(pageNo, pageSize);

        dangerTypeMapper.selectPage(dangerTypePage,null);
        //创建myPage对象
        MyPage<DangerType> myPage = new MyPage<>();
        myPage.setDatas(dangerTypePage.getRecords());
        myPage.setTotalsize((int) dangerTypePage.getTotal());
        myPage.setPagesize((int) dangerTypePage.getSize());
        myPage.setPageno((int) dangerTypePage.getCurrent());

        return myPage;
    }


    //添加危险预警类型
    @Override
    public Integer addDangerType(DangerType dangerType) {
        dangerType.setCreateTime(new Date());
        return dangerTypeMapper.insert(dangerType);
    }

    //查询所有的危险类型
    @Override
    public List<DangerType> selectAll() {
        return dangerTypeMapper.selectList(null);
    }

    //根据id删除危险预警类型
    @Override
    public Integer delDangerType(Integer id) {
        return dangerTypeMapper.deleteById(id);
    }
}
