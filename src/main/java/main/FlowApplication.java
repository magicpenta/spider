package main;

import api.WuyouApi;
import entity.HttpParams;
import entity.Proxy;
import factory.ProxyFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpUtil;

/**
 * 流量
 *
 * @author panda
 * @date 2017/12/06
 */
public class FlowApplication {

    private static final Logger logger = LoggerFactory.getLogger(FlowApplication.class);

    public static void main(String[] args) {

        int failureCount = 0;

        for (int i = 0; i < 100; i++) {

            logger.info("开始第 {} 轮访问...", i + 1);

            try {
                WuyouApi.requestProxyList();

                String targetUrl = "http://blog.csdn.net/magicpenta/article/details/78701646";

                Proxy proxy = ProxyFactory.getProxy();

                logger.info("使用代理：" + proxy.getProxyIp() + ":" + proxy.getProxyPort());
                HttpParams httpParams = HttpParams.getBuilder()
                        .setUrl(targetUrl)
                        .setNeedProxy(true)
                        .setProxy(proxy)
                        .build();

                String responseBody = HttpUtil.executeGetRequest(httpParams);
                if (StringUtils.isEmpty(responseBody)) {
                    failureCount++;
                    logger.info("失败次数：" + failureCount);
                }

                Thread.sleep(5000);
            } catch (Exception e) {
                continue;
            }
        }
    }

}
