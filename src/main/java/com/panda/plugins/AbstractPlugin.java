package com.panda.plugins;

import com.panda.entity.common.TaskDO;
import com.panda.filter.AndFilter;
import com.panda.filter.FileExtensionFilter;
import com.panda.filter.LinkExtractor;
import com.panda.filter.LinkFilter;
import com.panda.main.TaskRunner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.panda.service.DownloadService;
import com.panda.util.CommonUtil;

import java.util.List;

/**
 * 插件抽象类
 *
 * @author panda
 * @date 2017/12/01
 */
public abstract class AbstractPlugin extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPlugin.class);

    private static final Integer MAX_RETRY_TIMES = 5;

    protected TaskDO task;

    private static DownloadService downloadService;

    public AbstractPlugin(TaskDO task) {
        this.task = task;
        if (downloadService == null) {
            synchronized (AbstractPlugin.class) {
                downloadService = new DownloadService();
            }
        }
    }

    @Override
    public void run() {
        int count = 1;
        while (count <= MAX_RETRY_TIMES) {
            try {
                logger.info("第[{}]次运行: {}", count, task.getUrl());
                String body = downloadService.getResponseBody(task);
                if (StringUtils.isEmpty(body)) {
                    throw new NullPointerException();
                }
                if (isDetailPage(task.getUrl())) {
                    logger.info("开始解析详情页...");
                    parseContent(body);
                } else {
                    logger.info("开始解析列表页...");
                    extractPageLinks(body);
                }
                break;
            } catch (Exception e) {
                logger.error("插件解析异常: ", e);
                count++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    // ignore
                }
                continue;
            }
        }
        if (count > MAX_RETRY_TIMES) {
            logger.info("任务链接有误或插件异常, 任务解析失败...");
            TaskRunner.setError(true);
        }
    }

    public void extractPageLinks(String body) {

        LinkFilter hostFilter = new LinkFilter() {
            String urlHost = CommonUtil.getUrlPrefix(task.getUrl());

            @Override
            public boolean accept(String link) {
                return link.contains(urlHost);
            }
        };

        String[] fileExtensions = (".xls,.xml,.txt,.pdf,.jpg,.mp3,.mp4,.doc,.mpg,.mpeg,.jpeg,.gif,.png,.js,.zip," +
                ".rar,.exe,.swf,.rm,.ra,.asf,.css,.bmp,.pdf,.z,.gz,.tar,.cpio,.class").split(",");
        LinkFilter fileExtensionFilter = new FileExtensionFilter(fileExtensions);

        AndFilter filter = new AndFilter(new LinkFilter[]{hostFilter, fileExtensionFilter});

        List<String> urlList = LinkExtractor.extractLinks(task.getUrl(), body, filter);
        addUrl(urlList);
    }

    public void forceAddUrl(String url) {
        TaskRunner.forceAddUrlQueue(url);
    }

    public boolean addUrl(String url) {
        boolean result = TaskRunner.addUrlQueue(url);
        if (result) {
            TaskRunner.addUrlPool(url);
            return true;
        } else  {
            return false;
        }
    }

    public void addUrl(List<String> urlList) {
        TaskRunner.addUrlPool(urlList);
        TaskRunner.addUrlQueue(urlList);
    }

    public abstract void parseContent(String body) throws Exception;

    public abstract boolean isDetailPage(String url);

}
