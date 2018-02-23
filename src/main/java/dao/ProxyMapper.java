package dao;

import entity.Proxy;

import java.util.List;

/**
 * 代理 mapper 接口
 *
 * @author panda
 * @date 2018/2/3
 */
public interface ProxyMapper {

    Proxy findFirst();

    int insertList(List<Proxy> proxyList);

}
