package util;

import entity.HttpParams;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * httpClient工具类
 *
 * @author panda
 * @date 2017/10/28
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String executeGetRequest(String url) {
        if (url == null || "".equals(url)) {
            logger.warn("url不能为空！");
            return null;
        }
        HttpParams httpParams = HttpParams.getBuilder()
                .setUrl(url)
                .build();
        return executeGetRequest(httpParams);
    }

    public static String executeGetRequest(HttpParams httpParams) {

        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = initHttpClient(httpParams);
        String responseBody = null;

        try {

            // 创建 HttpGet 实例
            HttpGet httpGet = new HttpGet(httpParams.getUrl());

            // 设置 RequestConfig
            if (httpParams.getRequestConfig() != null) {
                httpGet.setConfig(httpParams.getRequestConfig());
            } else {
                httpGet.setConfig(initRequestConfig(httpParams));
            }

            // 设置请求头
            if (httpParams.getHeaderMap() != null) {
                Header[] headers = getHeadersByMap(httpParams.getHeaderMap());
                httpGet.setHeaders(headers);
            }

            // 获取响应对象
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("状态码: " + statusCode);

            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity, httpParams.getCharset());
            }

        } catch (Exception e) {
            logger.error("发送GET请求出现异常：", e);
        } finally {
            // 关闭连接
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("关闭连接异常：", e);
                }
            }
        }

        return responseBody;
    }

    public static String executePostRequest(HttpParams httpParams) {

        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = initHttpClient(httpParams);
        String responseBody = null;

        try {

            // 创建 HttpGet 实例
            HttpPost httpPost = new HttpPost(httpParams.getUrl());

            // 设置 RequestConfig
            if (httpParams.getRequestConfig() != null) {
                httpPost.setConfig(httpParams.getRequestConfig());
            } else {
                httpPost.setConfig(initRequestConfig(httpParams));
            }

            // 设置请求头
            if (httpParams.getHeaderMap() != null) {
                Header[] headers = getHeadersByMap(httpParams.getHeaderMap());
                httpPost.setHeaders(headers);
            }

            // 创建请求参数
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            Iterator iterator = httpParams.getFormParams().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> element = (Map.Entry<String, String>) iterator.next();
                formParams.add(new BasicNameValuePair(element.getKey(), element.getValue()));
            }
            if (formParams.size() > 0) {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, httpParams.getCharset());
                httpPost.setEntity(formEntity);
            }

            // 获取响应对象
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("状态码: " + statusCode);

            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity, httpParams.getCharset());
            }

        } catch (Exception e) {
            logger.error("发送POST请求出现异常：", e);
        } finally {
            // 关闭连接
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("关闭连接异常：", e);
                }
            }
        }

        return responseBody;
    }

    private static CloseableHttpClient initHttpClient(HttpParams httpParams) {

        CloseableHttpClient httpClient = null;

        if (httpParams == null) {
            httpClient = HttpClients.createDefault();
        }

        if (httpParams.getNeedProxy()) {
            String userName = httpParams.getProxy().getProxyUserName();
            String password = httpParams.getProxy().getProxyPassword();

            if (userName != null && password != null) {
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(
                        new AuthScope(AuthScope.ANY),
                        new UsernamePasswordCredentials(userName, password));
                httpClient = HttpClients.custom()
                        .setDefaultCredentialsProvider(credsProvider)
                        .build();
            }
        }

        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }

        return httpClient;
    }

    private static RequestConfig initRequestConfig(HttpParams httpParams) {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .build();

        if (httpParams == null) {
            return requestConfig;
        }

        if (httpParams.getNeedProxy()) {
            String proxyIp = httpParams.getProxy().getProxyIp();
            Integer proxyPort = httpParams.getProxy().getProxyPort();

            if (proxyIp != null && proxyPort != null) {
                HttpHost proxy = new HttpHost(proxyIp, proxyPort);
                requestConfig = RequestConfig.copy(requestConfig)
                        .setProxy(proxy)
                        .build();
            }
        }

        return requestConfig;
    }

    private static Header[] getHeadersByMap(Map<String, String> headerMap) {
        int index = 0;
        Header[] headers = new Header[headerMap.size()];
        Iterator iterator = headerMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> element = (Map.Entry<String, String>) iterator.next();
            headers[index] = new BasicHeader(element.getKey(), element.getValue());
            index++;
        }
        return headers;
    }

}
