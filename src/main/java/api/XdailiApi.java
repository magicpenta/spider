package api;

import entity.Proxy;
import factory.ProxyFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * spider
 *
 * @author panda
 * @date 2017/12/6
 */
public class XdailiApi {

    private static final Logger logger = Logger.getLogger(XdailiApi.class);

    private static final String API_URL = "http://api.xdaili.cn/xdaili-api//greatRecharge/getGreatIp?spiderId=7241b3292cad49568a2f24783f881410&orderno=YZ20171266537pxvwQo&returnType=1&count=20";

    public static void requestProxyList() {

        String responseBody = HttpUtil.executeGetRequest(API_URL);

        if (StringUtils.isEmpty(responseBody)) {
            logger.error("请求讯代理失败！");
            return;
        }

        try {
            List<Proxy> proxyList = new ArrayList<Proxy>();
            String[] proxyArray = responseBody.split("\r\n");

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
            logger.error("请求讯代理 API 失败:", e);
        }

    }
}
