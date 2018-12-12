package com.panda.util;

import org.junit.Test;

import java.util.List;

/**
 * csv工具类单元测试
 *
 * @author panda
 * @date 2018/11/13
 */
public class CsvUtilTest {

    @Test
    public void testGetDataList() {
        List<String[]> dataList = CsvUtil.readCsv("C:\\Users\\37516\\Desktop\\final\\test\\4old1667_4_0.csv");
        assert dataList.size() > 0;
    }

}
