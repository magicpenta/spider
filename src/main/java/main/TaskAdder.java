package main;

import dao.TaskDao;
import dao.UserIdDao;
import entity.Task;
import entity.UserId;
import entity.enums.TaskStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务添加器
 *
 * @author panda
 * @date 2018/2/20
 */
public class TaskAdder {

    private static final Logger logger = LoggerFactory.getLogger(TaskAdder.class);

    private static final Integer COUNT = 10000;

    public static void main(String[] args) {
        logger.info("开始读取任务信息...");

        int maxId = UserIdDao.selectMaxId();

        if (maxId <= 0) {
            logger.error("不存在任务数据, 退出");
            System.exit(0);
        }

        logger.info("max_id:{}", maxId);

        int id = 0;

        while (id <= maxId) {

            logger.info("-------------");
            logger.info("本轮id:{}", id);

            List<UserId> userIdList = UserIdDao.selectList(id);
            if (userIdList != null) {
                logger.info("本次发现任务数:{}", userIdList.size());
            }

            List<Task> taskList = new ArrayList<Task>();
            for (UserId userId : userIdList) {
                Task task = new Task();
                task.setUrl("https://www.zhihu.com/people/" + userId.getUserId() + "/activities#is_task_url=1");
                task.setStatus(TaskStatusEnum.NOT_RUNNING.getValue());
                taskList.add(task);
            }
            int result = TaskDao.insertList(taskList);
            logger.info("本次新增任务数:{}", result);

            id += COUNT;
        }
    }

}
