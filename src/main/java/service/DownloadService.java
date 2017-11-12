package service;

import entity.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 下载服务
 *
 * @author xiepd
 * @date 2017/10/28
 */
public class DownloadService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);

    public String getResponseBody(Task task) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String responseBody = null;

        try {

            HttpGet httpGet = new HttpGet(task.getUrl());

            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (StringUtils.isEmpty(task.getCharset())) {
                    task.setCharset("UTF-8");
                }
                responseBody = EntityUtils.toString(entity, task.getCharset());
            } else {
                logger.info("下载页面源码失败，状态码：" + statusCode);
            }

        } catch (Exception e) {
            logger.error("下载页面源码出现异常：", e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return responseBody;
    }

}
