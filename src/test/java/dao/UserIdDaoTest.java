package dao;

import entity.UserId;
import org.junit.Test;

import java.util.List;

/**
 * 用户 id dao 接口单元测试
 *
 * @author panda
 * @date 2018/2/22
 */
public class UserIdDaoTest {

    @Test
    public void testSelectMaxId() {
        int maxId = UserIdDao.selectMaxId();
        assert maxId > 0;
    }

    @Test
    public void testSelectList() {
        List<UserId> userIdList = UserIdDao.selectList(0);
        assert userIdList != null;
    }

}
