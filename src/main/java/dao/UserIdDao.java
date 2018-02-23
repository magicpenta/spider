package dao;

import entity.UserId;

import java.util.List;

/**
 * 用户 id dao 接口
 *
 * @author panda
 * @date 2018/2/22
 */
public class UserIdDao {

    public static int selectMaxId() {
        return BaseDao.selectOne("dao.UserIdMapper.selectMaxId", null);
    }

    public static List<UserId> selectList(int id) {
        return BaseDao.selectList("dao.UserIdMapper.selectList", id);
    }

}
