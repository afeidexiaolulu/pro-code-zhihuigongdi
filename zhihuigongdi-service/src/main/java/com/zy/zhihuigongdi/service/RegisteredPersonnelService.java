package com.zy.zhihuigongdi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.zhihuigongdi.bean.RegisteredPersonnel;
import com.zy.zhihuigongdi.util.MyPage;
import java.util.HashMap;



public interface RegisteredPersonnelService extends IService<RegisteredPersonnel> {

    //录入工人
    Integer addRegPersonnel(RegisteredPersonnel registeredPersonnel);

    //删除工人
    Integer delRegPersonnel(Integer id);

    //分页查询工人
    MyPage<RegisteredPersonnel> findAllRegPersonnel(HashMap<String, Object> paramMap);

    //更新工人信息
    Integer updateRegPersonnel(RegisteredPersonnel registeredPersonnel);

    //根据id查询工人
    RegisteredPersonnel queryRegPersonnelById(Integer id);

    //批量删除
    Integer delRegPersonnelBatch(Integer[] ids);

    //更新状态
    Integer updateRegPersonnel1(Integer id);

    //检查身份证号
    Integer regIdentityCheck(String identityNumber);
}
