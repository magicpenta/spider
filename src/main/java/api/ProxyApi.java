package api;

import config.Constants;
import dao.ProxyDao;
import entity.Proxy;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import service.DownloadService;

import java.util.ArrayList;
import java.util.List;

/**
 * 代理 api
 *
 * @author panda
 * @date 2017/12/06
 */
public class ProxyApi {

    private static final Logger logger = Logger.getLogger(ProxyApi.class);

    public static void requestProxyList() {

        String responseBody = new DownloadService().getResponseBody(Constants.PROXY_URL);

        if (StringUtils.isEmpty(responseBody)) {
            logger.error("请求代理api失败！");
            return;
        }

        try {
            List<Proxy> proxyList = new ArrayList<Proxy>();
            String[] proxyArray = responseBody.split("\n");
//            String[] proxyArray = responseBody.split("\r\n");

            // 对比代理限制数与请求到的代理数，取最小值
            int count = Constants.PROXY_QUANTITY >= proxyArray.length ? proxyArray.length : Constants.PROXY_QUANTITY;

            for (int i = 0; i < count; i++) {
                String[] proxyStr = proxyArray[i].split(":");
                String proxyIp = proxyStr[0];
                Integer proxyPort = Integer.valueOf(proxyStr[1]);

                logger.info("获取代理ip: " + proxyIp);
                logger.info("获取代理port: " + proxyPort);

                Proxy proxy = new Proxy();
                proxy.setId(i + 1);
                proxy.setProxyIp(proxyIp);
                proxy.setProxyPort(proxyPort);
                proxy.setProxyUserName("");
                proxy.setProxyPassword("");

                proxyList.add(proxy);
            }

            ProxyDao.insertList(proxyList);

        } catch (Exception e) {
            logger.error("请求代理api失败:", e);
        }

    }

}
