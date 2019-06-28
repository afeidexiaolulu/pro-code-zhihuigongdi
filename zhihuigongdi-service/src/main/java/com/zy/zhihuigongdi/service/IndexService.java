package com.zy.zhihuigongdi.service;

import com.zy.zhihuigongdi.bean.IndexBeam;
import com.zy.zhihuigongdi.bean.RegisteredPersonnel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IndexService {
    List<IndexBeam> queryAnalysisOfTheCompanyToWhichTheRegisteredOperatorsBelongList();

    List<IndexBeam> queryAnalysisOfTechnicalLevelOfRegisteredPersonnelList();

    List<IndexBeam> queryAgeStatisticsOfRegisteredPersonnelList();

    List<IndexBeam> queryDangerAlarmList(HttpServletRequest request);

    List<RegisteredPersonnel> queryBlackList();

    List<IndexBeam> queryNumberOfPeopleWorkingTodayList();

    Map<Object, Object> getMonitoringInfo();

    String queryDaysOfSafetyInProduction();

    Integer updateDaysOfSafetyInProduction(String newDay,String id);

    String queryProjectName();

}
