package util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
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

    private static String charset = "gb2312";

    public static String executeGetRequest(String url) {
        return executeGetRequest(url, null);
    }

    public static String executeGetRequest(String url, Header[] headers) {

        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String responseBody = null;

        try {

            // 创建 HttpGet 实例
            HttpGet httpGet = new HttpGet(url);

            // 设置请求头
            if (headers != null) {
                httpGet.setHeaders(headers);
            }

            // 获取响应对象
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("状态码: " + statusCode);

            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity, charset);
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

    public static String executePostRequest(String url, Map<String, String> map) {

        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String responseBody = null;

        try {

            // 创建 HttpGet 实例
            HttpPost httpPost = new HttpPost(url);

            // 创建请求参数
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> element = (Map.Entry<String, String>) iterator.next();
                formParams.add(new BasicNameValuePair(element.getKey(), element.getValue()));
            }
            if (formParams.size() > 0) {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, charset);
                httpPost.setEntity(formEntity);
            }

            // 获取响应对象
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("状态码: " + statusCode);

            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity, charset);
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
}
