package main;

import api.ProxyApi;
import config.Constants;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 代理获取进程
 *
 * @author panda
 * @date 2018/2/4
 */
public class ProxyRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProxyRunner.class);

    public static void main(String[] args) {

        Properties prop = new Properties();
        try {
            // 重新加载日志配置文件
            prop.load(ProxyRunner.class.getClassLoader().getResourceAsStream("log4j.properties"));
            prop.setProperty("log4j.appender.file.File", "log/proxy.log");
            PropertyConfigurator.configure(prop);
        } catch (IOException e) {
        }

        logger.info("启动定时更新代理服务...");

        while (true) {
            ProxyApi.requestProxyList();
            try {
                Thread.sleep(Constants.REQUEST_SLEEP_TIME);
            } catch (InterruptedException e) {

            }
        }
    }

}
