package com.panda.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * File工具类
 *
 * @author panda
 * @date 2017/10/28
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final String FILE_PATH = FileUtils.class.getClassLoader().getResource("").getPath();

    public static String readFile(String fileName) {
        return readFile(fileName, "utf-8");
    }

    public static String readFile(String fileName, String charset) {

        File file = new File(FILE_PATH + fileName);
        BufferedReader reader = null;
        String content = "";

        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), charset));
            String temp;
            while ((temp = reader.readLine()) != null) {
                content += temp;
            }

        } catch (Exception e) {
            logger.error("读取文件异常：" + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        return content;
    }

    public static void writeFile(String fileName, String content) {
        writeFile(fileName, content, "utf-8");
    }

    public static void writeFile(String fileName, String content, String charset) {

        File file = new File(FILE_PATH + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error("创建文件异常：" + e);
            }
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(file), charset)));
            writer.print(content);
        } catch (Exception e) {
            logger.error("文件写出异常：" + e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

}
