package service;

import entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpUtil;

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
