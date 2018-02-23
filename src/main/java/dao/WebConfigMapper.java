package dao;

import entity.WebConfig;

import java.util.List;

/**
 * 网站配置 mapper 接口
 *
 * @author panda
 * @date 2018/1/4
 */
public interface WebConfigMapper {

    List<WebConfig> selectList();

}
