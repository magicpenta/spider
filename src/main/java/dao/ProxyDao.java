package dao;

import entity.Proxy;

import java.util.List;

/**
 * 代理 dao 接口
 *
 * @author panda
 * @date 2018/2/3
 */
public class ProxyDao {

    public static Proxy findFirst() {
        return BaseDao.selectOne("dao.ProxyMapper.findFirst", null);
    }

    public static int insertList(List<Proxy> proxyList) {
        return BaseDao.insert("dao.ProxyMapper.insertList", proxyList);
    }

}
