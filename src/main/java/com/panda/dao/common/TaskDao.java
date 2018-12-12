package com.panda.dao.common;

import com.panda.dao.MapperFactory;
import com.panda.dao.common.mapper.TaskMapper;
import com.panda.entity.common.TaskDO;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 任务 dao 接口
 *
 * @author panda
 * @date 2018/1/13
 */
public class TaskDao {

    private static TaskDao dao;

    private TaskMapper mapper;

    private TaskDao() {
        mapper = MapperFactory.createMapper(TaskMapper.class);
    }

    public static TaskDao getInstance() {
        if (dao == null) {
            synchronized (TaskDao.class) {
                if (dao == null) {
                    dao = new TaskDao();
                }
            }
        }
        return dao;
    }

    public TaskDO selectOneById(int id) {
        Example example = new Example(TaskDO.class);
        example.and().andEqualTo("id", id);
        return mapper.selectOneByExample(example);
    }

    public List<TaskDO> selectListByStatus(int status) {
        // 缺少Limit
        Example example = new Example(TaskDO.class);
        example.and().andEqualTo("taskStatus", status);
        example.setOrderByClause("id ASC");
        return mapper.selectByExample(example);
    }

    public int insertList(List<TaskDO> taskList) {
        if (taskList == null || taskList.size() == 0) {
            return 0;
        }
        return mapper.insertList(taskList);
    }

    public int updateStatus(TaskDO task) {
        Example example = new Example(TaskDO.class);
        example.and().andEqualTo("id", task.getId());
        return mapper.updateByExampleSelective(task, example);
    }

}
