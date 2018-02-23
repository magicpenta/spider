package service;

import config.Constants;
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

    private static final DownloadService downloadService = new DownloadService();

    private DownloadService() {
        webConfigs = WebConfigDao.selectList();
    }

    public static DownloadService getInstance() {
        return downloadService;
    }

    /**
     * 重复请求次数
     */
    private static final Integer COUNT = 5;

    private List<WebConfig> webConfigs;

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

            for (int i = 0; i < COUNT; i++) {
                HttpParams httpParams = this.initHttpParams(task, webConfig);
                responseBody = httpUtil.executeGetRequest(httpParams);
                if (StringUtils.isEmpty(responseBody)) {
                    logger.error("请求失败，等待下一轮请求...");
                    Thread.sleep(Constants.REQUEST_SLEEP_TIME);
                    continue;
                }
                break;
            }
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
