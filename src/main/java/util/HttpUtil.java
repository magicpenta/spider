package util;

import config.Constants;
import dao.ProxyDao;
import entity.HttpParams;
import factory.ProxyFactory;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * httpClient工具类
 *
 * @author panda
 * @date 2017/10/28
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public String executeGetRequest(HttpParams httpParams) {

        GetMethod getMethod = null;
        String responseBody = null;
        int i = 0;

        if (httpParams.getUrl().contains("知乎用户")) {
            logger.info("非法链接, 不采集!");
            return responseBody;
        }

        while (true) {
            try {

                // 创建 HttpClient 对象
                HttpClient httpClient = new HttpClient();
                httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(Constants.CONNECTION_TIMEOUT);
                httpClient.getHttpConnectionManager().getParams().setSoTimeout(Constants.CONNECTION_TIMEOUT);
                if (httpParams.getProxy() != null) {

                    if (i >= 0) {
                        httpParams.setProxy(ProxyFactory.getInstance().getProxy());
                    }
                    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                            httpParams.getProxy().getProxyUserName(), httpParams.getProxy().getProxyPassword());
                    httpClient.getState().setProxyCredentials(AuthScope.ANY, credentials);
                    String proxyIp = httpParams.getProxy().getProxyIp();
                    Integer proxyPort = httpParams.getProxy().getProxyPort();
                    logger.info("使用代理-->" + proxyIp + ":" + proxyPort);
                    httpClient.getHostConfiguration().setProxy(proxyIp, proxyPort);
                }

                i++;
                // 创建 GetMethod 实例
                getMethod = new GetMethod(httpParams.getUrl());

                getMethod.setRequestHeader("Accept", "application/json, text/plain, */*");
                getMethod.setRequestHeader("Cache-Control", "max-age=0");
                getMethod.setRequestHeader("Connection", "close");

                // 设置请求头
                if (httpParams.getHeaderMap() != null) {
                    Header[] headers = getHeadersByMap(httpParams.getHeaderMap());
                    if (headers != null) {
                        for (Header header : headers) {
                            getMethod.setRequestHeader(header);
                        }
                    }
                }

                int statusCode = httpClient.executeMethod(getMethod);

                if (statusCode == HttpStatus.SC_NOT_FOUND || statusCode == HttpStatus.SC_GONE) {
                    break;
                }

                if (statusCode == HttpStatus.SC_OK) {
                    InputStream in = getMethod.getResponseBodyAsStream();
                    responseBody = this.getResponseBody(in);
                }

                if (responseBody != null) {
                    break;
                }
            } catch (Exception e) {
                logger.info("发送GET请求出现异常, 重新请求:", e);
                continue;
            } finally {
                if (getMethod != null) {
                    try {
                        getMethod.releaseConnection();
                    } catch (Exception e) {
                        logger.error("关闭响应对象异常：", e);
                    }
                }
            }
        }

        return responseBody;
    }

    private Header[] getHeadersByMap(Map<String, String> headerMap) {
        int index = 0;
        Header[] headers = new Header[headerMap.size()];
        Iterator iterator = headerMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> element = (Map.Entry<String, String>) iterator.next();
            headers[index] = new Header(element.getKey(), element.getValue());
            index++;
        }
        return headers;
    }

    private String getResponseBody(InputStream in) throws Exception {
        String responseBody = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

            String temp;
            while ((temp = reader.readLine()) != null) {
                responseBody += temp;
            }
        } finally {
            in.close();
        }
        return responseBody;
    }

}
