package dao;

import entity.WebConfig;

/**
 * 网站配置 mapper 接口
 *
 * @author panda
 * @date 2018/1/4
 */
public interface WebConfigMapper {

    WebConfig selectOneById(int id);

    WebConfig selectOneByDomain(String domain);

}
