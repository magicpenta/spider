package dao;

import entity.Task;
import org.junit.Test;

import java.util.List;

/**
 * 任务 dao 接口单元测试
 *
 * @author panda
 * @date 2018/3/26
 */
public class TaskDaoTest {

    @Test
    public void testSelectListByStatus() {
        List<Task> taskList = TaskDao.selectListByStatus(0);
        assert taskList != null;
    }

}
