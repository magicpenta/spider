package main;

import dao.TaskDao;
import entity.Task;
import entity.enums.TaskStatusEnum;
import factory.PluginFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plugins.AbstractPlugin;

import java.util.*;
import java.util.zip.CRC32;

/**
 * 任务执行线程
 *
 * @author panda
 * @date 2018/1/14
 */
public class TaskRunner extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(TaskRunner.class);

    private Task task;

    /**
     * URL 队列
     */
    private static Queue<String> urlQueue = new LinkedList<String>();

    /**
     * URL 排重
     */
    private static List<Long> urlPool = new ArrayList<Long>();

    /**
     * 终止标记
     */
    private static boolean isStop = false;

    /**
     * 异常标记
     */
    private static boolean isError = false;

    public TaskRunner(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        logger.info("任务线程开始执行...");
        long beginTimeStamp = System.currentTimeMillis();

        // 初始化 URL 队列
        addUrlQueue(task.getUrl());
        addUrlPool(task.getUrl());

        // 循环调度
        String taskUrl;

        while (true) {
            try {
                if (isStop) {
                    logger.info("任务执行完毕, 准备退出进程...");
                    Thread.sleep(5 * 1000);
                    task.setStatus(TaskStatusEnum.RUNNED.getValue());
                    break;
                }

                long timeMill = System.currentTimeMillis() - beginTimeStamp;
//                logger.info("当前运行时间:" + timeMill);
                if (timeMill > 3600000) {
                    logger.info("进程运行超过 1 小时, 强制退出进程!");
                    task.setStatus(TaskStatusEnum.ERROR.getValue());
                    break;
                }

                if (isError) {
                    logger.info("任务执行异常, 强制退出进程!");
                    task.setStatus(TaskStatusEnum.ERROR.getValue());
                    break;
                }

                taskUrl = urlQueue.poll();

                if (taskUrl == null) {
                    Thread.sleep(1000);
                    continue;
                }

                logger.info("当前任务URL：" + taskUrl);

                task.setUrl(taskUrl);
                AbstractPlugin plugin = PluginFactory.getInstance().getPlugin(task);
                plugin.start();

            } catch (Exception e) {
                logger.error("出现异常:", e);
                continue;
            }
        }

        // 更新任务状态
        TaskDao.updateStatus(task);
        System.exit(0);
    }

    /**
     * 将链接添加到 url 队列
     *
     * @param url
     */
    public static void addUrlQueue(String url) {
        if (!isUrlExist(url)) {
            urlQueue.add(url);
        }
    }

    /**
     * 将链接添加到 url 队列
     *
     * @param urlList
     */
    public static void addUrlQueue(List<String> urlList) {
        for (String url : urlList) {
            if (!isUrlExist(url)) {
                urlQueue.add(url);
            }
        }
    }

    /**
     * 添加链接到 url 池
     *
     * @param url
     */
    public static void addUrlPool(String url) {
        if (!isUrlExist(url)) {
            CRC32 c = new CRC32();
            c.update(url.getBytes());
            urlPool.add(c.getValue());
        }
    }

    /**
     * 添加链接到 url 池
     *
     * @param urlList
     */
    public static void addUrlPool(List<String> urlList) {
        for (String url : urlList) {
            if (!isUrlExist(url)) {
                CRC32 c = new CRC32();
                c.update(url.getBytes());
                urlPool.add(c.getValue());
            }
        }
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

        return urlPool.contains(c.getValue());
    }

    public static void setStop(boolean isStop) {
        TaskRunner.isStop = isStop;
    }

    public static void setError(boolean isError) {
        TaskRunner.isError = isError;
    }
}
