package dao;

import entity.WebConfig;

/**
 * 网站配置 dao 接口
 *
 * @author panda
 * @date 2018/1/7
 */
public class WebConfigDao {

    public static WebConfig selectOneByDomain(String domain) {
        return BaseDao.selectOne("dao.WebConfigMapper.selectOneByDomain", domain);
    }

}
