package com.zy.zhihuigongdi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.zhihuigongdi.bean.IndexBeam;
import com.zy.zhihuigongdi.bean.RegisteredPersonnel;
import com.zy.zhihuigongdi.dao.IndexMapper;
import com.zy.zhihuigongdi.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl extends ServiceImpl<IndexMapper, IndexBeam> implements IndexService {
    @Autowired
    private IndexMapper indexMapper;

    @Override
    public List<IndexBeam> queryAnalysisOfTheCompanyToWhichTheRegisteredOperatorsBelongList() {
        List<IndexBeam> indexBeamList = indexMapper.queryAnalysisOfTheCompanyToWhichTheRegisteredOperatorsBelongList();
        Integer countNum = indexMapper.countRegisteredpersonnel();
        DecimalFormat df = new DecimalFormat("#0.00");
        for (IndexBeam indexBeam:indexBeamList) {
            Integer num = indexBeam.getNumber();
            Double percentage = (double)num/countNum;
            indexBeam.setPercentage(df.format(percentage*100)+"%");
        }
        return indexBeamList;
    }

    @Override
    public List<IndexBeam> queryAnalysisOfTechnicalLevelOfRegisteredPersonnelList() {
        List<IndexBeam> indexBeamList = indexMapper.queryanalysisOfTechnicalLevelOfRegisteredPersonnelList();
        Integer countNum = indexMapper.countRegisteredpersonnel();
        DecimalFormat df = new DecimalFormat("#0.00");
        for (IndexBeam indexBeam:indexBeamList) {
            Integer num = indexBeam.getNumber();
            Double percentage = (double)num/countNum;
            indexBeam.setPercentage(df.format(percentage*100)+"%");
        }
        return indexBeamList;
    }

    @Override
    public List<IndexBeam> queryAgeStatisticsOfRegisteredPersonnelList() {
        return indexMapper.queryAgeStatisticsOfRegisteredPersonnelList();
    }

    @Override
    public List<IndexBeam> queryDangerAlarmList(HttpServletRequest request) {
        //选择累计type为total,否则为查询当日
        String type = request.getParameter("type");
        if("total".equals(type)){
            return indexMapper.queryDangerAlarmTotalList();
        }else{
            return indexMapper.queryDangerAlarmList();
        }
    }

    @Override
    public List<RegisteredPersonnel> queryBlackList() {
        return indexMapper.queryBlackList();
    }

    @Override
    public List<IndexBeam> queryNumberOfPeopleWorkingTodayList() {
        return indexMapper.queryNumberOfPeopleWorkingTodayList();
    }

    @Override
    public Map<Object, Object> getMonitoringInfo() {
        return indexMapper.getMonitoringInfo();
    }

    @Override
    public String queryDaysOfSafetyInProduction() {
        return indexMapper.queryDaysOfSafetyInProduction();
    }

    @Override
    public Integer updateDaysOfSafetyInProduction(String newDay,String id) {
        return indexMapper.updateDaysOfSafetyInProduction(newDay,id);
    }

    @Override
    public String queryProjectName() {
        return indexMapper.queryProjectName();
    }

}
