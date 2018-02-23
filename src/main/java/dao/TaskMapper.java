package dao;

import entity.Task;

import java.util.List;

/**
 * 任务 mapper 接口
 *
 * @author panda
 * @date 2018/1/13
 */
public interface TaskMapper {

    Task selectOneById(int id);

    List<Task> selectListByStatus(int status);

    int insertList(List<Task> taskList);

    int updateStatus(Task task);

}
