package main;

import entity.Task;
import factory.PluginFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plugins.AbstractPlugin;

import java.util.*;
import java.util.zip.CRC32;

/**
 * 应用入口
 *
 * @author panda
 * @date 2017/10/28
 */
public class SpiderApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpiderApplication.class);

    /**
     * URL 队列
     */
    private static Queue<String> urlQueue = new LinkedList<String>();

    /**
     * URL 排重
     */
    private static Map<Long, Integer> urlPool = new HashMap<Long, Integer>();


    public static void main(String[] args) {

        // 实例化任务对象
        Task task = Task.getBuilder()
                .setUrl("https://www.zhihu.com/api/v4/members/zhi-neng-wan-ka/activities?limit=10&after_id=1514210844&desktop=True")
                .build();

        // 初始化 URL 队列
        urlQueue.add(task.getUrl());
        addUrlPool(task.getUrl(), 1);

        // 循环调度
        String taskUrl;
        while ((taskUrl = urlQueue.poll()) != null) {
            logger.info("当前任务URL：" + taskUrl + "，当前层深：" + getDepth(taskUrl));

            try {
                task.setUrl(taskUrl);
                AbstractPlugin plugin = PluginFactory.getInstance().getPlugin(task);
                plugin.run();

                if (plugin.getUrlList() != null) {
                    int depth = getDepth(taskUrl) + 1;
                    for (String url : plugin.getUrlList()) {
                        if (!isUrlExist(url)) {
                            urlQueue.add(url);
                            addUrlPool(url, depth);
                        }
                    }
                }

                Thread.sleep(300);
            } catch (Exception e) {
                logger.error("出现异常:", e);
                continue;
            }
        }

    }

    /**
     * 添加链接到 url 池
     *
     * @param url
     * @param depth
     */
    private static void addUrlPool(String url, int depth) {
        CRC32 c = new CRC32();
        c.update(url.getBytes());

        urlPool.put(c.getValue(), depth);
    }

    /**
     * 判断 url 是否重复
     *
     * @param url
     * @return
     */
    private static boolean isUrlExist(String url) {

        CRC32 c = new CRC32();
        c.update(url.getBytes());

        return urlPool.containsKey(c.getValue());
    }

    /**
     * 获得层深
     *
     * @param url
     * @return
     */
    private static Integer getDepth(String url) {

        CRC32 c = new CRC32();
        c.update(url.getBytes());

        return urlPool.get(c.getValue()) == null ? 1 : urlPool.get(c.getValue());
    }

}
