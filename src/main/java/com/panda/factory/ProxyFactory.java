package com.panda.factory;

import com.panda.entity.common.ProxyDO;

/**
 * 代理工厂
 *
 * @author panda
 * @date 2018/2/4
 */
public class ProxyFactory {

    private static final ProxyFactory proxyFactory = new ProxyFactory();

    private volatile ProxyDO proxy;

    private ProxyFactory() {

    }

    public static ProxyFactory getInstance() {
        return proxyFactory;
    }

    public synchronized ProxyDO getProxy() {
        return proxy;
    }

    public synchronized void setProxy(ProxyDO proxy) {
        this.proxy = proxy;
    }

}
