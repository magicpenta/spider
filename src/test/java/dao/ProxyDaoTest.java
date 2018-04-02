package dao;

import entity.Proxy;
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
    public void testFindFirst() {
        Proxy proxy = ProxyDao.findOne();
        assert proxy != null;
    }

    @Test
    public void testUpdateList() {
        List<Proxy> proxyList = new ArrayList<Proxy>();
        Proxy proxy = new Proxy();
        proxy.setId(1);
        proxy.setProxyIp("127.0.0.1");
        proxy.setProxyPort(443);
        proxyList.add(proxy);
        int num = ProxyDao.insertList(proxyList);
        assert num > 0;
    }

}
