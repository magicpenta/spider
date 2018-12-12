package com.panda.dao.common;

import com.panda.entity.common.RequestConfigDO;
import org.junit.Test;

import java.util.List;

/**
 * 请求配置 dao 接口单元测试
 *
 * @author panda
 * @date 2018/12/12
 */
public class RequestConfigDaoTest {

    @Test
    public void testSelectAll() {
        List<RequestConfigDO> requestConfigList = RequestConfigDao.getInstance().selectAll();
        assert requestConfigList.size() >= 0;
    }
}
