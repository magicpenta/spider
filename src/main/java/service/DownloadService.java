package service;

import dao.WebConfigDao;
import entity.HttpParams;
import entity.Proxy;
import entity.Task;
import entity.WebConfig;
import factory.ProxyFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CommonUtil;
import util.HttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下载服务
 *
 * @author panda
 * @date 2017/10/28
 */
public class DownloadService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);

    private static List<WebConfig> webConfigs;

    static {
        webConfigs = WebConfigDao.selectList();
    }

    private HttpUtil httpUtil = new HttpUtil();

    public String getResponseBody(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        HttpParams httpParams = HttpParams.getBuilder()
                .setUrl(url)
                .build();
        return httpUtil.executeGetRequest(httpParams);
    }

    public String getResponseBody(Task task) {
        if (task == null || StringUtils.isEmpty(task.getUrl())) {
            return null;
        }

        String responseBody = null;

        try {
            String domain = CommonUtil.getHost(task.getUrl());
            WebConfig webConfig = this.selectOneByDomain(domain);

            HttpParams httpParams = this.initHttpParams(task, webConfig);

            logger.info("准备开始下载源码...");
            responseBody = httpUtil.executeGetRequest(httpParams);
        } catch (Exception e) {
            logger.error("下载服务出现异常:", e);
        }
        return responseBody;
    }

    private HttpParams initHttpParams(Task task, WebConfig webConfig) {

        if (webConfig == null) {
            HttpParams httpParams = HttpParams.getBuilder()
                    .setUrl(task.getUrl())
                    .build();
            return httpParams;
        }

        if (webConfig.getNeedCookie()) {

        }

        Proxy proxy = null;
        if (webConfig.getNeedProxy()) {
            proxy = ProxyFactory.getInstance().getProxy();
        }

        String[] requestHeaders = webConfig.getRequestHeaders().split(";");
        Map<String, String> headerMap = new HashMap<String, String>(16);

        if (requestHeaders != null) {
            try {
                for (String requestHeader : requestHeaders) {
                    String requestParam = requestHeader.split(":")[0];
                    String requestValue = requestHeader.split(":")[1];
                    headerMap.put(requestParam, requestValue);
                }
            } catch (Exception e) {
                logger.error("请求头设置异常:", e);
            }
        }

        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpParams httpParams = HttpParams.getBuilder()
                .setUrl(task.getUrl())
                .setProxy(proxy)
                .setHeaderMap(headerMap)
                .build();

        return httpParams;
    }

    private WebConfig selectOneByDomain(String domain) {

        if (webConfigs == null || webConfigs.size() == 0) {
            return null;
        }

        WebConfig result = null;

        for (WebConfig webConfig : webConfigs) {
            if (domain.equals(webConfig.getDomain())) {
                result = webConfig;
                break;
            }
        }

        return result;
    }

}
