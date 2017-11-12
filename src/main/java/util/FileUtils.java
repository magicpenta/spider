package util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Demo class
 *
 * @author xiepd
 * @date 2017/10/28
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final String FILE_PATH = FileUtils.class.getClassLoader().getResource("").getPath();

    public static String readFile(String fileName, String charset) {

        File file = new File(FILE_PATH + fileName);
        String content = "";

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), charset));
            String temp;
            while ((temp = reader.readLine()) != null) {
                content += temp;
            }


        } catch (Exception e) {
            logger.error("读取文件异常：" + e);
        }

        return content;
    }

    public static void writeFile() {
        File file = new File(FILE_PATH + File.separator + "out.txt");

        try {
            OutputStream out = new FileOutputStream(file);
            String text = "一";
            out.write(text.getBytes("GBK"));
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
