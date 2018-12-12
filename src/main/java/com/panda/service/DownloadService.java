package com.panda.service;

import com.panda.dao.common.RequestConfigDao;
import com.panda.entity.*;
import com.panda.entity.common.ProxyDO;
import com.panda.entity.common.RequestConfigDO;
import com.panda.entity.common.TaskDO;
import com.panda.factory.ProxyFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.panda.util.CommonUtil;
import com.panda.util.HttpUtil;

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

    private static List<RequestConfigDO> requestConfigList;

    static {
        requestConfigList = RequestConfigDao.getInstance().selectAll();
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

    public String getResponseBody(TaskDO task) {
        if (task == null || StringUtils.isEmpty(task.getUrl())) {
            return null;
        }

        String responseBody = null;

        try {
            String domain = CommonUtil.getDomain(task.getUrl());
            RequestConfigDO requestConfig = this.selectOneByDomain(domain);

            HttpParams httpParams = this.initHttpParams(task, requestConfig);

            logger.info("准备开始下载源码...");
            responseBody = httpUtil.executeGetRequest(httpParams);
        } catch (Exception e) {
            logger.error("下载服务出现异常:", e);
        }
        return responseBody;
    }

    private HttpParams initHttpParams(TaskDO task, RequestConfigDO requestConfig) {

        if (requestConfig == null) {
            HttpParams httpParams = HttpParams.getBuilder()
                    .setUrl(task.getUrl())
                    .build();
            return httpParams;
        }

        if (requestConfig.getNeedCookie()) {

        }

        ProxyDO proxy = null;
        if (requestConfig.getNeedProxy()) {
            proxy = ProxyFactory.getInstance().getProxy();
        }

        Map<String, String> headerMap = new HashMap<String, String>(16);

        String requestHeaderStr = requestConfig.getRequestHeaders();
        if (StringUtils.isNotEmpty(requestHeaderStr)) {
            String[] requestHeaders = requestHeaderStr.split("###");
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
        }

        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpParams httpParams = HttpParams.getBuilder()
                .setUrl(task.getUrl())
                .setProxy(proxy)
                .setHeaderMap(headerMap)
                .build();

        return httpParams;
    }

    private RequestConfigDO selectOneByDomain(String domain) {

        if (requestConfigList == null || requestConfigList.size() == 0) {
            return null;
        }

        RequestConfigDO result = null;

        for (RequestConfigDO requestConfig : requestConfigList) {
            if (domain.equals(requestConfig.getDomain())) {
                result = requestConfig;
                break;
            }
        }

        return result;
    }

}
