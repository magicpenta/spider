package daemon;

import com.google.gson.Gson;
import dao.TaskDao;
import entity.Task;
import entity.enums.TaskStatusEnum;
import main.MultipleTaskRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 进程守护线程
 *
 * @author panda
 * @date 2018/2/25
 */
public class ProcessDaemon extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ProcessDaemon.class);

    /**
     * 休眠时间，默认1分钟
     */
    private static final Integer SLEEP_TIME = 60000;

    private Task task;

    public ProcessDaemon(String message) {
        task = new Gson().fromJson(message, Task.class);
    }

    @Override
    public void run() {
        MultipleTaskRunner.incrementProcess();
        while (true) {
            task = TaskDao.selectOneById(task.getId());

            logger.info("任务{}当前状态为{}...", task.getId(), task.getStatus());

            // 定时检查任务状态，如果任务已执行完毕，则进程数减1
            if (TaskStatusEnum.RUNNED.getValue() == task.getStatus()) {
                logger.info("检查到任务{}已执行完毕，准备退出线程...", task.getId());
                break;
            }

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("线程休眠异常:", e);
            }
        }
        MultipleTaskRunner.decrementProcess();
    }

}
