package com.zy.zhihuigongdi.controller;

import com.zy.zhihuigongdi.bean.IndexBeam;
import com.zy.zhihuigongdi.bean.RegisteredPersonnel;
import com.zy.zhihuigongdi.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 在册作业人员所属单位分析
     * @return
     */
    @RequestMapping("analysisOfTheCompanyToWhichTheRegisteredOperatorsBelong")
    @ResponseBody
    public Map<Object,Object> analysisOfTheCompanyToWhichTheRegisteredOperatorsBelong(){
        Map<Object,Object> map = new HashMap<Object,Object>();
        try{
            List<IndexBeam> indexBeamList = indexService.queryAnalysisOfTheCompanyToWhichTheRegisteredOperatorsBelongList();
            map.put("indexBeamList",indexBeamList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 在册人员年龄段统计
     * @return
     */
    @RequestMapping("ageStatisticsOfRegisteredPersonnel")
    @ResponseBody
    public Map<Object,Object> ageStatisticsOfRegisteredPersonnel(){
        Map<Object,Object> map = new HashMap<Object,Object>();
        try{
            List<IndexBeam> indexBeamList = indexService.queryAgeStatisticsOfRegisteredPersonnelList();
            map.put("indexBeamList",indexBeamList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 在册人员技术等级分析
     * @return
     */
    @RequestMapping("analysisOfTechnicalLevelOfRegisteredPersonnel")
    @ResponseBody
    public Map<Object,Object> analysisOfTechnicalLevelOfRegisteredPersonnel(){
        Map<Object,Object> map = new HashMap<Object,Object>();
        try{
            List<IndexBeam> indexBeamList = indexService.queryAnalysisOfTechnicalLevelOfRegisteredPersonnelList();
            map.put("indexBeamList",indexBeamList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 危险报警
     * @return
     */
    @RequestMapping("dangerAlarm")
    @ResponseBody
    public Map<Object,Object> dangerAlarm(HttpServletRequest request){
        Map<Object,Object> map = new HashMap<Object,Object>();
        try{
            List<IndexBeam> indexBeamList = indexService.queryDangerAlarmList(request);
            map.put("indexBeamList",indexBeamList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 中冶黑名单人员库
     * @return
     */
    @RequestMapping("blackList")
    @ResponseBody
    public Map<Object,Object> blackList(){
        Map<Object,Object> map = new HashMap<Object,Object>();
        try{
            List<RegisteredPersonnel> blackList = indexService.queryBlackList();
            map.put("blackList",blackList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 今日作业人数
     * @return
     */
    @RequestMapping("numberOfPeopleWorkingToday")
    @ResponseBody
    public Map<Object,Object> numberOfPeopleWorkingToday(){
        Map<Object,Object> map = new HashMap<Object,Object>();
        try{
            List<IndexBeam> indexBeamList = indexService.queryNumberOfPeopleWorkingTodayList();
            map.put("indexBeamList",indexBeamList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 监控信息
     * @return
     */
    @RequestMapping("getMonitoringInfo")
    @ResponseBody
    public Map<Object,Object> getMonitoringInfo(){
        Map<Object,Object> map = indexService.getMonitoringInfo();
        return map;
    }

    /**
     * index
     * @return
     */
    @RequestMapping("zhihuigongdi")
    public String test(){
        return "html/index";
    }

    /**
     * VideoSurveillance
     * @return
     */
    @RequestMapping("VideoSurveillance")
    public String VideoSurveillance(){
        return "html/VideoSurveillance";
    }

    /**
     * BIMManagementCenter
     * @return
     */
    @RequestMapping("BIMManagementCenter")
    public String BIMManagementCenter(){
        return "html/BIMManagementCenter";
    }

    /**
     * EnvironmentalMonitoringCenter
     * @return
     */
    @RequestMapping("EnvironmentalMonitoringCenter")
    public String EnvironmentalMonitoringCenter(){
        return "html/EnvironmentalMonitoringCenter";
    }

    /**
     * LaborPersonnelManagement
     * @return
     */
    @RequestMapping("LaborPersonnelManagement")
    public String LaborPersonnelManagement(){
        return "html/LaborPersonnelManagement";
    }

    /**
     * PartyBuildingColumn
     * @return
     */
    @RequestMapping("PartyBuildingColumn")
    public String PartyBuildingColumn(){
        return "html/PartyBuildingColumn";
    }

    /**
     * ZhongyeYizhuangHomePage
     * @return
     */
    @RequestMapping("ZhongyeYizhuangHomePage")
    public String ZhongyeYizhuangHomePage(){
        return "html/ZhongyeYizhuangHomePage";
    }

    /**
     * 安全生产天数
     * @return
     */
    @RequestMapping("queryDaysOfSafetyInProduction")
    @ResponseBody
    public Map<Object,Object> queryDaysOfSafetyInProduction(){
        Map<Object,Object> map = new HashMap<Object,Object>();
        String daysOfSafetyInProduction = indexService.queryDaysOfSafetyInProduction();
        map.put("daysOfSafetyInProduction",daysOfSafetyInProduction);
        String projectName = indexService.queryProjectName();
        map.put("projectName",projectName);
        return map;
    }

}
