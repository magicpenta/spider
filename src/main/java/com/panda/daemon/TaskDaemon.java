package com.panda.daemon;

import com.panda.dao.common.ProxyDao;
import com.panda.entity.common.ProxyDO;
import com.panda.factory.ProxyFactory;
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

            ProxyDO proxy = ProxyDao.getInstance().selectRandomOne();
            ProxyFactory.getInstance().setProxy(proxy);

            if (proxy != null) {
                logger.info("定时更新代理配置, proxy_ip:{}, proxy_port:{}", proxy.getProxyIp(), proxy.getProxyPort());
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("守护线程休眠异常:", e);
            }
        }

    }

}
