package com.panda.dao.common;

import com.panda.entity.common.ProxyDO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 代理 dao 接口单元测试
 *
 * @author panda
 * @date 2018/2/3
 */
public class ProxyDaoTest {

    @Test
    public void testSelectRandomOne() {
        ProxyDO proxy = ProxyDao.getInstance().selectRandomOne();
        assert proxy != null;
    }

    @Test
    public void testReplaceList() {
        List<ProxyDO> proxyList = new ArrayList<ProxyDO>();
        ProxyDO proxy = new ProxyDO();
        proxy.setId(1);
        proxy.setProxyIp("127.0.0.1");
        proxy.setProxyPort(443);
        proxyList.add(proxy);
        int num = ProxyDao.getInstance().replaceList(proxyList);
        assert num > 0;
    }

}
