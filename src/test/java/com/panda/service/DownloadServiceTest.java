package com.panda.service;

import com.panda.entity.common.TaskDO;
import org.junit.Test;

/**
 * 下载服务单元测试
 *
 * @author panda
 * @date 2018/2/4
 */
public class DownloadServiceTest {

    @Test
    public void testGetResponseBody() {
        TaskDO task = new TaskDO();
        task.setId(1);
        task.setUrl("https://www.baidu.com");
        task.setTaskStatus(0);
        String responseBody = new DownloadService().getResponseBody(task);
        assert responseBody != null;
    }
}
