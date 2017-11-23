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
import util.HttpUtil;

import java.io.IOException;

/**
 * 下载服务
 *
 * @author panda
 * @date 2017/10/28
 */
public class DownloadService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);

    public String getResponseBody(Task task) {
        return HttpUtil.executeGetRequest(task.getUrl());
    }

}
