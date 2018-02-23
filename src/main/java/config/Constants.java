package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 全局常量
 *
 * @author panda
 * @date 2018/2/3
 */
public class Constants {

    private static final Logger logger = LoggerFactory.getLogger(Constants.class);

    private static final String CONFIG_PATH = "config.properties";

    private static Properties prop;

    static {
        prop = new Properties();
        try {
            prop.load(Constants.class.getClassLoader().getResourceAsStream(CONFIG_PATH));
        } catch (IOException e) {
            logger.error("加载全局配置文件异常:", e);
        }
    }

    public static final Integer CONNECTION_TIMEOUT = Integer.valueOf(prop.getProperty("connection_timeout", "10000"));

    public static final String PROXY_URL = prop.getProperty("proxy_url");

    public static final Integer PROXY_QUANTITY = Integer.valueOf(prop.getProperty("proxy_quantity", "1"));

    public static final Integer REQUEST_SLEEP_TIME = Integer.valueOf(prop.getProperty("request_sleep_time", "5000"));

}
