package com.panda.dao.common.mapper;

import com.panda.dao.MyMapper;
import com.panda.entity.common.ProxyDO;

import java.util.List;

public interface ProxyMapper extends MyMapper<ProxyDO> {

    ProxyDO selectRandomOne();

    int replaceList(List<ProxyDO> proxyList);
}