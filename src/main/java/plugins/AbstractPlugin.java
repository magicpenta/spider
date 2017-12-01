package plugins;

import entity.Task;
import filter.LinkExtractor;
import filter.LinkFilter;
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

    protected Task task;

    protected DownloadService downloadService = new DownloadService();

    private List<String> urlList = new ArrayList<String>();

    public AbstractPlugin(Task task) {
        this.task = task;
    }

    public void extractPageLinks(String body) {
        LinkFilter filter = new LinkFilter() {
            String urlHost = CommonUtil.getHost(task.getUrl());
            public boolean accept(String link) {
                return link.contains(urlHost);
            }
        };
        urlList = LinkExtractor.extractLinks(body, filter);
//        for (int i = 0; i < urlList.size(); i++) {
//            System.out.println("linkUrl:" + urlList.get(i));
//        }
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public abstract void parseContent(String body);

    public abstract boolean isDetailPage(String url);

}
