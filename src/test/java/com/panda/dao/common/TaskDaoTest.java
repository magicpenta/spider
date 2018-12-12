package com.panda.dao.common;

import com.panda.entity.common.TaskDO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务 dao 接口单元测试
 *
 * @author panda
 * @date 2018/3/26
 */
public class TaskDaoTest {

    @Test
    public void testSelectOneById() {
        TaskDO task = TaskDao.getInstance().selectOneById(1);
        assert task != null;
    }

    @Test
    public void testSelectListByStatus() {
        List<TaskDO> taskList = TaskDao.getInstance().selectListByStatus(2);
        assert taskList.size() >= 0;
    }

    @Test
    public void testInsertList() {
        List<TaskDO> taskList = new ArrayList<TaskDO>();
        TaskDO task = new TaskDO();
        task.setTaskId(777L);
        task.setTaskName("panda");
        task.setUrl("https://www.baidu.com");
        task.setTaskStatus(0);
        taskList.add(task);
        int result = TaskDao.getInstance().insertList(taskList);
        assert result >= 0;
    }

    @Test
    public void testUpdateStatus() {
        TaskDO task = new TaskDO();
        task.setId(1);
        task.setTaskStatus(0);
        int result = TaskDao.getInstance().updateStatus(task);
        assert result >= 0;
    }

}
