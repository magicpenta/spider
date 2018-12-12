package com.panda.util;

import com.panda.annotation.ChineseName;
import com.panda.filter.ExcelFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * excel工具类
 *
 * @author panda
 * @date 2018/11/11
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final Integer MAX_SIZE = 65535;

    public static <T> void exportExcel(String filePath, String fileName, String[] fields, List<T> dataList) {

        HSSFWorkbook workbook = new HSSFWorkbook();

        int dataSize = dataList.size();
        int sheetNum = dataSize % MAX_SIZE == 0 ? dataSize / MAX_SIZE : dataSize / MAX_SIZE + 1;
        for (int i = 0; i < sheetNum; i++) {
            int startIndex = i * MAX_SIZE;
            int endIndex;
            if (i == sheetNum - 1) {
                endIndex = dataSize;
            } else {
                endIndex = (i + 1) * MAX_SIZE;
            }
            String sheetName = "Sheet" + i;
            logger.info("sheetName:[{}], startIndex:[{}], endIndex:[{}]", sheetName, startIndex, endIndex);
            HSSFSheet sheet = workbook.createSheet(sheetName);
            List<T> subDataList = dataList.subList(startIndex, endIndex);
            writeToSheet(workbook, sheet, fields, subDataList);
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filePath + File.separator + fileName));
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                logger.error("关闭excel文件流异常: ", e);
            }
        }
    }

    private static <T> void writeToSheet(Workbook workbook, HSSFSheet sheet, String[] fields, List<T> dataList) {
        // 创建标题栏
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            try {
                Field field = dataList.get(0).getClass().getDeclaredField(fields[i]);
                field.setAccessible(true);
                HSSFCell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(field.getAnnotation(ChineseName.class).value());
            } catch (NoSuchFieldException e) {
                logger.error("字段名称有误: ", e);
                return;
            }
        }

        // 创建数据栏
        for (int i = 0; i < dataList.size(); i++) {
            HSSFRow dataRow = sheet.createRow(i + 1);
            for (int j = 0; j < fields.length; j++) {
                HSSFCell dataCell = dataRow.createCell(j);
                try {
                    Field field = dataList.get(i).getClass().getDeclaredField(fields[j]);
                    field.setAccessible(true);
                    Object value = field.get(dataList.get(i));
                    if (value instanceof Long || value instanceof Integer
                            || value instanceof Float || value instanceof Double) {
                        dataCell.setCellValue(Double.parseDouble(String.valueOf(value)));
                    } else if (value instanceof Date) {
                        dataCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value));
                    } else {
                        String valueStr = (String) value;
                        if (StringUtils.isNumeric(valueStr)) {
                            dataCell.setCellValue(Double.parseDouble(valueStr));
                        } else {
                            dataCell.setCellValue((String) value);
                        }
                    }
                } catch (Exception e) {
                    logger.error("反射获取字段值异常: ", e);
                }
            }
        }
    }

    public static List<HSSFWorkbook> getWorkBookList(String directoryPath) {
        List<HSSFWorkbook> workbookList = new ArrayList<HSSFWorkbook>();
        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            File[] fileList = directory.listFiles(new ExcelFileFilter());
            logger.info("读取文件数: " + fileList.length);
            for (File file : fileList) {
                HSSFWorkbook workbook = null;
                try {
                    FileInputStream in = new FileInputStream(file);
                    workbook = new HSSFWorkbook(in);
                } catch (IOException e) {

                }
                workbookList.add(workbook);
            }
        }
        return workbookList;
    }

    public static List<String[]> getDataList(String directoryPath) {
        List<HSSFWorkbook> workbookList = getWorkBookList(directoryPath);

        List<String[]> dataList = new ArrayList<String[]>();
        for (HSSFWorkbook workbook : workbookList) {
            int sheetNum = workbook.getNumberOfSheets();
            for (int num = 0; num < sheetNum; num++) {
                HSSFSheet sheet = workbook.getSheetAt(num);
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                    HSSFRow row = sheet.getRow(i);
                    String[] data = new String[100];
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        HSSFCell cell = row.getCell(j);
                        data[j] = cell.getStringCellValue();
                    }
                    dataList.add(data);
                }
            }
        }
        return dataList;
    }
}
