package plugins;

import entity.Task;
import filter.AndFilter;
import filter.FileExtensionFilter;
import filter.LinkExtractor;
import filter.LinkFilter;
import main.TaskRunner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.DownloadService;
import util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 插件抽象类
 *
 * @author panda
 * @date 2017/12/01
 */
public abstract class AbstractPlugin extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPlugin.class);

    protected Task task;

    public AbstractPlugin(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        logger.info("{} 开始运行...", task.getUrl());
        String body = DownloadService.getInstance().getResponseBody(task);
        if (StringUtils.isNotEmpty(body)) {
            if (isDetailPage(task.getUrl())) {
                logger.info("开始解析详情页...");
                parseContent(body);
            } else {
                logger.info("开始解析列表页...");
                extractPageLinks(body);
            }
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

    public void addUrl(String url) {
        TaskRunner.addUrlQueue(url);
        TaskRunner.addUrlPool(url);
    }

    public void addUrl(List<String> urlList) {
        TaskRunner.addUrlPool(urlList);
        TaskRunner.addUrlQueue(urlList);
    }

    public abstract void parseContent(String body);

    public abstract boolean isDetailPage(String url);

}
