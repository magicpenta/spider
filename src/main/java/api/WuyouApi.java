package api;

import entity.Proxy;
import factory.ProxyFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import service.DownloadService;
import util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 无忧 api
 *
 * @author panda
 * @date 2017/12/06
 */
public class WuyouApi {

    private static final Logger logger = Logger.getLogger(WuyouApi.class);

    private static final String API_URL = "http://api.ip.data5u.com/dynamic/get.html?order=8aad992c3ab172d616bf9168e2082bbc&sep=3";

    public static void requestProxyList() {

        String responseBody = DownloadService.getResponseBody(API_URL);

        if (StringUtils.isEmpty(responseBody)) {
            logger.error("请求无忧代理失败！");
            return;
        }

        try {
            List<Proxy> proxyList = new ArrayList<Proxy>();
            String[] proxyArray = responseBody.split("\n");

            for (int i = 0; i < proxyArray.length; i++) {
                String[] proxyStr = proxyArray[i].split(":");
                String proxyIp = proxyStr[0];
                Integer proxyPort = Integer.valueOf(proxyStr[1]);

                logger.info("获取代理ip: " + proxyIp);
                logger.info("获取代理port: " + proxyPort);

                Proxy proxy = new Proxy();
                proxy.setProxyIp(proxyIp);
                proxy.setProxyPort(proxyPort);
                proxy.setProxyUserName("");
                proxy.setProxyPassword("");

                proxyList.add(proxy);
            }

            ProxyFactory.addProxy(proxyList);

        } catch (Exception e) {
            logger.error("请求无忧 API 失败:", e);
        }

    }

}
