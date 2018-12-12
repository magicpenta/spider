package com.panda.util;

import com.csvreader.CsvReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * csv工具类
 *
 * @author panda
 * @date 2018/11/13
 */
public class CsvUtil {

    private static final Logger logger = LoggerFactory.getLogger(CsvUtil.class);

    public static List<String[]> readCsv(String filePath) {
        return readCsv(filePath, "GBK");
    }

    public static List<String[]> readCsv(String filePath, String charset) {
        List<String[]> csvList = new ArrayList<String[]>();
        try {
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName(charset));
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                csvList.add(reader.getValues());
            }
            reader.close();
        } catch (Exception e) {
            logger.info("读取csv文件异常: " + filePath, e);
        }
        return csvList;
    }

}
