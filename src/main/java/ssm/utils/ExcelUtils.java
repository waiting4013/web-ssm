package ssm.utils;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import test.Monitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {
    private static ExcelUtils instance = new ExcelUtils();

    private ExcelUtils() {
    }

    public static ExcelUtils getInstance() {
        return instance;
    }

    /**
     * 将 List<Map<String,Object>> 类型的数据导出为 Excel
     * 默认 Excel 文件的输出路径为 项目根目录下
     * 文件名为 filename + 时间戳 + .xlsx
     *
     * @param mapList  数据源(通常为数据库查询数据)
     * @param filename 文件名前缀, 实际文件名后会加上日期
     * @param title    表格首行标题
     * @return 文件输出路径
     */
    public String createExcel(List<Map> mapList, String filename, String title) {
        //获取数据源的 key, 用于获取列数及设置标题
        Map<String, Object> map = mapList.get(0);
        Set<String> stringSet = map.keySet();
        ArrayList<String> headList = new ArrayList<>(stringSet);

        //定义一个新的工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建一个Sheet页
        XSSFSheet sheet = wb.createSheet(title);
        //设置行高
        sheet.setDefaultRowHeight((short) (2 * 256));
        //为有数据的每列设置列宽
        for (int i = 0; i < headList.size(); i++) {
            sheet.setColumnWidth(i, 8000);
        }
        //设置单元格字体样式
        XSSFFont font = wb.createFont();
        font.setFontName("等线");
        font.setFontHeightInPoints((short) 16);

        //在sheet里创建第一行，并设置单元格内容为 title (标题)
        XSSFRow titleRow = sheet.createRow(0);
        XSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headList.size() - 1));
        // 创建单元格文字居中样式并设置标题单元格居中
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCell.setCellStyle(cellStyle);

        //获得表格第二行
        XSSFRow row = sheet.createRow(1);
        //根据数据源信息给第二行每一列设置标题
        for (int i = 0; i < headList.size(); i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(headList.get(i));
        }

        XSSFRow rows;
        XSSFCell cells;
        //循环拿到的数据给所有行每一列设置对应的值
        for (int i = 0; i < mapList.size(); i++) {
            //在这个sheet页里创建一行
            rows = sheet.createRow(i + 2);
            //给该行数据赋值
            for (int j = 0; j < headList.size(); j++) {
                String value = "";
                try {
                    value = mapList.get(i).get(headList.get(j)).toString();
                } catch (Exception e) {
                    value = "";
                }

                cells = rows.createCell(j);
                cells.setCellValue(value);
            }
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        // 使用项目根目录, 文件名加上时间戳
        String path = System.getProperty("user.dir") + "\\" + filename + dateFormat.format(date) + ".xlsx";
        System.out.println("Excel文件输出路径: " + path);
        try {
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);

            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    public static void main(String[] args) {
        System.out.println("start...");
        List<Map<String, Object>> mapArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("队列", i);
            map.put("生产者", i);
            map.put("消费者", i);
            mapArrayList.add(map);
        }
        Monitor monitor = new Monitor();
        List<Map> list = monitor.getList();

        System.out.println("end...");
        ExcelUtils.getInstance().createExcel(list, "data", "数据");

    }

}
