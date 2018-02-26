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

    /**
     * 代理相关常量
     */
    public static final String PROXY_URL = prop.getProperty("proxy_url");
    public static final Integer PROXY_QUANTITY = Integer.valueOf(prop.getProperty("proxy_quantity", "1"));

    public static final Integer REQUEST_SLEEP_TIME = Integer.valueOf(prop.getProperty("request_sleep_time", "5000"));

    /**
     * mq 相关常量
     */
    public static final String AMQ_URL = prop.getProperty("amq_url", "failover:(tcp://localhost:61616)");
    public static final String JMX_URL = prop.getProperty("jmx_url", "service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
    public static final String BROKER_NAME = prop.getProperty("broker_name", "my_broker:brokerName=localhost,type=Broker");
    public static final String TASK_QUEUE_NAME = prop.getProperty("task_queue_name", "MQ_TASK");
    public static final Long QUEUE_MAX_NUM = Long.valueOf(prop.getProperty("queue_max_num", "100"));

    public static final Integer MAX_PROCESS = Integer.valueOf(prop.getProperty("max_process", "10"));

}
