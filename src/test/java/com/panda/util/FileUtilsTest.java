package com.panda.util;

import org.junit.Test;

/**
 * File工具类单元测试
 *
 * @author panda
 * @date 2017/11/18
 */
public class FileUtilsTest {

    @Test
    public void testReadFile() {
        String content = FileUtils.readFile("test.txt");
        System.out.println(content);
    }

    @Test
    public void testWriteFile() {
        FileUtils.writeFile("panda.txt", "这是一个测试。");
    }

}
