package dao;

import entity.WebConfig;
import java.util.List;

/**
 * 网站配置 dao 接口
 *
 * @author panda
 * @date 2018/1/7
 */
public class WebConfigDao {

    public static List<WebConfig> selectList() {
        return BaseDao.selectList("dao.WebConfigMapper.selectList", null);
    }

}
