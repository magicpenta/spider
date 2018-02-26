package main;

import com.google.gson.Gson;
import config.Constants;
import dao.TaskDao;
import entity.Task;
import entity.enums.TaskStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ActiveJmxService;
import service.ActivemqService;

import java.util.List;

/**
 * 任务采集器
 * 发送任务到 MQ
 *
 * @author panda
 * @date 2018/2/24
 */
public class TaskCrawler {

    private static final Logger logger = LoggerFactory.getLogger(TaskCrawler.class);

    /**
     * 休眠时间，默认1分钟
     */
    private static final Integer SLEEP_TIME = 60000;

    /**
     * 队列最大上限
     */
    private static final Long QUEUE_MAX_NUM = Constants.QUEUE_MAX_NUM;

    public static void main(String[] args) {
        logger.info("开始加载任务...");

        String queueName = Constants.TASK_QUEUE_NAME;
        ActivemqService service = new ActivemqService();
        ActiveJmxService jmxService = ActiveJmxService.getInstance();

        while (true) {

            List<Task> taskList = TaskDao.selectListByStatus(TaskStatusEnum.NOT_RUNNING.getValue());

            if (taskList == null || taskList.size() == 0) {
                logger.info("所有任务已加载完毕, 退出程序");
                break;
            }

            Long queueSize = jmxService.getQueueSize(queueName);
            if (queueSize == null || queueSize < QUEUE_MAX_NUM) {

                logger.info("成功加载{}个任务...", taskList.size());

                for (int i = 0; i < taskList.size(); i++) {
                    Task nowTask = taskList.get(i);
                    String message = new Gson().toJson(nowTask);
                    try {
                        service.sendMessage(queueName, message);

                        // 修改任务状态为 正在运行中
                        nowTask.setStatus(TaskStatusEnum.RUNNING.getValue());
                        TaskDao.updateStatus(nowTask);
                    } catch (Exception e) {
                        logger.error("发送消息到 MQ 队列异常:", e);
                    }
                }
            } else {
                logger.info("当前队列剩余:{}, 已达上限...", queueSize);
            }

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("线程休眠异常:", e);
            }

        }

        service.close();

    }

}
