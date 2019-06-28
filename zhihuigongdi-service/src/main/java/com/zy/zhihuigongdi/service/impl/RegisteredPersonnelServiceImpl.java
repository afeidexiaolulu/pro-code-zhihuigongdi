package com.zy.zhihuigongdi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.zhihuigongdi.bean.RegisteredPersonnel;
import com.zy.zhihuigongdi.dao.RegisteredPersonnelMapper;
import com.zy.zhihuigongdi.service.RegisteredPersonnelService;
import com.zy.zhihuigongdi.util.MyPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
public class RegisteredPersonnelServiceImpl extends ServiceImpl<RegisteredPersonnelMapper, RegisteredPersonnel> implements RegisteredPersonnelService {

    @Autowired
    RegisteredPersonnelMapper registeredPersonnelMapper;


    //插入注册工人
    @Override
    public Integer addRegPersonnel(RegisteredPersonnel registeredPersonnel) {
        registeredPersonnel.setCreateTime(new Date());
        //插入
        return registeredPersonnelMapper.insert(registeredPersonnel);
    }

    //删除注册工人
    @Override
    public Integer delRegPersonnel(Integer id) {
        //删除
        return registeredPersonnelMapper.deleteById(id);
    }

    //分页查询工人
    @Override
    public MyPage<RegisteredPersonnel> findAllRegPersonnel(HashMap<String, Object> paramMap) {
        Integer pageSize = (Integer) paramMap.get("pageSize");
        Integer pageNo = (Integer) paramMap.get("pageNo");
        RegisteredPersonnel queryCondition = (RegisteredPersonnel) paramMap.get("registeredPersonnel");
        //分页查询
        Page<RegisteredPersonnel> personnelPage = new Page<>(pageNo, pageSize);
        //分页条件
        QueryWrapper<RegisteredPersonnel> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id_");
        if(queryCondition == null){
            //分页查询
            registeredPersonnelMapper.selectPage(personnelPage,wrapper);
        }else {

            if(!StringUtils.isEmpty(queryCondition.getName())){
                wrapper.like("name_", queryCondition.getName());
            }
            if(!StringUtils.isEmpty(queryCondition.getBelongCompany())){
                wrapper.like("belong_company_", queryCondition.getBelongCompany());
            }
            if(!StringUtils.isEmpty(queryCondition.getTechnicalGrade())){
                wrapper.like("technical_grade_", queryCondition.getTechnicalGrade());
            }
            if(!StringUtils.isEmpty(queryCondition.getIdentityNumber())){
                wrapper.like("identity_number_",queryCondition.getIdentityNumber());
            }
            //分页查询
            registeredPersonnelMapper.selectPage(personnelPage,wrapper);
        }

        MyPage<RegisteredPersonnel> myPage = new MyPage<>();
        myPage.setDatas(personnelPage.getRecords());
        myPage.setPageno((int)personnelPage.getCurrent());
        myPage.setPagesize((int)personnelPage.getSize());
        myPage.setTotalsize((int)personnelPage.getTotal());

        return myPage;
    }

    //更新用户信息
    @Override
    public Integer updateRegPersonnel(RegisteredPersonnel registeredPersonnel) {

        return registeredPersonnelMapper.updateById(registeredPersonnel);
    }

    //根据用户ID查询用户
    @Override
    public RegisteredPersonnel queryRegPersonnelById(Integer id) {
        return registeredPersonnelMapper.selectById(id);
    }

    //检查身份号
    @Override
    public Integer regIdentityCheck(String identityNumber) {
        QueryWrapper<RegisteredPersonnel> personnelQueryWrapper = new QueryWrapper<>();
        personnelQueryWrapper.eq("identity_number_",identityNumber);
        List<RegisteredPersonnel> registeredPersonnelList = registeredPersonnelMapper.selectList(personnelQueryWrapper);
        if (registeredPersonnelList.size() != 0){
            return 1;
        }
        return 0;
    }

    //更新状态
    @Override
    public Integer updateRegPersonnel1(Integer id) {
        UpdateWrapper<RegisteredPersonnel> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id_",id);
        RegisteredPersonnel registeredPersonnel = new RegisteredPersonnel();
        registeredPersonnel.setStatu("黑名单");
        return registeredPersonnelMapper.update(registeredPersonnel,updateWrapper);
    }

    //批量删除
    @Override
    public Integer delRegPersonnelBatch(Integer[] ids) {
        //idea 增强for循环  iter快捷键
        List<Integer> list = new ArrayList<>();
        for (Integer id : ids) {
            list.add(id);
        }
        return registeredPersonnelMapper.deleteBatchIds(list);
    }
}
