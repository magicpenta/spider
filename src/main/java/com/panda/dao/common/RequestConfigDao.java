package com.panda.dao.common;

import com.panda.dao.MapperFactory;
import com.panda.dao.common.mapper.RequestConfigMapper;
import com.panda.entity.common.RequestConfigDO;

import java.util.List;

/**
 * 请求配置 dao 接口
 *
 * @author panda
 * @date 2018/1/7
 */
public class RequestConfigDao {

    private static RequestConfigDao dao;

    private RequestConfigMapper mapper;

    private RequestConfigDao() {
        mapper = MapperFactory.createMapper(RequestConfigMapper.class);
    }

    public static RequestConfigDao getInstance() {
        if (dao == null) {
            synchronized (RequestConfigDao.class) {
                if (dao == null) {
                    dao = new RequestConfigDao();
                }
            }
        }
        return dao;
    }

    public List<RequestConfigDO> selectAll() {
        return mapper.selectAll();
    }

}
