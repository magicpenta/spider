package main;

import entity.Task;
import factory.PluginFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plugins.AbstractPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.zip.CRC32;

/**
 * 应用入口
 *
 * @author panda
 * @date 2017/10/28
 */
public class SpiderApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpiderApplication.class);

    private static Queue<String> urlQueue = new PriorityBlockingQueue<String>();

    private static Set<Long> urlPool = new HashSet<Long>();


    public static void main(String[] args) {

        Task task = Task.getBuilder()
                .setUrl("http://sm.xmu.edu.cn/html/intl/")
                .build();

        urlQueue.add(task.getUrl());

        String taskUrl;
        while ((taskUrl = urlQueue.poll()) != null) {
            logger.info("当前任务URL：" + taskUrl);
            task.setUrl(taskUrl);

            AbstractPlugin plugin = PluginFactory.getInstance().getPlugin(task);
            plugin.run();

            if (plugin.getUrlList() != null) {
                for (String url : plugin.getUrlList()) {
                    if (isUrlExist(url)) {
                        urlQueue.add(url);
                    }
                }
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 判断 url 是否重复
     * @param url
     * @return
     */
    private static boolean isUrlExist(String url) {

        CRC32 c = new CRC32();
        c.update(url.getBytes());

        if (!urlPool.contains(c.getValue())) {
            urlPool.add(c.getValue());
            return true;
        }

        return false;
    }

}
