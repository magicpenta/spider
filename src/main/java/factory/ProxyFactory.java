package factory;

import entity.Proxy;

/**
 * 代理工厂
 *
 * @author panda
 * @date 2018/2/4
 */
public class ProxyFactory {

    private static final ProxyFactory proxyFactory = new ProxyFactory();

    private volatile Proxy proxy;

    private ProxyFactory() {

    }

    public static ProxyFactory getInstance() {
        return proxyFactory;
    }

    public synchronized Proxy getProxy() {
        return proxy;
    }

    public synchronized void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

}
