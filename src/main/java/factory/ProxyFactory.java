package factory;

import entity.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 代理工厂
 *
 * @author panda
 * @date 2017/12/06
 */
public class ProxyFactory {

    private static final Logger logger = LoggerFactory.getLogger(ProxyFactory.class);

    private final static ReentrantLock lockProxy = new ReentrantLock();

    private static Queue<Proxy> proxyQueue = new LinkedList<Proxy>();

    public static Proxy getProxy() {
        Proxy proxy = null;
//        try {
//            if (lockProxy.tryLock(3, TimeUnit.SECONDS)) {
                proxy = proxyQueue.poll();
//                proxyQueue.offer(proxy);
//            }
//        } catch (InterruptedException e) {
//            logger.error("代理出队异常", e);
//        } finally {
//            lockProxy.unlock();
//        }
        return proxy;
    }

    public static void addProxy(List<Proxy> proxyList) {
//        try {
//            if (lockProxy.tryLock(3, TimeUnit.SECONDS)) {
                proxyQueue = new LinkedList<Proxy>(proxyList);
//            }
//        } catch (InterruptedException e) {
//            logger.error("代理入队异常", e);
//        } finally {
//            lockProxy.unlock();
//        }
    }

    public static boolean isProxyEmpty() {
        return proxyQueue.isEmpty();
    }

}
