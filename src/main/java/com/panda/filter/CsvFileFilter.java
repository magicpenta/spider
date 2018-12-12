package com.panda.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * csv文件过滤器
 *
 * @author panda
 * @date 2018/11/13
 */
public class CsvFileFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        if (name.contains(".csv") && name.contains("old")) {
            return true;
        }
        return false;
    }

}
