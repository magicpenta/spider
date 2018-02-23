package daemon;

import config.Constants;
import dao.ProxyDao;
import entity.Proxy;
import factory.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单进程任务守护线程
 *
 * @author panda
 * @date 2018/2/4
 */
public class TaskDaemon extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(TaskDaemon.class);

    @Override
    public void run() {

        logger.info("开启守护线程...");

        while (true) {

            Proxy proxy = ProxyDao.findFirst();
            ProxyFactory.getInstance().setProxy(proxy);

            if (proxy != null) {
                logger.info("定时更新代理配置, proxy_ip:{}, proxy_port:{}", proxy.getProxyIp(), proxy.getProxyPort());
            }

            try {
                Thread.sleep(Constants.REQUEST_SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("守护线程休眠异常:", e);
            }
        }

    }

}
