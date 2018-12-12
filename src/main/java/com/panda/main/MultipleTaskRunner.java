package com.panda.main;

import com.panda.config.Constants;
import com.panda.daemon.ProcessDaemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.panda.service.ActivemqService;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多进程入口
 *
 * @author panda
 * @date 2018/1/14
 */
public class MultipleTaskRunner {

    private static final Logger logger = LoggerFactory.getLogger(MultipleTaskRunner.class);

    /**
     * 当前运行进程总数
     */
    private static AtomicInteger processCount = new AtomicInteger(0);

    public static void main(String[] args) {

        String queueName = Constants.TASK_QUEUE_NAME;
        ActivemqService service = new ActivemqService();
        service.createConsumer(queueName);

        String cmd = getCmd();

        while (true) {
            try {
                int nowProcessNum = processCount.get();
                int maxProcessNum = Constants.MAX_PROCESS;

                if (nowProcessNum >= maxProcessNum) {
                    logger.info("当前进程数 {} 大于最大进程数 {}, 不开启新进程...", nowProcessNum, maxProcessNum);
                    Thread.sleep(Constants.TASK_INTERVAL);
                    continue;
                }

                logger.info("准备从 MQ 加载任务...");
                String message = service.getMessage();

                Runtime runtime = Runtime.getRuntime();

                Process process = runtime.exec(cmd + " " + Base64.getEncoder().encodeToString(message.getBytes(Charset.forName("UTF-8"))));
                new ProcessDaemon(message).start();
                new RuntimeThread(process.getErrorStream()).start();
                new RuntimeThread(process.getInputStream()).start();
            } catch (Exception e) {
                logger.error("启动进程异常:", e);
            }
        }
    }

    public static void incrementProcess() {
        processCount.incrementAndGet();
    }

    public static void decrementProcess() {
        processCount.decrementAndGet();
    }

    public static String getCmd() {
        String cmd;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            cmd = "java -Dfile.encoding=UTF-8 -classpath .;." + File.separator + "lib" + File.separator
                    + "* -Xmx512m com.panda.main.SingleTaskRunner";
        } else {
            cmd = "java -Dfile.encoding=UTF-8 -classpath .:." + File.separator + "lib" + File.separator
                    + "* -Xmx512m com.panda.main.SingleTaskRunner";
        }
        return cmd;
    }

}
