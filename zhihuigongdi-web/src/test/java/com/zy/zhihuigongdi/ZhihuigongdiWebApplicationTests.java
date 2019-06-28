package com.zy.zhihuigongdi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ZhihuigongdiWebApplicationTests {

    @Test
    public void contextLoads() {
        Date now = new Date();
        // java.util.Date -> java.time.LocalDate
        LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // java.time.LocalDate -> java.sql.Date
        Date newDate = java.sql.Date.valueOf(localDate);
        System.out.println(newDate);
    }


    @Test
    public void testCreate() throws IOException {

        // 创建新的Excel 工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 在Excel工作簿中建一工作表，其名为缺省值
        //HSSFSheet sheet = workbook.createSheet();

        // 如要新建一名为"会员登录统计"的工作表，其语句为：
        HSSFSheet sheet = workbook.createSheet("会员登录统计");

        // 创建行（第一行）
        HSSFRow row1 = sheet.createRow(0);

        // 创建单元格（第一列）
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("今日人数");

        // 创建单元格（第二列）
        HSSFCell cell2 = row1.createCell(1);
        cell2.setCellValue(666);

        //创建单元格（第三列）
        HSSFCell cell3 = row1.createCell(2);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cell3.setCellValue(sdf.format(date));

        // 新建一输出文件流（注意：要先创建文件夹）
        FileOutputStream out = new FileOutputStream("d:/test-write.xls");
        // 把相应的Excel 工作簿存盘
        workbook.write(out);
        out.flush();
        // 操作结束，关闭文件
        out.close();
        System.out.println("文件生成成功");

    }


}
