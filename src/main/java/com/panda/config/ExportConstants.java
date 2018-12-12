package com.panda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * spider
 *
 * @author panda
 * @date 2018/11/25
 */
public class ExportConstants {

    private static final Logger logger = LoggerFactory.getLogger(ExportConstants.class);

    private static final String CONFIG_PATH = "export.properties";

    private static Properties prop;

    static {
        prop = new Properties();
        try {
            prop.load(Constants.class.getClassLoader().getResourceAsStream(CONFIG_PATH));
        } catch (IOException e) {
            logger.error("加载全局配置文件异常:", e);
        }
    }

    public static final String EXPORT_FILE_PATH = prop.getProperty("export.file.path");

    public static final String RECENT_DATA_FIELDS = prop.getProperty("recent.comment.fields");

    public static final String HISTORY_DATA_FIELDS = prop.getProperty("history.comment.fields");

    public static final String APPEND_FIELDS = prop.getProperty("append.fields");

    public static final Integer EXPORT_DATA_TYPE = Integer.valueOf(prop.getProperty("export.data.type", "0"));
}
