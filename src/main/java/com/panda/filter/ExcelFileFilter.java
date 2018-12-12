package com.panda.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * excel文件过滤器
 *
 * @author panda
 * @date 2018/11/13
 */
public class ExcelFileFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        if (name.contains(".xls")) {
            return true;
        }
        return false;
    }

}
