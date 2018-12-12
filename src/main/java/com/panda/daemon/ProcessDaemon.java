package com.panda.daemon;

import com.google.gson.Gson;
import com.panda.config.Constants;
import com.panda.dao.common.TaskDao;
import com.panda.entity.common.TaskDO;
import com.panda.entity.enums.TaskStatusEnum;
import com.panda.main.MultipleTaskRunner;
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

    private TaskDO task;

    public ProcessDaemon(String message) {
        task = new Gson().fromJson(message, TaskDO.class);
    }

    @Override
    public void run() {
        MultipleTaskRunner.incrementProcess();
        while (true) {
            task = TaskDao.getInstance().selectOneById(task.getId());

            logger.info("任务{}当前状态为{}...", task.getId(), task.getTaskStatus());

            // 定时检查任务状态，如果任务已执行完毕，则进程数减1
            if (TaskStatusEnum.RUNNED.getValue() == task.getTaskStatus() ||
                    TaskStatusEnum.ERROR.getValue() == task.getTaskStatus() ||
                    TaskStatusEnum.TIME_OUT.getValue() == task.getTaskStatus()) {
                logger.info("检查到任务{}已执行完毕，准备退出线程...", task.getId());
                break;
            }

            try {
                Thread.sleep(Constants.TASK_STATUS_INTERVAL);
            } catch (InterruptedException e) {
                logger.error("线程休眠异常:", e);
            }
        }
        MultipleTaskRunner.decrementProcess();
    }

}
