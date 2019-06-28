package com.zy.zhihuigongdi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.zhihuigongdi.bean.IndexBeam;
import com.zy.zhihuigongdi.bean.RegisteredPersonnel;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface IndexMapper extends BaseMapper<IndexBeam> {
    List<IndexBeam> queryAnalysisOfTheCompanyToWhichTheRegisteredOperatorsBelongList();

    Integer countRegisteredpersonnel();

    List<IndexBeam> queryanalysisOfTechnicalLevelOfRegisteredPersonnelList();

    List<IndexBeam> queryAgeStatisticsOfRegisteredPersonnelList();

    List<IndexBeam> queryDangerAlarmList();

    List<IndexBeam> queryDangerAlarmTotalList();

    List<RegisteredPersonnel> queryBlackList();

    List<IndexBeam> queryNumberOfPeopleWorkingTodayList();

    Map<Object, Object> getMonitoringInfo();

    @Select("SELECT PORT_ FROM T_MONITORINGINFO WHERE ID_ = '2'")
    String queryDaysOfSafetyInProduction();

    Integer updateDaysOfSafetyInProduction(String newDay,String id);

    @Select("SELECT PORT_ FROM T_MONITORINGINFO WHERE ID_ = '3'")
    String queryProjectName();
}
