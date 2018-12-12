package com.panda.dao.common;

import com.panda.dao.MapperFactory;
import com.panda.dao.common.mapper.ProxyMapper;
import com.panda.entity.common.ProxyDO;

import java.util.List;

/**
 * 代理 dao 接口
 *
 * @author panda
 * @date 2018/2/3
 */
public class ProxyDao {

    private static ProxyDao dao;

    private ProxyMapper mapper;

    private ProxyDao() {
        mapper = MapperFactory.createMapper(ProxyMapper.class);
    }

    public static ProxyDao getInstance() {
        if (dao == null) {
            synchronized (ProxyDao.class) {
                if (dao == null) {
                    dao = new ProxyDao();
                }
            }
        }
        return dao;
    }

    public ProxyDO selectRandomOne() {
        return mapper.selectRandomOne();
    }

    public int replaceList(List<ProxyDO> proxyList) {
        if (proxyList == null || proxyList.size() == 0) {
            return 0;
        }
        return mapper.replaceList(proxyList);
    }

}
