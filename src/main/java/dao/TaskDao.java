package dao;

import entity.Task;

import java.util.List;

/**
 * 任务 dao 接口
 *
 * @author panda
 * @date 2018/1/13
 */
public class TaskDao {

    public static Task selectOneById(int id) {
        return BaseDao.selectOne("dao.TaskMapper.selectOneById", id);
    }

    public static List<Task> selectListByStatus(int status) {
        return BaseDao.selectList("dao.TaskMapper.selectListByStatus", status);
    }

    public static void main(String[] args) {
        Task task = TaskDao.selectOneById(1);
        System.out.println(task.getUrl());
    }
}
