package com.zy.zhihuigongdi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.zhihuigongdi.bean.TodayOperator;
import com.zy.zhihuigongdi.dao.TodayOperatorMapper;
import com.zy.zhihuigongdi.service.TodayOperatorService;
import com.zy.zhihuigongdi.util.ExcelImportHSSFUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/6/20 0020 下午 6:18
 */
@Service
public class TodayOperatorServiceImpl extends ServiceImpl<TodayOperatorMapper, TodayOperator> implements TodayOperatorService {

    @Autowired
    private TodayOperatorMapper todayOperatorMapper;

    @Override
    @Transactional
    public void insertTodayOperator(InputStream inputStream) throws Exception {

        //创建poi工具类，获取工作簿
        ExcelImportHSSFUtil excelImportHSSFUtil = new ExcelImportHSSFUtil(inputStream);
        HSSFSheet sheet = excelImportHSSFUtil.getSheet();
        //获取有多少行数据  索引从0开始
        Integer rowNum = sheet.getLastRowNum();
        if(0 == rowNum){
            throw new RuntimeException();
        }
        //判断日期是否已录入过，如果录入过 删除一起录入数据
        HSSFCell timeCell = sheet.getRow(1).getCell(5);
        String dateString = excelImportHSSFUtil.getCellValue(timeCell, timeCell.getCellType());
        if(timeCell != null){
            String dateSubString = dateString.substring(0, dateString.indexOf(" "));

            QueryWrapper<TodayOperator> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("time_", dateSubString);
            List<TodayOperator> todayOperators = todayOperatorMapper.selectList(queryWrapper);
            if(todayOperators.size() != 0){
                Integer deleteNum = todayOperatorMapper.deleteByTime(dateSubString);
            }
        }

        HSSFRow row1 = sheet.getRow(0);
        //每列单元格个数  索引从1个开始
        short CellNum = row1.getLastCellNum();
        //从第二行开始读取
        ArrayList<String> list = new ArrayList<>();
        String value;
        for(int i=1; i<=rowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 1; j < CellNum; j++) {
                HSSFCell cell = row.getCell(j);
                if(cell != null){
                    value = excelImportHSSFUtil.getCellValue(cell, cell.getCellType());
                }else {
                    value = "未知";
                }
                //将获取的value值放入list中
                list.add(value);
            }
            TodayOperator todayOperator = new TodayOperator(null,list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7),null,new Date());
            //清空list
            list.clear();
            todayOperatorMapper.insert(todayOperator);
        }
    }
}
