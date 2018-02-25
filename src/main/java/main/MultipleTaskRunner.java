package main;

import config.Constants;
import daemon.ProcessDaemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ActivemqService;

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

    /**
     * 休眠时间，默认1分钟
     */
    private static final Integer SLEEP_TIME = 60000;

    public static void main(String[] args) {

        ActivemqService service = ActivemqService.getInstance();
        String queueName = Constants.TASK_QUEUE_NAME;

        while (true) {
            try {
                int nowProcessNum = processCount.get();
                int maxProcessNum = Constants.MAX_PROCESS;

                if (nowProcessNum >= maxProcessNum) {
                    logger.info("当前进程数 {} 大于最大进程数 {}, 不开启新进程...", nowProcessNum, maxProcessNum);
                    Thread.sleep(SLEEP_TIME);
                    continue;
                }

                logger.info("准备从 MQ 加载任务...");
                String message = service.getMessage(queueName);

                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec("\"C:\\Program Files\\Java\\jdk1.8.0_131\\bin\\java\" \"-javaagent:D:\\IntelliJ IDEA Ultimate\\lib\\idea_rt.jar=60652:D:\\IntelliJ IDEA Ultimate\\bin\" -Dfile.encoding=UTF-8 -classpath \"C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\charsets.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\deploy.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\access-bridge-64.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\cldrdata.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\dnsns.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\jaccess.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\jfxrt.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\localedata.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\nashorn.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\sunec.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\sunjce_provider.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\sunmscapi.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\sunpkcs11.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\ext\\zipfs.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\javaws.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\jce.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\jfr.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\jfxswt.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\jsse.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\management-agent.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\plugin.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\resources.jar;C:\\Program Files\\Java\\jdk1.8.0_131\\jre\\lib\\rt.jar;D:\\panda_project\\spider\\target\\classes;C:\\Users\\37516\\.m2\\repository\\org\\apache\\httpcomponents\\httpclient\\4.5.2\\httpclient-4.5.2.jar;C:\\Users\\37516\\.m2\\repository\\org\\apache\\httpcomponents\\httpcore\\4.4.4\\httpcore-4.4.4.jar;C:\\Users\\37516\\.m2\\repository\\commons-logging\\commons-logging\\1.2\\commons-logging-1.2.jar;C:\\Users\\37516\\.m2\\repository\\commons-codec\\commons-codec\\1.9\\commons-codec-1.9.jar;C:\\Users\\37516\\.m2\\repository\\org\\apache\\commons\\commons-lang3\\3.5\\commons-lang3-3.5.jar;C:\\Users\\37516\\.m2\\repository\\org\\slf4j\\slf4j-api\\1.7.25\\slf4j-api-1.7.25.jar;C:\\Users\\37516\\.m2\\repository\\org\\slf4j\\slf4j-log4j12\\1.7.25\\slf4j-log4j12-1.7.25.jar;C:\\Users\\37516\\.m2\\repository\\log4j\\log4j\\1.2.17\\log4j-1.2.17.jar;C:\\Users\\37516\\.m2\\repository\\org\\jsoup\\jsoup\\1.10.2\\jsoup-1.10.2.jar;C:\\Users\\37516\\.m2\\repository\\org\\apache\\activemq\\activemq-broker\\5.15.0\\activemq-broker-5.15.0.jar;C:\\Users\\37516\\.m2\\repository\\org\\apache\\activemq\\activemq-client\\5.15.0\\activemq-client-5.15.0.jar;C:\\Users\\37516\\.m2\\repository\\org\\apache\\geronimo\\specs\\geronimo-jms_1.1_spec\\1.1.1\\geronimo-jms_1.1_spec-1.1.1.jar;C:\\Users\\37516\\.m2\\repository\\org\\fusesource\\hawtbuf\\hawtbuf\\1.11\\hawtbuf-1.11.jar;C:\\Users\\37516\\.m2\\repository\\org\\apache\\geronimo\\specs\\geronimo-j2ee-management_1.1_spec\\1.0.1\\geronimo-j2ee-management_1.1_spec-1.0.1.jar;C:\\Users\\37516\\.m2\\repository\\org\\apache\\activemq\\activemq-openwire-legacy\\5.15.0\\activemq-openwire-legacy-5.15.0.jar;C:\\Users\\37516\\.m2\\repository\\com\\google\\guava\\guava\\18.0\\guava-18.0.jar;C:\\Users\\37516\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-databind\\2.6.7\\jackson-databind-2.6.7.jar;C:\\Users\\37516\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-annotations\\2.6.0\\jackson-annotations-2.6.0.jar;C:\\Users\\37516\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-core\\2.6.7\\jackson-core-2.6.7.jar;C:\\Users\\37516\\.m2\\repository\\xalan\\xalan\\2.7.1\\xalan-2.7.1.jar;C:\\Users\\37516\\.m2\\repository\\xalan\\serializer\\2.7.1\\serializer-2.7.1.jar;C:\\Users\\37516\\.m2\\repository\\net\\sourceforge\\nekohtml\\nekohtml\\1.9.22\\nekohtml-1.9.22.jar;C:\\Users\\37516\\.m2\\repository\\xerces\\xercesImpl\\2.11.0\\xercesImpl-2.11.0.jar;C:\\Users\\37516\\.m2\\repository\\xml-apis\\xml-apis\\1.4.01\\xml-apis-1.4.01.jar;C:\\Users\\37516\\.m2\\repository\\com\\google\\code\\gson\\gson\\2.8.0\\gson-2.8.0.jar;C:\\Users\\37516\\.m2\\repository\\org\\htmlparser\\htmlparser\\2.1\\htmlparser-2.1.jar;C:\\Users\\37516\\.m2\\repository\\org\\htmlparser\\htmllexer\\2.1\\htmllexer-2.1.jar;C:\\Users\\37516\\.m2\\repository\\org\\mybatis\\mybatis\\3.4.1\\mybatis-3.4.1.jar;C:\\Users\\37516\\.m2\\repository\\mysql\\mysql-connector-java\\5.1.38\\mysql-connector-java-5.1.38.jar;C:\\Users\\37516\\.m2\\repository\\org\\mongodb\\mongodb-driver\\3.6.1\\mongodb-driver-3.6.1.jar;C:\\Users\\37516\\.m2\\repository\\org\\mongodb\\bson\\3.6.1\\bson-3.6.1.jar;C:\\Users\\37516\\.m2\\repository\\org\\mongodb\\mongodb-driver-core\\3.6.1\\mongodb-driver-core-3.6.1.jar\" main.SingleTaskRunner" + " " + Base64.getEncoder().encodeToString(message.getBytes()));
                new ProcessDaemon(message).start();
//                new RuntimeThread(process.getErrorStream()).start();
//                new RuntimeThread(process.getInputStream()).start();
//                int status = process.waitFor();
//                if (status == 0) {
//                    logger.info("启动进程成功！");
//                } else {
//                    logger.info("启动进程失败！");
//                }
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

}
