package service;

import dao.WebConfigDao;
import entity.HttpParams;
import entity.Task;
import entity.WebConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CommonUtil;
import util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 下载服务
 *
 * @author panda
 * @date 2017/10/28
 */
public class DownloadService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);

    public static String getResponseBody(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        HttpParams httpParams = HttpParams.getBuilder()
                .setUrl(url)
                .build();
        return HttpUtil.executeGetRequest(httpParams);
    }

    public static String getResponseBody(Task task) {
        if (task == null || StringUtils.isEmpty(task.getUrl())) {
            return null;
        }
        String domain = CommonUtil.getHost(task.getUrl());
        WebConfig webConfig = WebConfigDao.selectOneByDomain(domain);
        HttpParams httpParams = initHttpParams(task, webConfig);
        return HttpUtil.executeGetRequest(httpParams);
    }

    private static HttpParams initHttpParams(Task task, WebConfig webConfig) {

        if (webConfig == null) {
            HttpParams httpParams = HttpParams.getBuilder()
                    .setUrl(task.getUrl())
                    .build();
            return httpParams;
        }

        if (Integer.valueOf(1).equals(webConfig.getNeedCookie())) {

        }

        if (Integer.valueOf(1).equals(webConfig.getNeedProxy())) {

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
                .setHeaderMap(headerMap)
                .build();

        return httpParams;
    }

}
