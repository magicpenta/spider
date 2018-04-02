package main;

import com.google.gson.Gson;
import daemon.TaskDaemon;
import dao.TaskDao;
import entity.Task;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * 单进程入口
 *
 * @author panda
 * @date 2017/10/28
 */
public class SingleTaskRunner extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(SingleTaskRunner.class);


    public static void main(String[] args) {

        logger.info("任务进程开始执行...");

        if (args.length == 0) {
            logger.error("获取进程参数失败，退出进程！");
            System.exit(0);
        }

        String json = new String(Base64.getDecoder().decode(args[0]));

        Task task = new Gson().fromJson(json, Task.class);
//        Task task = TaskDao.selectOneById(1);

        Properties prop = new Properties();
        try {
            // 重新加载日志配置文件
            prop.load(SingleTaskRunner.class.getClassLoader().getResourceAsStream("log4j.properties"));
            prop.setProperty("log4j.appender.file.File", "log/task_" + task.getId() + ".log");
            PropertyConfigurator.configure(prop);
        } catch (IOException e) {
            logger.error("加载日志文件异常:", e);
        }

        // 开启守护线程
        TaskDaemon taskDaemon = new TaskDaemon();
        taskDaemon.setDaemon(true);
        taskDaemon.setPriority(MAX_PRIORITY);
        taskDaemon.start();

        new TaskRunner(task).start();

    }

}
